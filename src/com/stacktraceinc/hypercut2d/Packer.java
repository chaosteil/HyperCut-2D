package com.stacktraceinc.hypercut2d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Packer{

	private class PackerBox{
		public int sLeft, sTop, sRight, sBottom;

		PackerBox(int left, int top, int right, int bottom){
			this.sLeft = left;
			this.sTop = top;
			this.sRight = right;
			this.sBottom = bottom;
		}

		public int getWidth(){
			return this.sRight - this.sLeft;
		}

		public int getHeight(){
			return this.sBottom - this.sTop;
		}

		public int getSize(){
			return this.getWidth() * this.getHeight();
		}
	}

	private class PackerNode{
		public PackerNode[] child;
		public PackerBox pb;
		public Coordinate coord;
		public Part rect;
		
		PackerNode(){
			System.out.println("newNode");
			this.child = new PackerNode[2];
			this.child[0] = null;
			this.child[1] = null;
			this.rect = null;
			this.coord = new Coordinate(0, 0);
		}
		
		private PackerNode insert(Part rect){
			int rectWidth = 0; 
			int rectHeight = 0;
			
			if(rect.rotation()){
				rectWidth = rect.getSecondValue();
				rectHeight = rect.getFirstValue();
			}else{
				rectWidth = rect.getFirstValue();
				rectHeight = rect.getSecondValue();
			}
			
			System.out.println("New insert call");
			if(this.child[0] != null && this.child[1] != null){
				System.out.println("Not a leaf. ");
				PackerNode newNode = this.child[0].insert(rect);
				
				if(newNode != null){
					System.out.println("newNode != null ");
					return newNode;
				}
				
				return this.child[1].insert(rect);
			
			}else{
				System.out.println("A leaf. ");

				if(this.rect != null){
					System.out.println("Already has a shape. ");
					return null;
				}

				if(this.pb.getSize() < rect.getArea()){
					System.out.println("Shape too big");
					rect.setCoordinate(new Coordinate(-1, -1));
					return null;
				}

				System.out.println(this.pb.getWidth() + " w " + rectWidth);
				System.out.println(this.pb.getHeight() + " h " + rectHeight);
				
				
				
				if((this.pb.getWidth() == rectWidth) 
				&& (this.pb.getHeight() == rectHeight) ){
					this.rect = rect;
					rect.setCoordinate(this.coord);
					System.out.println("inserting at: " + this.coord.getX() + " " + this.coord.getY() + " " + " ");
					return this;	
				}

				this.child[0] = new PackerNode();
				this.child[1] = new PackerNode();

				int difWidth = Math.abs(pb.getWidth() - rectWidth);
				int difHeight = Math.abs(pb.getHeight() - rectHeight);

				if(difWidth > difHeight){
					System.out.println("W > H ");
					this.child[0].coord = new Coordinate(this.coord.getX(), this.coord.getY());
					this.child[1].coord = new Coordinate(this.coord.getX() + rectWidth, this.coord.getY());
					this.child[0].pb = new PackerBox(this.pb.sLeft, this.pb.sTop, this.pb.sLeft + rectWidth, this.pb.sBottom);
					this.child[1].pb = new PackerBox(this.pb.sLeft + rectWidth, this.pb.sTop, this.pb.sRight, this.pb.sBottom);
				}else{
					System.out.println("H > W "); 
					this.child[0].coord = new Coordinate(this.coord.getX(), this.coord.getY());
					this.child[1].coord = new Coordinate(this.coord.getX(), this.coord.getY() + rectHeight);
					this.child[0].pb = new PackerBox(this.pb.sLeft, this.pb.sTop, this.pb.sRight, this.pb.sTop + rectHeight);
					this.child[1].pb = new PackerBox(this.pb.sLeft, this.pb.sTop + rectHeight, this.pb.sRight, this.pb.sBottom);
				}

				return this.child[0].insert(rect);
			}
		}
	}
	
	private PackerNode root;

	private void clearTree(){
		root.child[0] = null;
		root.child[1] = null;
	}
	
	private List<Part> sortList(List<Part> list){
		List<Part> sortedList = new ArrayList<Part>();
		
		for(Part part : list){
			sortedList.add(part.clone());
		}
		
		Collections.sort(sortedList, new PartComparator());
		return sortedList;
	}
	
	Packer(int width, int height){
		root = new PackerNode();
		root.pb = new PackerBox(0, 0, width, height);
	}
	
	public void resize(int width, int height){
		root.pb = new PackerBox(0, 0, width, height);
	}

	public List<Part> pack(List<Part> rectangles){
		this.clearTree();
		List<Part> list = sortList(rectangles);
		
		for (int i = 0; i < list.size(); i++){
			this.root.insert(list.get(i));			
		}
		
		return list;
	}		
}