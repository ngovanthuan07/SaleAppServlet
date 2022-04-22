package com.quanlybanhang.converts.array;

public class NewArray {
	private static NewArray newArray;

	public static NewArray getInstance() {
		if (newArray == null) {
			newArray = new NewArray();
		}
		return newArray;
	}

	public Long[] toArrayLong(Long arr[], Long obj) {
		Long newArr[] =  new Long[arr.length + 1];	
		for (int i = 0; i < arr.length; i++) {
			newArr[i] = arr[i];
		}
		newArr[arr.length]  = obj;
		return newArr;
	}
}
