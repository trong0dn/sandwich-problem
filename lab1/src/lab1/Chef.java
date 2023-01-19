package lab1;

import java.util.List;

/**
 * Chef class represents a chef with only one final ingredient to make a sandwich along with two other ingredients on the table.
 * @author Trong Nguyen
 * @version 1.0
 */
public class Chef extends Thread {
	
	private String name;
	private String ingredient;
	private List<String> table;
	
	private final int MAX_SANDWICHES = 20;
	
	/**
	 * Constructor for Chef.
	 * @param name			name of the Chef Thread
	 * @param table			table where the ingredients will be placed to make the sandwich
	 * @param ingredient		one of the item necessary to form a complete sandwich
	 */
	public Chef(String name, List<String> table, String ingredient) {
		super(name);
		this.name = name;
		this.table = table;
		this.ingredient = ingredient;
	}
	
	/**
	 * Run method override from Threads
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (Kitchen.count < MAX_SANDWICHES) {
			getSandwich();
		}
		System.out.println("Program Completed.");
	}
	
	/**
	 * Makes a sandwich with all ingredients on the table and eats it.
	 */
	private synchronized Object getSandwich() {
		Object obj = table;
		for (String foodItem : table) {
			// Check if the table is empty or if chef already has that ingredient 
			while (table.isEmpty() || foodItem.equals(ingredient)) {
				try {
					System.out.println(name + " is waiting for ingredients.");
					wait();
				} catch (InterruptedException e) {
					System.err.println("ERROR: " + name);
					e.printStackTrace();
				}
			}
		}
		table.clear(); // Make and eat sandwich
		Kitchen.count++;
		System.out.println(name + " made a sandwich by adding " + ingredient + " to the table.");
		System.out.println(name + " made a total of " + Kitchen.count + " sandwiches.");
		notifyAll();
		return obj;
	}
	
}
