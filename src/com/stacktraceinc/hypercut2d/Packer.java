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
				
				System.out.println(this.pb.getWidth() + " w " + rect.getFirstValue());
				System.out.println(this.pb.getHeight() + " h " + rect.getSecondValue());
				
				if(rect.isRotated() == false){
					if((pb.getWidth() > pb.getHeight()) && (rect.getFirstValue() < rect.getSecondValue())){
						System.out.println("rotating 1");
						rect.rotate();
					}else if((pb.getWidth() < pb.getHeight()) && (rect.getFirstValue() > rect.getSecondValue())){
						System.out.println("rotating 2");
						rect.rotate();
					}
				}
				
				if(this.pb.getSize() < rect.getArea() || pb.getWidth() < rect.getFirstValue() || pb.getHeight() < rect.getSecondValue()){
					System.out.println("Shape too big");
					rect.setCoordinate(new Coordinate(-1, -1));
					return null;
				}
			
				if((this.pb.getWidth() == rect.getFirstValue()) 
				&& (this.pb.getHeight() == rect.getSecondValue()) ){
					this.rect = rect;
					rect.setCoordinate(this.coord);
					System.out.println("inserting at: " + this.coord.getX() + " " + this.coord.getY() + " " + " ");
					return this;	
				}			
				
				this.child[0] = new PackerNode();
				this.child[1] = new PackerNode();

				int difWidth = Math.abs(pb.getWidth() - rect.getFirstValue());
				int difHeight = Math.abs(pb.getHeight() - rect.getSecondValue());

				if(difWidth > difHeight){
					System.out.println("W > H ");
					this.child[0].coord = new Coordinate(this.coord.getX(), this.coord.getY());
					this.child[1].coord = new Coordinate(this.coord.getX() + rect.getFirstValue(), this.coord.getY());
					this.child[0].pb = new PackerBox(this.pb.sLeft, this.pb.sTop, this.pb.sLeft + rect.getFirstValue(), this.pb.sBottom);
					this.child[1].pb = new PackerBox(this.pb.sLeft + rect.getFirstValue(), this.pb.sTop, this.pb.sRight, this.pb.sBottom);
				}else{
					System.out.println("H > W "); 
					this.child[0].coord = new Coordinate(this.coord.getX(), this.coord.getY());
					this.child[1].coord = new Coordinate(this.coord.getX(), this.coord.getY() + rect.getSecondValue());
					this.child[0].pb = new PackerBox(this.pb.sLeft, this.pb.sTop, this.pb.sRight, this.pb.sTop + rect.getSecondValue());
					this.child[1].pb = new PackerBox(this.pb.sLeft, this.pb.sTop + rect.getSecondValue(), this.pb.sRight, this.pb.sBottom);
				}

				return this.child[0].insert(rect);
			}
		}
	}
	
	private PackerNode root;

	private void clearTree(){
		root.rect = null;
		root.child[0] = null;
		root.child[1] = null;
	}
	
	private List<Part> sortList(List<Part> list){
		List<Part> sortedList = new ArrayList<Part>();
		
		for(Part part : list){
			sortedList.add(part.clone());
		}
		
		for(Part part : sortedList){
			part.setFirstValue(part.getFirstValue() + 4);
			part.setSecondValue(part.getSecondValue() + 4);
		}
		
		Collections.sort(sortedList, new PartComparator());
		return sortedList;
	}
	
	Packer(int width, int height){
		root = new PackerNode();
		root.pb = new PackerBox(0, 0, width + 4, height + 4);
	}
	
	public void resize(int width, int height){
		root.pb = new PackerBox(0, 0, width + 4, height + 4);
	}

	public List<Part> pack(List<Part> rectangles){
		this.clearTree();
		List<Part> list = sortList(rectangles);
		
		for (int i = 0; i < list.size(); i++){
			this.root.insert(list.get(i));			
		}
		
		for(Part part : list){
			part.setFirstValue(part.getFirstValue() - 4);
			part.setSecondValue(part.getSecondValue() - 4);
		}
				
		return list;
	}		
}
