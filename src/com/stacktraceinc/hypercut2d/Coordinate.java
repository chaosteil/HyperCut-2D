package com.stacktraceinc.hypercut2d;

class Coordinate{
	private int x;
	private int y;

	Coordinate(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}

	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}

	public boolean compare(Coordinate coord){
		if((this.x == coord.getX()) && (this.y == coord.getY())){
			return true;
		}else{
			return false;
		}
	}
}