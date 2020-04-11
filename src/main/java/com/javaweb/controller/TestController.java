package com.javaweb.controller;

import com.javaweb.dto.BuildingDTO;
import com.javaweb.dto.CustomerDTO;
import com.javaweb.enums.BuildingTypeEnum;
import com.javaweb.enums.DistrictEnum;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.ICustomerService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @Autowired
    private IBuildingService service;

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/test-save-building")
    @ResponseBody
    public ResponseEntity save(){
        for(int i=0;i<100;i++){
            service.save(newDTOTest());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test-save-customer")
    @ResponseBody
    public ResponseEntity saveCustomer(){
        for(int i=0;i<100;i++){
            customerService.save(newCustomerDTOTest());
        }
        return ResponseEntity.ok().build();
    }

    private static String[] buildingName = new String[]{"Landmark","Bitexco","FLC Tower","Lotte Center","Vinfast","Sonadezi"};
    private static String[] districtName = new String[]{"Nguyễn Huệ","Lê Lai","Lê Thị Riêng","Nguyễn Trãi",
                "Hàm Nghi","Phạm Ngũ Lão","Võ Văn Kiệt"};
    private static int random(int min,int max){
        int random = (int) (Math.random()*(max-min+1) + min);
        if(random>max) random = max;
        return random;
    }
    private String names = "ABCDEFGH";
    private DistrictEnum[] districtEnums = DistrictEnum.values();
    private BuildingTypeEnum[] buildingTypeEnums = BuildingTypeEnum.values();
    private BuildingDTO newDTOTest(){
        BuildingDTO dto = new BuildingDTO();
        dto.setName(buildingName[random(0,buildingName.length-1)] + " " + (random(1,200)));
        dto.setBuildingArea((long)random(100,5000));
        dto.setNumberOfBasement((long)random(1,10));
        dto.setDistrict(districtEnums[random(0,districtEnums.length-1)].name());
        dto.setWard("phường " + (random(0,10)));
        dto.setStreet("đường " + districtName[random(0,districtName.length-1)]);
        dto.setManagerName("Nguyễn Văn " + (names.charAt(random(0,names.length()-1))));
        dto.setManagerPhone(0 + "" + (RandomStringUtils.randomNumeric(9)));
        dto.setBuildingType(new String[]{buildingTypeEnums[random(0, buildingTypeEnums.length - 1)].name()});
        dto.setRentArea(new StringBuilder().append(random(1,10)*100).append(",").append(random(1,10)*100).toString());
        dto.setRentCost((long)random(100,3000));

        dto.setStructure("");
        dto.setCostDescription("");
        dto.setServiceCost("");
        dto.setCarCost("");
        dto.setMotorbikeCost("");
        dto.setOvertimeCost("");
        dto.setElectricityCost("");
        dto.setDeposit("");
        dto.setPayment("");
        dto.setRentTime("");
        dto.setDecoratorTime("");
        return dto;
    }
    private CustomerDTO newCustomerDTOTest(){
        CustomerDTO dto = new CustomerDTO();
        dto.setName("Nguyễn Văn " + (names.charAt(random(0,names.length()-1))));
        dto.setPhone(0 + "" + (RandomStringUtils.randomNumeric(9)));
        dto.setEmail(("nguyenvan" + (names.charAt(random(0,names.length()-1)))+ (RandomStringUtils.randomNumeric(3)) +"@gmail.com").toLowerCase());
        dto.setDemand("");
        dto.setCompany("");
        dto.setNote("");
        return dto;
    }
}
