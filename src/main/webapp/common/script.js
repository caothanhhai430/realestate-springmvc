const API_URL = "http://localhost:8080/api-server";
var currentRequestForm = "";
var ITEMS_ON_PAGE = 10;
$(document).ready(function () {



  const loadData = (url) => {
    console.log(url);
    var data = "";

    fetch(url)
      .then(res => res.json())
      .then(res => {
        res.map(building => {
          data += `<tr id="checkbox-row-${building.id}">
                            <td class="center">
                                <label class="pos-rel">
                                    <input class="checkbox-delete c-id${building.id}" type="checkbox" class="ace">
                                    
                                </label>
                            </td>
                            <td>
                                <a href="#">${building.name}</a>
                            </td>
                            <td>${building.address}</td>
                            <td>${building.managerName}</td>
                            <td>${building.managerPhone}</td>
                            <td>${building.buildingArea}</td>
                            <td>${building.rentCost}</td>
                            <td>${building.getBuildingTypeArray}</td>
                            <td>${building.rentArea}</td>
                            <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    <button id='btn_assign_code${building.id}' data-toggle="modal"
                                        data-target="#modalStaff"
                                        class="btn_assign ColVis_Button ColVis_MasterButton btn btn-white btn-info btn-bold"><span>
                                            <i class="ace-icon glyphicon glyphicon-user"></i></span></button>
                                    <button id='btn_update_code${building.id}' data-toggle="modal" data-target="#myModal"
                                        class="btn_update ColVis_Button ColVis_MasterButton btn btn-white btn-info btn-bold"><span>
                                            <i class="ace-icon fa fa-pencil bigger-130"></i></span></button>
                                    <button id="btn_delete_code${building.id}"
                                        class="btn_delete ColVis_Button ColVis_MasterButton btn btn-white btn-info btn-bold"><span>
                                            <i class="ace-icon fa fa-trash-o bigger-130"></i></span></button>

                                </div>

                            </td>
                        </tr>`
        })
        $('#data-building-list')[0].innerHTML = data;

      }
      )
  }


  const buildingPagination = (numItems, itemsOnPage, currentPage) => {

    $('#pagination-container').pagination({
      items: numItems,
      itemsOnPage: itemsOnPage,
      egdes: 2,
      currentPage: currentPage,
      prevText: "Previous",
      nextText: "Next",
      onPageClick: function (pageNumber) {
        loadData(`${API_URL}/building/list?${currentRequestForm}&page=${pageNumber}&size=10`);
      }
    });
  }

  const fetchFirstPagination= (url)=>{
    console.log(url);
    fetch(url).then(res=> res.json()).then(count=>{
      console.log(count);
      buildingPagination(count,ITEMS_ON_PAGE,1);
    })
    .catch(e=>{
      console.log('err'+e);
    })
  }



  const fetchData = async()=>{
    if(!!currentRequestForm) currentRequestForm = $('#building_form').serialize();
    loadData(`${API_URL}/building/list?${currentRequestForm}`);
    fetchFirstPagination(`${API_URL}/building/count?${currentRequestForm}`);
  }

  fetchData();

  $('#btn_search').click(()=>{
    currentRequestForm = $('#building_form').serialize();
    fetchData();
  })


  $("#dynamic-table").on("click", "button[id^='btn_assign']", function () {
    var tbody =
      document.getElementById("tBody");
    let id = $(this).attr('id');
    let buildingId = id.substr(id.indexOf("_code") + 5);
    $('#assign_buildingId').val(buildingId);
    var data = "";
    fetch('http://localhost:8080/api-server/staff/assignment?id=' + buildingId)
      .then(res => res.json())
      .then(res => {
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
    let buildingId = id.substr(id.indexOf("_code") + 5);
    $('#modal_buildingId').val(buildingId);
    fetch('http://localhost:8080/api-server/building?id=' + buildingId)
      .then(res => res.json())
      .then(res => {
        $('#buildingForm')[0].reset();
        binding(res);
      })
      .catch(e => {
        console.log(e);
      })
  })


  $("#btn_add_building").click(() => {
    $('#submit_save')[0].innerHTML = 'Thêm mới';

    $('#buildingForm')[0].reset();
  })


  function binding(obj) {
    const map = new Map(Object.entries(obj));
    var inputList = $("#buildingForm .z-field");
    for (let i = 0; i < inputList.length; i++) {
      const data = map.get(inputList[i]['name']);
      if (!!data) {
        if (inputList[i]['name'] == 'buildingType') {
          if (obj.buildingType.includes(inputList[i]['value'])) {
            inputList[i]['checked'] = true;
          }
        }
        else {
          inputList[i]['value'] = data;
        }

      }
    }
  }


  $("#delete-selected-building").click(() => {
    var rows = $("input[class^='checkbox-delete']:checked");
    let ids = [];
    for (let i = 0; i < rows.length; i++) {
      let className = rows[i].className;
      let id = className.substr(className.indexOf("c-id") + 4);
      ids.push(parseInt(id));
    }
    let data = { 'ids': ids }
    console.log(JSON.stringify(data));
    fetch('http://localhost:8080/api-server/building', {
      method: 'DELETE',
      body: JSON.stringify(data),
      headers: {
        'Content-Type': 'application/json'
      }
    }).then(res => res.json())
      .then(res => {
        console.log(res);
      });

  })
  $("#dynamic-table").on("click", "button[id^='btn_delete']", function () {

    let id = $(this).attr('id');
    let buildingId = id.substr(id.indexOf("_code") + 5);
    let ids = [parseInt(buildingId)];
    let data = { 'ids': ids }
    fetch('http://localhost:8080/api-server/building', {
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
    var buildingId = [];
    $.each(x, function (i, field) {
      if ($(field).find('input[type=checkbox]').is(':checked')) {
        let id = $(field).find("input[id^='assignstaff_code']").attr('id');
        let getStaffId = id.substr(id.indexOf("_code") + 5);
        staffId.push(parseInt(getStaffId));
      };
    });
    buildingId = (parseInt($('#assign_buildingId').attr('value')));
    let data = { staffId, buildingId };
    console.log(JSON.stringify(data));

    fetch('http://localhost:8080/api-server/staff/assignment', {
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
    var x = $("#buildingForm").serializeArray();
    var type = [];
    var data = {};
    $.each(x, function (i, field) {
      data[field.name] = field.value;
      if (field.name == 'buildingType') {
        type.push(field.value);
      }
    });
    let method = 'POST';
    if ((!!$('#modal_buildingId')[0].value)) {
      method = 'PUT';
    }

    console.log($('#modal_buildingId')[0].value);
    console.log(method);
    data['buildingType'] = type;
    console.log(JSON.stringify(data));
    console.log('new');
    fetch('http://localhost:8080/api-server/building', {
      method: method,
      body: JSON.stringify(data), // data can be `string` or {object}!
      headers: {
        'Content-Type': 'application/json'
      }
    }).then(res => res.json())
      .then(res => {
        console.log(res);
        $('#buildingForm')[0].reset();
        $('#myModal').modal('hide');
      });

  });





});