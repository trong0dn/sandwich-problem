package lab1;

import java.util.List;

/**
 * Agent class represents an agent that randomly chooses two ingredients to be placed on the table.
 * @author Trong Nguyen
 * @version 1.0
 */
public class Agent extends Thread {
	
	private String name;
	private List<String> table;
	
	private String ingredient1;
	private String ingredient2;
	private String ingredient3;
	
	private final int MIN_RANGE = 1;
	private final int MAX_RANGE = 3;
	
	/**
	 * Constructor of Agent.
	 * @param name			name of the Agent Thread
	 * @param table			table where the ingredients will be placed to make the sandwich
	 * @param ingredient1	one of the item necessary to form a complete sandwich
	 * @param ingredient2	one of the item necessary to form a complete sandwich
	 * @param ingredient3	one of the item necessary to form a complete sandwich
	 */
	public Agent(String name, List<String> table, String ingredient1, String ingredient2, String ingredient3) {
		super(name);
		this.name = name;
		this.table = table;
		this.ingredient1 = ingredient1;
		this.ingredient2 = ingredient2;
		this.ingredient3 = ingredient3;
	}
	
	/**
	 * Run method override from Thread class
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while(true) {
			putIngredients(table);
		}
	}
	
	/**
	 * Randomly selects 2 ingredients to put on the table.
	 */
	private synchronized boolean putIngredients(List<String> table) {
		// Check whether the table is full before adding ingredients
		while (!(table.isEmpty())) {
			try {
				System.out.println(name + " is waiting for the table to be empty.");
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); 
				System.err.println("ERROR: " + name);
				e.printStackTrace();
			}
		}
		int randomChoice = randomSelection(MIN_RANGE, MAX_RANGE);
		switch(randomChoice) {
		case 1:
			table.add(ingredient1);
			table.add(ingredient2);
			break;
		case 2:
			table.add(ingredient2);
			table.add(ingredient3);
			break;
		case 3:
			table.add(ingredient1);
			table.add(ingredient3);
			break;
		default:
			System.err.println("ERROR: Ingredients were not selected");
			break;
		}
		System.out.println(name + " has chosen 2 random ingredients.");
		notifyAll();
		return true;
	}
	
	/**
	 * Randomly chooses a number between a range.
	 * @return	random integer within minimum and maximum range.
	 */
	public int randomSelection(int min, int max) {
		int range = (max - min) + 1;
		return (int)(Math.random() * range) + min;
	}
	
}