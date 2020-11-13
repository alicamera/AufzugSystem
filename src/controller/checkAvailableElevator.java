package controller;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import model.ElevatorSystem;
import model.Request;
import model.direction;
import model.status;

public class checkAvailableElevator extends Thread {

	ElevatorSystem es;

	public checkAvailableElevator(ElevatorSystem systemElevator) {
		this.es = systemElevator;
	}

	public String requestToString() {
		String s = "";
		for (Request r : es.getRequests()) {
			s += "\n From " + r.getCurrentFloor() + " To " + r.getDestinationFloor() + " Floor. Direction "
					+ r.getDirection();
		}
		return s;
	}

	@Override
	public void run() {
		while (es.getRequests().size() == 0) {
			try {
				//if there is no requests wait
				sleep(1000);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		for (int i = 0; i < es.getRequests().size() + 1; i++) {
			// after a thread takes a request it is removed from the list, so that next
			// thread does not takes the same
			Request request = es.getRequests().peek();
			Request tmp = es.getRequests().poll();
			int destinationFloor = request.getDestinationFloor();

			// goes through all elevator to find a free one
			for (int j = 0; j < es.getElevators().size(); j++) {
				if (es.getElevators().get(j).getStatus().equals(status.free)) {

					// after we find a free elevator we check the direction from request
					// case 1: we want to go up
					if (request.getDirection().equals(direction.up)) {

						// we found a free elevator which can process the request
						// so we mark that elevator as taken
						es.getElevators().get(j).setStatus(status.taken);

						// when we go up we have 3 scenarios
						// case 1: elevator is on the same floor as the current floor of request
						if (es.getElevators().get(j).getCurrentFloor() == request.getCurrentFloor()) {
							//System.out.println("Case: Elevator is on my floor! Just go up!");
							System.out.println("Elevator number: " + es.getElevators().get(j).getNumber()
									+ "! DC Tower Zutritt zum " + destinationFloor + " stock");
							es.getElevators().get(j).goUp(destinationFloor);
						}

						// case 2: elevator is on the floor under the current floor of request
						else if (es.getElevators().get(j).getCurrentFloor() < request.getCurrentFloor()) {
							//System.out.println("Case: Elevator is under me! It first comes up to my floor and then goes up again!");
							System.out.println("Elevator number: " + es.getElevators().get(j).getNumber()
									+ "! DC Tower Zutritt zum " + request.getCurrentFloor() + " stock");
							es.getElevators().get(j).goUp(request.getCurrentFloor());

							System.out.println("Elevator number: " + es.getElevators().get(j).getNumber()
									+ "! DC Tower Zutritt zum " + destinationFloor + " stock");
							es.getElevators().get(j).goUp(destinationFloor);
						}

						// case 3: elevator is on the floor above the current floor of request
						else {
							//System.out.println("Case: Elevator is on floor above me! It first goes down and then up!");
							System.out.println("Elevator number: " + es.getElevators().get(j).getNumber()
									+ "! Rückfahrt vom " + es.getElevators().get(j).getCurrentFloor() + " Stock ins "
									+ request.getCurrentFloor());
							es.getElevators().get(j).goDown(request.getCurrentFloor());
							System.out.println("Elevator number: " + es.getElevators().get(j).getNumber()
									+ "! DC Tower Zutritt zum " + destinationFloor + " stock!");
							es.getElevators().get(j).goUp(destinationFloor);
						}

						// after elevator comes to destination floor, it processed the request
						// so it free again
						es.getElevators().get(j).setStatus(status.free);
						System.out.println("Elevator number: " + es.getElevators().get(j).getNumber()+" is free now!");
						break;
					}
					// case 1: we want to go down
					else if (request.getDirection().equals(direction.down)) {
						// mark the elevator as taken
						es.getElevators().get(j).setStatus(status.taken);

						// we also have 3 senarios
						// case 1: elevator is on the same floor as the current floor of request
						if (es.getElevators().get(j).getCurrentFloor() == request.getCurrentFloor()) {
							//System.out.println("Case: Elevator is on my floor! Just go down!");
							System.out.println("Elevator number: " + es.getElevators().get(j).getNumber()
									+ "! Rückfahrt vom " + es.getElevators().get(j).getCurrentFloor() + " Stock ins "
									+ destinationFloor);
							es.getElevators().get(j).goDown(destinationFloor);
						}

						// case 2: elevator is on the floor above the current floor of request
						else if (es.getElevators().get(j).getCurrentFloor() > request.getCurrentFloor()) {
							//System.out.println("Case: Elevator is above me! It first comes down to my floor and then down again");
							System.out.println("Elevator number: " + es.getElevators().get(j).getNumber()
									+ "! Rückfahrt vom " + es.getElevators().get(j).getCurrentFloor() + " Stock ins "
									+ request.getCurrentFloor());
							es.getElevators().get(j).goDown(request.getCurrentFloor());
							System.out.println("Elevator number: " + es.getElevators().get(j).getNumber()
									+ "! Rückfahrt vom " + es.getElevators().get(j).getCurrentFloor() + " Stock ins "
									+ destinationFloor);
							es.getElevators().get(j).goDown(destinationFloor);
						}

						// case 3: elevator is on the floor under the current floor of request
						else {
							//System.out.println("Case: Elevator is under me! It first comes up to my floor and then down!");
							System.out.println("Elevator number: " + es.getElevators().get(j).getNumber()
									+ "! DC Tower Zutritt zum " + request.getCurrentFloor() + " stock");
							es.getElevators().get(j).goUp(request.getCurrentFloor());

							System.out.println("Elevator number: " + es.getElevators().get(j).getNumber()
									+ "! Rückfahrt vom " + es.getElevators().get(j).getCurrentFloor() + " Stock ins "
									+ destinationFloor);
							es.getElevators().get(j).goDown(destinationFloor);
						}

						// after the elevator reaches the destination floor it free again
						es.getElevators().get(j).setStatus(status.free);
						System.out.println("Elevator number: " + es.getElevators().get(j).getNumber()+" is free now!");
						break;
					}

				}

			}

		}

	}

}
