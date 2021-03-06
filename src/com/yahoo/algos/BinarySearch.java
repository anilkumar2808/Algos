package com.yahoo.algos;

import java.util.Arrays;

public class BinarySearch {

	/**
	 * Remember 2 things 
	 * one mid = low + (high-low)/2 and
	 * other while low<=high or if low>high return -1
	 */
	static int search = 45;
	static int[] array = {45,2,5,8,15,3,5,90,24,34,52,98};
	public static void main(String[] args) {
		Arrays.sort(array);
		Common.print(array);
		int index = bsearch2(0, array.length-1);
		 System.out.println(index +" " + ((index!=-1)? array[index]:-1) +"");
		int index2 = bsearch1(0, array.length-1);
		 System.out.println(index2 +" " + ((index2!=-1)? array[index2]:-1) +"");
	}
	
	private static int bsearch1(int low, int high){ //fails on last and first index
		if(low>high) return -1; //NOTE not low<=high
		int mid  = low + (high - low)/2;
		if(array[mid]==search){
			return mid;
		}
		if(search > array[mid]){
			return bsearch1(mid+1, high);
		}
		if(search < array[mid]){
			return bsearch1(low, mid-1);
		}
		
		return -1;
	}
	
	private static int bsearch2(int low , int high){
		
		while (low<=high){
			int mid = low + (high -low)/2;
			if(array[mid]==search){
				return mid;
			}
			if(search > array[mid]){
				low = mid+1;
			}
			if(search < array[mid]){
				high = mid-1;
			}
		}
		return -1;
	}
}
