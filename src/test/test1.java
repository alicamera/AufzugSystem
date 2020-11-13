package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import controller.checkAvailableElevator;
import model.ElevatorSystem;
import model.direction;

//case where we have more requests then elevators or threads

public class test1 {

	@Test
	public void test() throws Exception {

		int number_of_elevators = 4;
		int number_of_floors = 5;

		// create new system of elevators
		ElevatorSystem systemElevator = new ElevatorSystem(number_of_floors, number_of_elevators);

		// create a few request to system
		systemElevator.addRequest(0, 3, direction.up);
		systemElevator.addRequest(2, 4, direction.up);
		systemElevator.addRequest(5, 1, direction.down);
		systemElevator.addRequest(3, 5, direction.up);

		systemElevator.addRequest(4, 1, direction.down);
		systemElevator.addRequest(3, 0, direction.down);
		systemElevator.addRequest(5, 0, direction.down);

		systemElevator.addRequest(2, 1, direction.down);
		systemElevator.addRequest(4, 2, direction.down);
		systemElevator.addRequest(1, 4, direction.up);

		// System.out.println("Finish main thread");

		Thread.sleep(10000);
	}

}
