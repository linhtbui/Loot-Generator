/**
 * Monster class for holding monsters
 * @author builinh & friedman2
 *
 */
public class Monster {

	public String name;
	public String type;
	public int level; 
	public String treasureClass;
	/**
	 * Constructor for generating a monster
	 * @param s, a line in text file
	 */
	public Monster(String s) {
		String[] input = s.split("\t");
		this.name = input[0];
		this.type = input[1];
		this.level = Integer.parseInt(input[2]);
		this.treasureClass = input[3];
	}
	/**
	 * 
	 * @return the monster's name, a string
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * 
	 * @return the name of the treasureClass, a string
	 */
	public String getTreasureClass(){
		return this.treasureClass;
	}
}
	
