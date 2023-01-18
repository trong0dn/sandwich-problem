package lab1;

import java.util.List;

/**
 * Chef class represents a chef with only one final ingredient to make a sandwich with two ingredients on the table.
 * @author Trong Nguyen
 * @version 1.0
 */
public class Chef extends Thread {
	private List<String> table;
	
	private String name;
	private String ingredient;
	
	private final int MAX_SANDWICHES = 100;
	
	/**
	 * Constructor for Chef
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
	public void run() {
		while (Kitchen.count < MAX_SANDWICHES) {
			makeSandwich();
		}
	}
	
	/**
	 * Makes a sandwich with all ingredients on the table and eats it.
	 */
	public void makeSandwich() {
		synchronized(this) {
			for (String foodItem : table) {
				while (table.size() == 0 || foodItem.equals(ingredient)) {
					try {
						System.out.println("Chef is waiting for ingredients.");
						wait();
					} catch (InterruptedException e) {
						System.err.println("ERROR: Chef Thread: ");
						e.printStackTrace();
						return;
					}
				}
			}
			table.clear(); // Make and eat sandwich
			Kitchen.count++;
			System.out.println(name + " made a sandwich by adding " + ingredient + " to the table.");
			System.out.println(name + " made a total of " + Kitchen.count + " sandwiches.");
			notifyAll();
		}
	}
}
