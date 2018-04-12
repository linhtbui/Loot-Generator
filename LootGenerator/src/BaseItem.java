import java.util.Random;

/**
 * Base Item class for holding armors
 * @author builinh & friedman2
 *
 */
public class BaseItem {

	public String item;
	public int minac; 
	public int maxac;
	
	/**
	 * Constructor to create a new BaseItem 
	 * @param s, a string
	 */
	public BaseItem(String s){
		String[] input = s.split("\t");
		this.item = input[0];
		this.minac = Integer.parseInt(input[1]);
		this.maxac = Integer.parseInt(input[2]);
	}
	
	/**
	 * 
	 * @return item, a string of the name of the item
	 */
	public String getName() {
		return this.item;
	}
	/**
	 * Generate a random number for stat inside range indicated
	 * @return defense stat, an int
	 */
	public int getDefense() {
		Random rand = new Random(); 
		int ret = rand.nextInt(this.maxac - this.minac);
		return this.minac + ret; 
	}
}
