package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

import controller.checkAvailableElevator;

public class ElevatorSystem {	

	private List<Elevator> elevators = new ArrayList<Elevator>();
	private int numberFloors;
	private int numberElevators;
	private Queue<Request> requests = new ConcurrentLinkedQueue<Request>();
	private List<Thread> list = new ArrayList<Thread>();
	
	public List<Thread> getList() {
		return list;
	}
	public Queue<Request> getRequests() {
		return requests;
	}
	public List<Elevator> getElevators() {
		return elevators;
	}
	
	public ElevatorSystem(int numberFloors, int numberElevators) throws InterruptedException {
		super();
		this.numberFloors = numberFloors;
		this.numberElevators = numberElevators;
		setupSystem();
	}
	
	//when we create system by default all elevators are free and on 0(ground) floor
	private void setupSystem() throws InterruptedException {
		for (int i=1; i<=this.numberElevators; i++) {	
			Elevator e=new Elevator(status.free, 0, i);
			elevators.add(e);
			
			//create as many threads as number of elevators
			Thread t1 = new checkAvailableElevator(this);
			t1.start();
			//added sleep to avoid concurrency
			t1.sleep(100);
		}

	}
	
	public void addRequest(int currentFloor, int destinationFloor, direction direction) {
		Request request = new Request(currentFloor, destinationFloor, direction);
		requests.add(request);
	}
}
