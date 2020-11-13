package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

import model.ElevatorSystem;
import model.direction;

//case where we have less requests then elevators

class test2 {

	@Test
	void test() throws Exception {
		int number_of_elevators = 4;
		int number_of_floors = 5;
		// create new system of elevators
		ElevatorSystem systemElevator = new ElevatorSystem(number_of_floors, number_of_elevators);

		// create a few request to system
		systemElevator.addRequest(0, 3, direction.up);
		systemElevator.addRequest(4, 1, direction.down);
		systemElevator.addRequest(3, 0, direction.down);

		// System.out.println("Finish main thread");

		Thread.sleep(10000);
	}

}
