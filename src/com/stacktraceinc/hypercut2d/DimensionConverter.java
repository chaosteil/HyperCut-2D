package com.stacktraceinc.hypercut2d;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdesktop.beansbinding.Converter;

public class DimensionConverter extends Converter<List<Dimension>, List> {

    public List convertForward(List<Dimension> arg) {
    	List list = new ArrayList();
    	for (Dimension d : arg){
    		list.add(getDimensionString(d));
    	}
    	return list;
    }

    public List<Dimension> convertReverse(List arg) {
    	// TODO: Implement this. Might not be needed however.
    	return new ArrayList<Dimension>();
    }

    private String getDimensionString(Dimension arg) {
    	 return new StringBuilder().append(arg.getWidth()).append(" mm x ").append(arg.getHeight()).append(" mm").toString();
    }
}