package lab1;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Agent class represents an agent that randomly chooses two ingredients to be on the table.
 * @author Trong Nguyen
 * @version 1.0
 */
public class Agent extends Thread {
	private List<String> table;
	private String name;
	
	private String ingredient1;
	private String ingredient2;
	private String ingredient3;
	
	private final int MIN = 1;
	private final int MAX = 3;
	
	/**
	 * Constructor of Agent 
	 * @param name			name of the Agent Thread
	 * @param table			table where the ingredients will be placed to make the sandwich
	 * @param ingredient1	one of the item necessary to form a complete sandwich
	 * @param ingredient2	one of the item necessary to form a complete sandwich
	 * @param ingredient3	one of the item necessary to form a complete sandwich
	 */
	public Agent(String name, List<String> table, String ingredient1, String ingredient2, String ingredient3 ) {
		super(name);
		this.name = name;
		this.table = table;
		this.ingredient1 = ingredient1;
		this.ingredient2 = ingredient2;
		this.ingredient3 = ingredient3;
	}
	
	/**
	 * Run method override from Threads
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		while(true) {
			selectIngredient();
		}
	}
	
	/**
	 * Randomly selects 2 ingredients to put on the table.
	 */
	public void selectIngredient() {
		synchronized(this) {
			while(table.size() != 0) {
				try {
					System.out.println("Agent is waiting for empty table.");
					wait();
				} catch (InterruptedException e) {
					System.err.println("ERROR: Agent Thread: ");
					e.printStackTrace();
					return;
				}
			}
			int randomChoice = randomSelection();
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
				System.err.println("FAIL: Ingredients fell off the table!");
				break;
			}
			System.out.println(name + " has choosen 2 random ingredients: " + table.get(0) + " and " + table.get(1) + ".");
			notifyAll();
		}
	}
	
	/**
	 * Randomly chooses a number between a range.
	 * @return	random number within MIN and MIN range
	 */
	public int randomSelection() {
		return ThreadLocalRandom.current().nextInt(MIN, MAX + 1);
	}
}
