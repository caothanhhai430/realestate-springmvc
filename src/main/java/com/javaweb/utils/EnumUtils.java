package com.javaweb.utils;

import java.util.HashMap;
import java.util.Map;

import com.javaweb.enums.BuildingTypeEnum;
import com.javaweb.enums.DistrictEnum;

public class EnumUtils{
	public static Map<String,String> getBuildingTypes(){
		Map<String,String> buildingTypes = new HashMap<>();
		
		for(BuildingTypeEnum key : BuildingTypeEnum.values()) {
			buildingTypes.put(key.name(),key.getBuildingType());
		}
		return buildingTypes;
	}
	
	public static Map<String,String> getDistricts(){
		Map<String,String> districts = new HashMap<>();
		
		for(DistrictEnum key : DistrictEnum.values()) {
			districts.put(key.name(),key.getDistrictName());
		}
		return districts;
	}
	
	
}
