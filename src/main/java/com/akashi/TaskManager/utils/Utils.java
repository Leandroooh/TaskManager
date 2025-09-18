package com.akashi.TaskManager.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

public class Utils {

	public static void copyNonNullProperties(Object source, Object target){
		BeanUtils.copyProperties(source, target, getNullPropertiesName(source));
	}

	public static String[] getNullPropertiesName(Object source){

		final BeanWrapper src = new BeanWrapperImpl(source);
		PropertyDescriptor[] propertyData = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<>();

		for (PropertyDescriptor propertyDesc: propertyData){
			Object srcValue = src.getPropertyValue(propertyDesc.getName());

			if(srcValue == null) {
				emptyNames.add(propertyDesc.getName());
			}
		}

		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}
}
