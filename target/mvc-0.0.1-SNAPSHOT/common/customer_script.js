const API_URL = "http://localhost:8080/api-server";
var currentRequestForm = "";
var ITEMS_ON_PAGE = 10;
$(document).ready(function () {



  const customerToTableRowHTML = (customer)=>{
    return `<td class="center">
        <label class="pos-rel">
            <input class="checkbox-delete c-id${customer.id}" type="checkbox" class="ace">
            
        </label>
    </td>
    <td>
        <a href="#">${customer.name}</a>
    </td>
    <td>${customer.phone}</td>
    <td>${customer.email}</td>
    <td>${customer.demand}</td>
    <td></td>
    <td></td>
    <td></td>
    <td>
        <div class="hidden-sm hidden-xs action-buttons">
            <button id='btn_assign_code${customer.id}' data-toggle="modal"
                data-target="#modalStaff"
                class="btn_assign ColVis_Button ColVis_MasterButton btn btn-white btn-info btn-bold"><span>
                    <i class="ace-icon glyphicon glyphicon-user"></i></span></button>
            <button id='btn_update_code${customer.id}' data-toggle="modal" data-target="#myModal"
                class="btn_update ColVis_Button ColVis_MasterButton btn btn-white btn-info btn-bold"><span>
                    <i class="ace-icon fa fa-pencil bigger-130"></i></span></button>
            <button id="btn_delete_code${customer.id}"
                class="btn_delete ColVis_Button ColVis_MasterButton btn btn-white btn-info btn-bold"><span>
                    <i class="ace-icon fa fa-trash-o bigger-130"></i></span></button>

        </div>

    </td>`
  }

  const loadData = (url) => {
    console.log(url);
    var data = "";



    fetch(url)
      .then(res => res.json())
      .then(res => {
        res.map(customer => {
          data +=  `<tr id="checkbox-row-${customer.id}">
          ${customerToTableRowHTML(customer)}
          </tr>`
        })
        $('#data-customer-list')[0].innerHTML = data;

      }
      )
  }


  const customerPagination = (numItems, itemsOnPage, currentPage) => {

    $('#pagination-container').pagination({
      items: numItems,
      itemsOnPage: itemsOnPage,
      egdes: 2,
      currentPage: currentPage,
      prevText: "Previous",
      nextText: "Next",
      onPageClick: function (pageNumber) {
        loadData(`${API_URL}/customer/list?${currentRequestForm}&page=${pageNumber}&size=${ITEMS_ON_PAGE}`);
      }
    });
  }

  const fetchFirstPagination= (url)=>{
    console.log(url);
    fetch(url).then(res=> res.json()).then(count=>{
      customerPagination(count,ITEMS_ON_PAGE,1);
    })
    .catch(e=>{
      console.log('err'+e);
    })
  }



  const fetchData = async()=>{
    if(!!currentRequestForm) currentRequestForm = $('#customer_form').serialize();
    loadData(`${API_URL}/customer/list?${currentRequestForm}`);
    fetchFirstPagination(`${API_URL}/customer/count?${currentRequestForm}`);
  }

  fetchData();

  $('#btn_search').click(()=>{
    currentRequestForm = $('#customer_form').serialize();
    fetchData();
  })


  $("#dynamic-table").on("click", "button[id^='btn_assign']", function () {
    var tbody =
      document.getElementById("tBody");
    let id = $(this).attr('id');
    let customerId = id.substr(id.indexOf("_code") + 5);
    $('#assign_customerId').val(customerId);
    var data = "";
    fetch('http://localhost:8080/api-server/staff/assignment-customer?id=' + customerId)
      .then(res => res.json())
      .then(res => {
        console.log(res);
        res.map(e => {
          data += '<tr> <td><input type="hidden" id="assignstaff_code' + e[0] + '"> <input type="checkbox"' + e[2] + ' ></td> <td>' + e[1] + '</td> </tr>';
        })
        tbody.innerHTML = data;
      }
      )

  });


  $("#dynamic-table").on("click", "button[id^='btn_update']", function () {
    console.log('abc');
    $('#submit_save')[0].innerHTML = 'Cập nhật';

    let id = $(this).attr('id');
    let customerId = id.substr(id.indexOf("_code") + 5);
    $('#modal_customerId').val(customerId);
    fetch('http://localhost:8080/api-server/customer?id=' + customerId)
      .then(res => res.json())
      .then(res => {
        $('#customerForm')[0].reset();
        binding(res);
      })
      .catch(e => {
        console.log(e);
      })
  })


  $("#btn_add_customer").click(() => {
    $('#submit_save')[0].innerHTML = 'Thêm mới';

    $('#customerForm')[0].reset();
  })


  function binding(obj) {
    const map = new Map(Object.entries(obj));
    var inputList = $("#customerForm .z-field");
    for (let i = 0; i < inputList.length; i++) {
      const data = map.get(inputList[i]['name']);
      if (!!data) {
        if (inputList[i]['name'] == 'customerType') {
          if (obj.customerType.includes(inputList[i]['value'])) {
            inputList[i]['checked'] = true;
          }
        }
        else {
          inputList[i]['value'] = data;
        }

      }
    }
  }


  $("#delete-selected-customer").click(() => {
    var rows = $("input[class^='checkbox-delete']:checked");
    let ids = [];
    for (let i = 0; i < rows.length; i++) {
      let className = rows[i].className;
      let id = className.substr(className.indexOf("c-id") + 4);
      ids.push(parseInt(id));
    }
    let data = { 'ids': ids }
    console.log(JSON.stringify(data));
    fetch('http://localhost:8080/api-server/customer', {
      method: 'DELETE',
      body: JSON.stringify(data),
      headers: {
        'Content-Type': 'application/json'
      }
    }).then(res => res.json())
      .then(res => {
        if(res=true){
          $("#dynamic-table input[class^='checkbox-delete']:checked").closest('tr').remove();
        }
      });

  })
  $("#dynamic-table").on("click", "button[id^='btn_delete']", function () {

    let id = $(this).attr('id');
    let customerId = id.substr(id.indexOf("_code") + 5);
    let ids = [parseInt(customerId)];
    let data = { 'ids': ids }
    fetch('http://localhost:8080/api-server/customer', {
      method: 'DELETE',
      body: JSON.stringify(data),
      headers: {
        'Content-Type': 'application/json'
      }
    }).then(res => res.json())
      .then(res => {
        $(this).closest("tr").remove();
      });

  });



  $("#saveAssignmentStaff").click(() => {
    var x = $("#tBody")[0].rows;
    var staffId = [];
    var customerId = [];
    $.each(x, function (i, field) {
      if ($(field).find('input[type=checkbox]').is(':checked')) {
        let id = $(field).find("input[id^='assignstaff_code']").attr('id');
        let getStaffId = id.substr(id.indexOf("_code") + 5);
        staffId.push(parseInt(getStaffId));
      };
    });
    customerId = (parseInt($('#assign_customerId').attr('value')));
    let data = { staffId, customerId };
    console.log(JSON.stringify(data));

    fetch('http://localhost:8080/api-server/staff/assignment-customer', {
      method: 'POST', // or 'PUT'
      body: JSON.stringify(data), // data can be `string` or {object}!
      headers: {
        'Content-Type': 'application/json'
      }
    }).then(res => res.json())
      .then(res => {
        console.log(res);
      });

  })

  $("#submit_save").click(function () {
    var x = $("#customerForm").serializeArray();
    var type = [];
    var data = {};
    $.each(x, function (i, field) {
      data[field.name] = field.value;
      if (field.name == 'customerType') {
        type.push(field.value);
      }
    });
    let method = 'POST';
    if ((!!$('#modal_customerId')[0].value)) {
      method = 'PUT';
    }

    console.log($('#modal_customerId')[0].value);
    console.log(method);
    data['customerType'] = type;
    
    fetch('http://localhost:8080/api-server/customer', {
      method: method,
      body: JSON.stringify(data), // data can be `string` or {object}!
      headers: {
        'Content-Type': 'application/json'
      }
    }).then(res => res.json())
      .then(res => {
        console.log(res);
        $('#customerForm')[0].reset();
        $('#myModal').modal('hide');
        if(method=='PUT'){
          $(`#checkbox-row-${res.id}`).html(customerToTableRowHTML(res));
        }else{
          const pageString = $('#pagination-container .active > span')[0].innerHTML;
          const pageNumber = parseInt(pageString);
          loadData(`${API_URL}/customer/list?${currentRequestForm}&page=${pageNumber}&size=${ITEMS_ON_PAGE}`);
        }
      });

  });





});