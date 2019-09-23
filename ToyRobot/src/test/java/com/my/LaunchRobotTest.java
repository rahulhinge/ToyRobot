package com.my;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for LaunchRobot
 * @author Rahul
 *
 */
class LaunchRobotTest {
	
	/** Class under test **/
	private LaunchRobot classUnderTest;
	
	@BeforeEach
	public void init() {
		classUnderTest = new LaunchRobot();
		
	}

	/**
	 * Test the place method happy path
	 */
	@Test
	void testPlace_happy() {
		//Method under test invoked
		classUnderTest.place(1, 2, "north");
		assertEquals("Verifying X", 1, classUnderTest.getCurrentX());
		assertEquals("Verifying Y", 2, classUnderTest.getCurrentY());
		assertEquals("Verifying Orientation", 4, classUnderTest.getCurrentDirection());

	}

	/**
	 * Test the move method happy path
	 */
	@Test
	void testMove_happy() {
		classUnderTest.place(1, 2, "north");
		//Method under test invoked
		classUnderTest.move();
		assertEquals("Verifying X", 1, classUnderTest.getCurrentX());
		assertEquals("Verifying Y", 3, classUnderTest.getCurrentY());
		assertEquals("Verifying Orientation", 4, classUnderTest.getCurrentDirection());
		
	}

	/**
	 * Test the left method happy path
	 */
	@Test
	void testLeft_happy() {
		classUnderTest.place(1, 2, "north");
		//Method under test invoked
		classUnderTest.left();
		assertEquals("Verifying X", 1, classUnderTest.getCurrentX());
		assertEquals("Verifying Y", 2, classUnderTest.getCurrentY());
		assertEquals("Verifying Orientation", 3, classUnderTest.getCurrentDirection());
	}

	/**
	 * Test right method happy path
	 */
	@Test
	void testRight_happy() {
		classUnderTest.place(1, 2, "north");
		//Method under test invoked
		classUnderTest.right();
		assertEquals("Verifying X", 1, classUnderTest.getCurrentX());
		assertEquals("Verifying Y", 2, classUnderTest.getCurrentY());
		assertEquals("Verifying Orientation", 1, classUnderTest.getCurrentDirection());
	}

	/**
	 * Test report method happy path
	 */
	@Test
	void testReport_happy() {
		
		classUnderTest.place(1, 2, "north");
		//Method under test invoked
		assertEquals("Verifying Report", "report => 1 2 NORTH", classUnderTest.report());
		
	}

}
