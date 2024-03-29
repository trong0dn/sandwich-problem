package lab1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Kitchen class holds the table on which the sandwich is made.
 * @author Trong Nguyen
 * @version 1.0
 */
public class Kitchen {
	
	public static int count = 0;
	
	/**
	 * Main method to runs the program.
	 * @param args	default parameter
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			Kitchen kitchen = new Kitchen();
			kitchen.makeSandwich();
		}
	}
	
	/**
	 * Method to initialize the table along with ingredients and the chef.
	 */
	private void makeSandwich() {
		// Table object is shared by Agent and Chef
		List<String> table = Collections.synchronizedList(new ArrayList<String>());
		
		// Create the Agent and Chefs threads, passing each thread a reference to the shared Table object
		Thread agent = new Agent("AGENT", table, "BREAD", "PEANUT BUTTER", "JAM");
		Thread chef1 = new Chef("CHEF-1", table, "BREAD");
		Thread chef2 = new Chef("CHEF-2", table, "PEANUT BUTTER");
		Thread chef3 = new Chef("CHEF-3", table, "JAM");
		
		System.out.println("Welcome to the Chef's Kitchen for Sandwiches...");
		agent.start();
		chef1.start();
		chef2.start();
		chef3.start();
	}

}
