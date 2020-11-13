package model;

public class Request {
	private int currentFloor;
	private int destinationFloor;
	private direction direction;
	
	public Request(int currentFloor, int destinationFloor, model.direction direction) {
		super();
		this.currentFloor = currentFloor;
		this.destinationFloor = destinationFloor;
		this.direction = direction;
	}
	
	public int getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(int currentFloor) throws Exception {
		if(currentFloor<0) throw new Exception ("Invalid number");
		this.currentFloor = currentFloor;
	}

	public int getDestinationFloor() {
		return destinationFloor;
	}

	public void setDestinationFloor(int destinationFloor) throws Exception {
		if(destinationFloor<0) throw new Exception ("Invalid number");
		this.destinationFloor = destinationFloor;
	}

	public direction getDirection() {
		return direction;
	}

	public void setDirection(direction direction) {
		this.direction = direction;
	}

	
}
