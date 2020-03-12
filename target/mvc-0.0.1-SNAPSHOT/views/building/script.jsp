<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script >

$(document).ready(function(){
    $("button[id^='btn_assign'").click(function() {		
        
        var tbody =  
            document.getElementById("tBody"); 
        let id = $(this).attr('id');
        let buildingId = id.substr(id.indexOf("_code")+5);
        $('#assign_buildingId').val(buildingId);
        var data = "";
        fetch('http://localhost:8080/api-server/staff?buildingId='+buildingId)
        .then(res=>res.json())
        .then(res=>{
            res.map(e=>{
                data += '<tr> <td><input type="hidden" id="assignstaff_code'+e.id+'"> <input type="checkbox"'+e.checked+' ></td> <td>'+e.fullname+'</td> </tr>';
            })				
            tbody.innerHTML = data;
        }
        )
        
    });

$("#saveAssignmentStaff").click(()=>{
    var x = $("#tBody")[0].rows;
    var staffId = [];
    var buildingId = [];
    $.each(x, function(i, field){
        if($(field).find('input[type=checkbox]').is(':checked')){
            let id = $(field).find("input[id^='assignstaff_code']").attr('id');
            let getStaffId = id.substr(id.indexOf("_code")+5);
            staffId.push(parseInt(getStaffId));	
        };
    });
    buildingId.push(parseInt($('#assign_buildingId').attr('value')));
       let data = {staffId,buildingId};
       console.log(JSON.stringify(data));
           
       fetch('http://localhost:8080/api-server/assignmentstaff', {
        method: 'POST', // or 'PUT'
        body: JSON.stringify(data), // data can be `string` or {object}!
        headers: {
          'Content-Type': 'application/json'
        }
      }).then(res => res.json())
      .then(res =>	{
        console.log(res);  
      });
       
})

$("#submit_save").click(function(){
    var x = $("#form_save").serializeArray();
    var type = [];
    var data = {};
    $.each(x, function(i, field){
      data[field.name] =  field.value;
      if(field.name == 'buildingType'){
          type.push(field.value);
      }
    });
    data['buildingType'] = type;
    console.log(JSON.stringify(data));
    console.log('new');
    fetch('http://localhost:8080/api-server/building', {
        method: 'POST', // or 'PUT'
        body: JSON.stringify(data), // data can be `string` or {object}!
        headers: {
          'Content-Type': 'application/json'
        }
      }).then(res => res.json())
      .then(res =>	{
        console.log(res);  
      });
    
  });
  });
</script>
