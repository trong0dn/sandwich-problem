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
	 * Main method to initialize the table along with ingredients and runs the program.
	 * @param args	default parameter
	 */
	public static void main(String[] args) {
		// Table object is shared by Agent and Chef
		List<String> table = Collections.synchronizedList(new ArrayList<String>());
		
		// Create the Agent and Chefs threads, passing each thread a reference to the shared Table object
		Thread agent = new Agent("Agent", table, "bread", "peanut butter", "jam");
		Thread chef1 = new Chef("Chef1", table, "bread");
		Thread chef2 = new Chef("Chef2", table, "peanut butter");
		Thread chef3 = new Chef("Chef3", table, "jam");
		
		System.out.println("Welcome to the Chef's Kitchen for Sandwiches...");
		agent.start();
		chef1.start();
		chef2.start();
		chef3.start();
	}

}
