const API_URL = "http://localhost:8080/api-server";
var currentRequestForm = "";
var ITEMS_ON_PAGE = 10;
$(document).ready(function () {



  const customerToTableRowHTML = (customer) => {
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
            
                    <button id='btn_care_code${customer.id}' data-toggle="modal" data-target="#transactionModal"
            class="btn_care ColVis_Button ColVis_MasterButton btn btn-white btn-info btn-bold"><span>
                <i class="ace-icon 	fa fa-group bigger-130"></i></span></button>
        
            <button id="btn_delete_code${customer.id}"
                class="btn_delete ColVis_Button ColVis_MasterButton btn btn-white btn-info btn-bold"><span>
                    <i class="ace-icon fa fa-trash-o bigger-130"></i></span></button>

        </div>

    </td>`
  }


  function timeConverter(UNIX_timestamp) {
    var a = new Date(UNIX_timestamp);
    // var months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
    var year = a.getFullYear();
    var month = a.getMonth();
    var date = a.getDate();
    var hour = a.getHours();
    var min = a.getMinutes();
    var sec = a.getSeconds();
    var time = hour + ':' + min + ':' + sec + '       ' + date + '-' + month + '-' + year;
    return time;
  }


  const transactionToTableRowHTML = (obj) => {
    return `<tr>
      <td>${timeConverter(obj.createdDate)}</td>
      <td style="max-width: 300px;">${obj.note}</td>
      <td>${obj.createdBy}</td>
      </tr>`
  }

  const loadData = (url) => {
    console.log(url);
    var data = "";



    fetch(url)
      .then(res => res.json())
      .then(res => {
        res.map(customer => {
          data += `<tr id="checkbox-row-${customer.id}">
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

  const fetchFirstPagination = (url) => {
    console.log(url);
    fetch(url).then(res => res.json()).then(count => {
      customerPagination(count, ITEMS_ON_PAGE, 1);
    })
      .catch(e => {
        console.log('err' + e);
      })
  }



  const fetchData = async () => {
    if (!!currentRequestForm) currentRequestForm = $('#customer_form').serialize();
    loadData(`${API_URL}/customer/list?${currentRequestForm}`);
    fetchFirstPagination(`${API_URL}/customer/count?${currentRequestForm}`);
  }

  fetchData();

  $('#btn_search').click(() => {
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
    fetch(`${API_URL}/staff/assignment-customer?id=${customerId}`)
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
    fetch(`${API_URL}/customer?id=${customerId}`)
      .then(res => res.json())
      .then(res => {
        $('#customerForm')[0].reset();
        binding(res);
      })
      .catch(e => {
        console.log(e);
      })
  })


  $("#btn-addCareNote").click(() => {
    const note = $('#customerCareNote').val();
    const customerId = $('#id-customer-trans').val();
    console.log(customerId);

    $.confirm({
      title: 'Thêm ghi chú',
      boxWidth:'600px',
      useBootstrap:false,
      content: '' +
        '<form action=""  class="formName">' +
        '<div class="form-group">' +
        '<textarea type="text" style="height:150px;" placeholder="Nhập ghi chú" class="cname form-control" required />' +
        '</div>' +
        '</form>',
      buttons: {
        formSubmit: {
          text: 'Thêm',
          btnClass: 'btn-success',
          action: function () {
            let note = this.$content.find('.cname').val();
            if (!!name) {
              $.alert('');
              return false;
            }
            const data = {
              note, customerId, type: 0
            }

            fetch(`${API_URL}/transaction`, {
              method: 'POST',
              body: JSON.stringify(data),
              headers: {
                'Content-Type': 'application/json'
              }
            }).then(res => res.json())
              .then(res => {
                const tbody = $('#tbody-customerCare')[0];
                tbody.innerHTML = transactionToTableRowHTML(res) + tbody.innerHTML;
                $.alert('Thao tác thành công');
              })
              .catch(e => {
                console.log(e);
              })
          }
        },
        cancel: {
          text: 'Hủy'
        },
      },
      onContentReady: function () {
        // bind to events
        var jc = this;
        this.$content.find('form').on('submit', function (e) {
          // if the user submits the form by pressing enter in the field.
          e.preventDefault();
          jc.$$formSubmit.trigger('click'); // reference the button and click it
        });
      }
    });

  })


  $("#btn-addTourNote").click(() => {
    const note = $('#customerTourNote').val();
    const customerId = $('#id-customer-trans').val();
    console.log(customerId);
    const data = {
      note, customerId, type: 1
    }
    console.log(JSON.stringify(data));
    $.confirm({
      title: false,
      content: 'Bạn có muốn thực hiện các thay đổi!',
      buttons: {
        confirm: {
          text: 'Đồng ý',
          btnClass: 'btn-danger',
          action: () => {
            fetch(`${API_URL}/transaction`, {
              method: 'POST',
              body: JSON.stringify(data),
              headers: {
                'Content-Type': 'application/json'
              }
            }).then(res => res.json())
              .then(res => {
                const tbody = $('#tbody-customerTour')[0];
                tbody.innerHTML = transactionToTableRowHTML(res) + tbody.innerHTML;
                $('#customerTourNote').val('')
              })
              .catch(e => {
                console.log(e);
              })
          }
        },
        cancel: {
          text: 'Hủy'
        }
      }
    });
  })


  $("#dynamic-table").on("click", "button[id^='btn_care']", function () {

    let id = $(this).attr('id');
    let customerId = id.substr(id.indexOf("_code") + 5);
    $('#id-customer-trans').val(customerId);
    const customerCareTable = $('#customerCare > tbody')[0];
    const customerTourTable = $('#customerTour > tbody')[0];
    let customerCareData = "";
    let customerTourData = "";
    fetch(`${API_URL}/transaction/list?customerId=${customerId}`)
      .then(res => res.json())
      .then(res => {
        res.map(e => {
          const row = transactionToTableRowHTML(e);
          if (e.type == 0) {
            customerCareData += row;
          } else if (e.type == 1) customerTourData += row;
        })
        customerCareTable.innerHTML = customerCareData;
        customerTourTable.innerHTML = customerTourData;

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
    $.confirm({
      title: false,
      content: 'Bạn có muốn thực hiện thao tác xóa!',
      buttons: {
        confirm: {
          text: 'Đồng ý',
          btnClass: 'btn-danger',
          action: () => {
            fetch(`${API_URL}/customer`, {
              method: 'DELETE',
              body: JSON.stringify(data),
              headers: {
                'Content-Type': 'application/json'
              }
            }).then(res => res.json())
              .then(res => {
                if (res = true) {
                  $("#dynamic-table input[class^='checkbox-delete']:checked").closest('tr').remove();
                  $.alert('Đã xóa thành công');
                }
              });
          }

        },
        cancel: {
          text: 'Hủy'
        }
      }
    });

  })
  $("#dynamic-table").on("click", "button[id^='btn_delete']", function () {

    let id = $(this).attr('id');
    let customerId = id.substr(id.indexOf("_code") + 5);
    let ids = [parseInt(customerId)];
    let data = { 'ids': ids }
    $.confirm({
      title: false,
      content: 'Bạn có muốn thực hiện thao tác xóa!',
      buttons: {
        confirm: {
          text: 'Đồng ý',
          btnClass: 'btn-danger',
          action: () => {
            fetch(`${API_URL}/customer`, {
              method: 'DELETE',
              body: JSON.stringify(data),
              headers: {
                'Content-Type': 'application/json'
              }
            }).then(res => res.json())
              .then(res => {
                if (res = true) {
                  $(this).closest("tr").remove();
                  $.alert('Đã xóa thành công');
                }
              });
          }

        },
        cancel: {
          text: 'Hủy'
        }
      }
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

    $.confirm({
      title: false,
      content: 'Bạn có muốn thực hiện các thay đổi!',
      buttons: {
        confirm: {
          text: 'Đồng ý',
          btnClass: 'btn-danger',
          action: () => {
            fetch(`${API_URL}/staff/assignment-customer`, {
              method: 'POST', // or 'PUT'
              body: JSON.stringify(data), // data can be `string` or {object}!
              headers: {
                'Content-Type': 'application/json'
              }
            }).then(res => res.json())
              .then(res => {
                $.alert('Thực hiện thành công');
              });
          }
        },
        cancel: {
          text: 'Hủy'
        }
      }
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
    $.confirm({
      title: false,
      content: 'Bạn có muốn thực hiện các thay đổi!',
      buttons: {
        confirm: {
          text: 'Đồng ý',
          btnClass: 'btn-danger',
          action: () => {
            fetch(`${API_URL}/customer`, {
              method: method,
              body: JSON.stringify(data), // data can be `string` or {object}!
              headers: {
                'Content-Type': 'application/json'
              }
            }).then(res => res.json())
              .then(res => {
                $('#buildingForm')[0].reset();
                $('#myModal').modal('hide');
                if (method == 'PUT') {
                  $(`#checkbox-row-${res.id}`).html(buildingToTableRowHTML(res));
                } else {
                  const pageString = $('#pagination-container .active > span')[0].innerHTML;
                  const pageNumber = parseInt(pageString);
                  loadData(`${API_URL}/building/list?${currentRequestForm}&page=${pageNumber}&size=${ITEMS_ON_PAGE}`);
                  console.log(`${API_URL}/building/list?${currentRequestForm}&page=${pageNumber}&size=${ITEMS_ON_PAGE}`);
                  $.alert('Thực hiện thành công');
                }
              });
          },
        },
        cancel: {
          text: 'Hủy'
        }
      }
    });

  });





});