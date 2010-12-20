package com.stacktraceinc.hypercut2d;

import java.util.Comparator;

public class PartComparator implements Comparator<Part>{
	@Override
	public int compare(Part p1, Part p2){
		if (p1.getArea() < p2.getArea()){
			return 1;
		}else if (p1.getArea() == p2.getArea()){
			return 0;
		}else{
			return -1;
		}
	}
}