package model;

import java.util.concurrent.TimeUnit;

public class Elevator {
	
	private status status;
	private int currentFloor;
	private int number;

	public Elevator() {};
	
	public Elevator(status status, int currentFloor, int number) {
		super();
		this.status = status;
		this.currentFloor = currentFloor;
		this.number = number;
	}	
	
	public status getStatus() {
		return status;
	}
	public void setStatus(status status) {
		this.status = status;
	}
	public int getCurrentFloor() {
		return currentFloor;
	}
	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}
		
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	//function when elevator moves up  
	public void goUp(int destinationFloor) {
		while(getCurrentFloor()!=destinationFloor)  {
			setCurrentFloor(getCurrentFloor()+1);
			//simulation of time that elevator take to move
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e1)  {
				e1.printStackTrace();
			}
		}
	}
	
	//function when elevator moves down  
	public void goDown(int destinationFloor) {
		while(getCurrentFloor()!=destinationFloor)  {
			setCurrentFloor(getCurrentFloor()-1);
			//simulation of time that elevator take to move
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e1)  {
				e1.printStackTrace();
			}
		}
	}
	
}
