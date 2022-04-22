package com.quanlybanhang.utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class FormUtil {
	public static <T> T toModel(Class<T> myClass, HttpServletRequest request) {
		T object = null;
		try {
			object = myClass.newInstance();
			BeanUtils.populate(object, request.getParameterMap()); // chuyen cac key va value de map
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			System.out.print(e.getMessage());
		}
		return object;
	}
}
	