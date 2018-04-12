import java.util.Random;

/**
 * Suffix class for holding suffixes
 * @author builinh & friedman2
 *
 */
public class Suffix {
	public String name;
	public String mod1code;
	public int mod1min;
	public int mod1max;

	/**
	 * Constructor to generate a new suffix
	 * @param s, a string of text file
	 */
	public Suffix(String s) {
		String[] input = s.split("\t");
		this.name = input[0];
		this.mod1code = input[1];
		this.mod1min = Integer.parseInt(input[2]);
		this.mod1max = Integer.parseInt(input[3]);
	}

	/**
	 * 
	 * @return get the name of the suffix
	 */
	public String getSuffix() {
		return this.name;

	}

	/**
	 * 
	 * @return get the Mod1Code of the suffix
	 */
	public String getMod1Code() {
		return this.mod1code;
	}
	
	/**
	 * 
	 * @return stat, an int representing a randomized stat in range
	 */
	public int statistic() {
		Random rand = new Random();
		int diff = this.mod1max -this.mod1min; 
		if (diff > 0){
			int ret = rand.nextInt(diff); 
			return this.mod1min + ret; 
		} else {
			return this.mod1min; 
		}
	}
}
