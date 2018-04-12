import java.util.Random;

/**
 * Treasure class for holding treasures
 * @author builinh & friedman2
 *
 */
public class Treasure {
	
	public String name;
	public String[] items;
	
	/**
	 * Constructor to generate a new treasure
	 * @param s, a string of text file
	 */
	public Treasure(String s) {
		String[] input = s.split("\t");
		this.name = input[0];
		this.items = new String[3]; 
		for (int i = 0; i < 3; i++){
			items[i] = input[i +1];
		}
	}
	
	/**
	 * 
	 * @return item, a string of the name of the treasure
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Choose a random item in the list
	 * @return a string represent the item
	 */
	public String randomItem() {
		Random rand = new Random();
		return items[rand.nextInt(3)];
	}
	
}
