package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.checkAvailableElevator;
import model.ElevatorSystem;
import model.Request;
import model.direction;

public class call {

	public static void main(String[] args) throws Exception {

		// insert number of elevators and number of floors
		Scanner sc = new Scanner(System.in);

		System.out.print("Please insert number of elevetors: ");
		int number_of_elevators = sc.nextInt();

		System.out.print("Please insert number of floors: ");
		int number_of_floors = sc.nextInt();

		// validate numbers
		if (number_of_elevators <= 0 || number_of_floors <= 0)
			throw new Exception("Invalid numbers");

		// create new system of elevators
		ElevatorSystem systemElevator = new ElevatorSystem(number_of_floors, number_of_elevators);

		// create a few request to system
		systemElevator.addRequest(0, 3, direction.up);
		systemElevator.addRequest(2, 4, direction.up);
		systemElevator.addRequest(5, 1, direction.down);
		systemElevator.addRequest(3, 5, direction.up);

		// create more request

		systemElevator.addRequest(4, 1, direction.down);
		systemElevator.addRequest(3, 0, direction.down);
		systemElevator.addRequest(5, 0, direction.down);

		systemElevator.addRequest(2, 1, direction.down);
		systemElevator.addRequest(4, 2, direction.down);
		systemElevator.addRequest(1, 4, direction.up);

		// System.out.println("Finish main thread");

	}

}
