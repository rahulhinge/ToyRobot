package com.my;

import java.util.Scanner;

/**
 * Implementation of ToyRobot
 * 
 * @author Rahul
 *
 */
public class LaunchRobot {

	/**
	 * Command constants
	 */
	private final String COMMAND_MOVE = "MOVE";
	private final String COMMAND_LEFT = "LEFT";
	private final String COMMAND_RIGHT = "RIGHT";
	private final String COMMAND_REPORT = "REPORT";
	private final String COMMAND_PLACE = "PLACE";

	private final String SPACE = " ";

	/**
	 * Orientation constants
	 */
	private final String EAST = "EAST";
	private final String SOUTH = "SOUTH";
	private final String WEST = "WEST";
	private final String NORTH = "NORTH";

	/**
	 * Table space constantss
	 */
	private static int TABLE_LENGHT = 5;
	private static int TABLE_WIDTH = 5;

	/** Robot's current X co-ordinate **/
	int currentX = 0;
	/** Robot's current Y co-ordinate **/
	int currentY = 0;
	/** Robot's current orientation **/
	int currentDirection = 1;

	/** Flag for first 'PLACE' command **/
	boolean isPlaceIssued = false;

	/**
	 * Application launch method
	 * 
	 * @param args parameter to the applications
	 */
	public static void main(String[] args) {

		LaunchRobot robot = new LaunchRobot();
		robot.start();

	}

	/**
	 * Start method for the Robot which accepts user commands. To end the
	 * application please press ctrl + c
	 */
	public void start() {

		// Following code accepts the user command and pass it to appropriate function
		// for execution.
		while (true) {

			System.out.println("Please enter Command :");
			Scanner scan = new Scanner(System.in);
			String command = scan.nextLine().toUpperCase();

			if (command.startsWith(COMMAND_PLACE)) {

				String[] splitCommand = command.split(SPACE);
				if (splitCommand.length == 4) {
					int x = Integer.parseInt(splitCommand[1]);
					int y = Integer.parseInt(splitCommand[2]);
					String orientation = splitCommand[3];

					place(x, y, orientation);
					if (!isPlaceIssued) {
						isPlaceIssued = true;
					}
				}

			}
			if (command.equals(COMMAND_REPORT) && isPlaceIssued) {
				report();
			}
			if (command.equals(COMMAND_MOVE) && isPlaceIssued) {
				move();
			}
			if (command.equals(COMMAND_LEFT) && isPlaceIssued) {
				left();
			}
			if (command.equals(COMMAND_RIGHT) && isPlaceIssued) {
				right();
			}
			if (!isPlaceIssued) {
				System.out.println("First command must be 'PLACE X Y ORIENTATION' please...:");
			}

		}

	}

	/**
	 * Place the Robot at specified co-ordinates and orientations. This should be
	 * the first command before any other commands
	 * 
	 * @param x           X co-ordinate
	 * @param y           Y co-ordinate
	 * @param orientation Facing direction
	 */
	public void place(int x, int y, String orientation) {
		Direction direction = getDirectionFromString(orientation);
		int dirVal = direction.getValue();
		// Execute command only if x>=0, x<=4, y>=0, y<4 and valid orientation
		if ((x >= 0 && x <= (TABLE_LENGHT - 1)) && (y >= 0 && y <= (TABLE_WIDTH - 1))
				&& (dirVal == 1 || dirVal == 2 || dirVal == 3 || dirVal == 4)) {
			currentX = x;
			currentY = y;
			currentDirection = dirVal;
		} else {
			System.out.println("Invalid Placement X: " + x + " Y : " + y + " D: " + dirVal);
		}
	}

	/**
	 * Moves the robot forward in the direction of Orientation The command is ignore
	 * if the robot is going out of table bounds.s
	 */
	public void move() {

		int probableNewX;
		int probableNewY;
		// Depending on direction following code calculates the values of
		// currentX/currentY
		if (currentDirection == 1) { // Move EAST
			probableNewX = currentX + 1;
			if (probableNewX > TABLE_LENGHT - 1) {
				System.out.println("Not moving...I will fall down");

			} else {
				currentX = probableNewX;
			}
		}
		if (currentDirection == 3) { // Move WEST
			probableNewX = currentX - 1;
			if (probableNewX < 0) {
				System.out.println("Not moving...I will fall down");

			} else {
				currentX = probableNewX;
			}
		} else if (currentDirection == 2) { // Move SOUTH
			probableNewY = currentY - 1;
			if (probableNewY < 0) {
				System.out.println("Not moving...I will fall down");

			} else {
				currentY = probableNewY;
			}
		} else if (currentDirection == 4) { // Move NORTH
			probableNewY = currentY + 1;
			if (probableNewY > TABLE_WIDTH - 1) {
				System.out.println("Not moving...I will fall down");

			} else {
				currentY = probableNewY;
			}
		}
	}

	/**
	 * Turns the robot in counter clockwise direction.
	 */
	public void left() {

		// Calculate the new direction counter clockwise by 1 step. See the @Direction
		// enum for values
		currentDirection -= 1;
		if (currentDirection < 1) {
			currentDirection = 4;
		}

	}

	/**
	 * Turns the robot in clockwise direction.
	 */
	public void right() {

		// Calculate the new direction clockwise by 1 step. See the @Direction enum for
		// values
		currentDirection += 1;
		if (currentDirection > 4) {
			currentDirection = 1;
		}

	}

	/**
	 * Prints and returns the current position and orientation of the robot.
	 * 
	 * @return The location of the robot.
	 */
	public String report() {

		Direction direction = getDirectionFromVal(currentDirection);
		// Prints and returns the robots current location and orientation
		String report = "report => " + currentX + " " + currentY + " " + direction;
		System.out.println(report + "\n\r");
		return report;

	}

	/**
	 * Getter for currentX
	 * 
	 * @return currentX
	 */
	public int getCurrentX() {
		return currentX;
	}

	/**
	 * Getter for currentY
	 * 
	 * @return currentX
	 */
	public int getCurrentY() {
		return currentY;
	}

	/**
	 * Getter for currentDirection
	 * 
	 * @return currentDirection
	 */
	public int getCurrentDirection() {
		return currentDirection;
	}

	private Direction getDirectionFromString(String orientation) {

		Direction direction = null;

		switch (orientation.toUpperCase()) {
		case EAST:
			direction = Direction.EAST;
			break;
		case SOUTH:
			direction = Direction.SOUTH;
			break;
		case WEST:
			direction = Direction.WEST;
			break;
		case NORTH:
			direction = Direction.NORTH;
			break;
		}

		return direction;
	}

	private Direction getDirectionFromVal(int value) {

		Direction direction = null;

		switch (value) {
		case 1:
			direction = Direction.EAST;
			break;
		case 2:
			direction = Direction.SOUTH;
			break;
		case 3:
			direction = Direction.WEST;
			break;
		case 4:
			direction = Direction.NORTH;
			break;
		}

		return direction;
	}

	private enum Direction {

		EAST(1), SOUTH(2), WEST(3), NORTH(4);

		int val;

		private Direction(int val) {
			this.val = val;
		}

		public int getValue() {
			return val;
		}
	}

}
