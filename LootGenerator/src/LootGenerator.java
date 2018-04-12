import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;


/**
 * The driver for the loot generator program 
 * @author builinh & friedman2
 *
 */
public class LootGenerator {

	public static Scanner in; 
	
	/**
	 * Create an ArrayList of monsters from the text file
	 * @param file, the text file of monsters
	 * @return ret, a ArrayList of monsters
	 * @throws FileNotFoundException
	 */
	public static List<Monster> getMonsters(File file) throws FileNotFoundException {
		Scanner in = new Scanner(file); 
		List<Monster> ret = new ArrayList<Monster>();
		while(in.hasNext()){
			ret.add(new Monster(in.nextLine()));
		}
		in.close();
		return ret;
	}
	/**
	 * Create a HashMap of treasures from the text file
	 * @param file, the text file of treasures
	 * @return ret, a HashMap of treasures
	 * @throws FileNotFoundException
	 */
	public static Map<Treasure, Integer> getTreasureClass(File file) throws FileNotFoundException {
		Scanner in = new Scanner(file);
		Map<Treasure, Integer> ret = new HashMap<>();
		int n = 0;
		while(in.hasNext()){
			ret.put(new Treasure(in.nextLine()), n);
			n++;
		}
		in.close();
		return ret;	
	}

	/**
	 * Return a random prefix from the text file
	 * @param file, the text file of prefixes
	 * @return ret, a Prefix
	 * @throws FileNotFoundException
	 */
	public static Prefix getPrefix(File file) throws FileNotFoundException{
		Scanner in = new Scanner(file);
		List<Prefix> arr = new ArrayList<>();
		Random rand = new Random();
		while(in.hasNext()) {
			Prefix p = new Prefix(in.nextLine());
			arr.add(p);
		}
		Prefix ret = arr.get(rand.nextInt(arr.size()));
		in.close();
		return ret;
	}

	/**
	 * Return a random suffix from the text file
	 * @param file, the text file of suffixes
	 * @return ret, a Suffix
	 * @throws FileNotFoundException
	 */
	public static Suffix getSuffix(File file) throws FileNotFoundException{
		Scanner in = new Scanner(file);
		List<Suffix> arr = new ArrayList<>();
		Random rand = new Random();
		while(in.hasNext()) {
			Suffix s = new Suffix(in.nextLine());
			arr.add(s);
		}
		Suffix ret = arr.get(rand.nextInt(arr.size()));
		in.close();
		return ret;
	}

	/**
	 * Create a HashMap of armors from the text file
	 * @param file, the text file of armors
	 * @return ret, a HashMap of armors
	 * @throws FileNotFoundException
	 */
	public static Map<BaseItem, Integer> getArmors(File file) throws FileNotFoundException{
		Scanner in = new Scanner(file);
		Map<BaseItem, Integer> ret = new HashMap<>();
		int n = 0;
		while(in.hasNext()){
			ret.put(new BaseItem(in.nextLine()), n);
			n++;
		}
		in.close();
		return ret;
	}
	/**
	 * Generate a random monster from the list
	 * @param monsters, an ArrayList of monsters
	 * @return cur, a random Monster
	 */
	public static Monster pickMonster(List<Monster> monsters){
		Random rand = new Random();
		return monsters.get(rand.nextInt(monsters.size()));
	}
	
	/**
	 * Find a treasure corresponding to the monster
	 * @param monster, a Monster; classes, a map
	 * @return cur, a Treasure
	 */
	public static Treasure fetchTreasureClass(Monster monster, Map<Treasure, Integer> classes){
		String name =  monster.getTreasureClass();
		for(Treasure cur : classes.keySet()){
			if(cur.getName().equals(name)){
				return cur;
			}
		}
		return null;
	}
	
	/**
	 * Generate a base item (armor) for the program 
	 * @param treasure, a Treasure
	 * @param itemList, a map of Base Items
	 * @param treasureList, a map of treasures
	 * @return cur, a Base Item
	 */
	public static BaseItem generateBaseItem(Treasure treasure, Map<BaseItem, Integer> itemList, Map<Treasure, Integer> treasureList){
		String name = treasure.randomItem();
		//if treasure is already a base item
		for (BaseItem cur : itemList.keySet()) {
			if(cur.getName().equals(name)){
				return cur;
			}
		}
		//if treasure generated is a class of treasure
		for (Treasure curTreasure : treasureList.keySet()){
			if(curTreasure.getName().equals(name)){
				return generateBaseItem(curTreasure, itemList, treasureList);
			}
		}
		return null;
	}
	
	/**
	 * Generate stat for base item
	 * @param baseItem, a BaseItem
	 * @return ret, a string
	 */
	public static String generateBaseStat(BaseItem baseItem) {
		String ret = "Defense: " + baseItem.getDefense();
		return ret;
	}
	
	/**
	 * Generate prefixes and suffixes for the item dropped with 50% for each category.
	 * @param baseItem, a BaseItem
	 * @return ret, a String
	 * @throws FileNotFoundException
	 */
	public static String generateAffixAndStats(BaseItem baseItem) throws FileNotFoundException {
		String ret;
		Random randPre = new Random();
		Random randSuf = new Random();
		int randNumPre = randPre.nextInt(2);
		int randNumSuf = randSuf.nextInt(2);
		//50% chance of prefix
		if (randNumPre == 0 && randNumSuf == 1) {
			File prefixFile = new File("src/data/large/MagicPrefix.txt");
			Prefix pre = getPrefix(prefixFile);
			ret = pre.getPrefix() + " " + baseItem.getName() + "\n" + generateBaseStat(baseItem)
				+ "\n" + pre.statistic() + " " + pre.getMod1Code();
			return ret;
		// 50% chance of suffix
		} else if (randNumSuf == 0 && randNumPre == 1) {
			File suffixFile = new File("src/data/large/MagicSuffix.txt");
			Suffix suf = getSuffix(suffixFile);
			ret = baseItem.getName() + " " + suf.getSuffix() + "\n" + generateBaseStat(baseItem)
				+ "\n" + suf.statistic() + " " + suf.getMod1Code();
			return ret;
		//When both get generated
		} else if (randNumSuf == 0 && randNumPre == 0){
			File prefixFile = new File("src/data/large/MagicPrefix.txt");
			Prefix pre = getPrefix(prefixFile);
			File suffixFile = new File("src/data/large/MagicSuffix.txt");
			Suffix suf = getSuffix(suffixFile);
			ret = pre.getPrefix() + " " + baseItem.getName() + " " + suf.getSuffix() + "\n" 
				+ generateBaseStat(baseItem) + "\n" + pre.statistic() + " " + pre.getMod1Code() + "\n" + suf.statistic() + " " + suf.getMod1Code();
			return ret;
		//when no affixes get generated
		} else {
			ret = baseItem.getName() + "\n" + generateBaseStat(baseItem) + "\n";
			return ret;
		}
	}
	
	/**
	 * Main Driver
	 * @param argc
	 * @throws FileNotFoundException
	 */
	public static void main(String[] argc) throws FileNotFoundException  {

		String resp = "y";
		do {
			//Get file
			File monsterFile = new File("src/data/large/monstats.txt");
			Monster m = pickMonster(getMonsters(monsterFile));
			File treasureClassFile = new File("src/data/large/TreasureClassEx.txt");
			File armorFile = new File("src/data/large/armor.txt");
			
			//Generate user's prompt
			System.out.println("Fighting " + m.getName());
			System.out.println("You have slain " + m.getName());
			System.out.println(m.getName() + " dropped: \n \n");
			
			//Get the data from files
			Map<Treasure, Integer> treasureList = getTreasureClass(treasureClassFile);
			Map<BaseItem, Integer> armorList = getArmors(armorFile);
			Treasure treasure = fetchTreasureClass(m, treasureList);
			BaseItem baseItem = generateBaseItem(treasure, armorList, treasureList);
			System.out.println(generateAffixAndStats(baseItem));
			System.out.println("\n");
			System.out.println("Fight again [y/n]?");
			in = new Scanner(System.in);
			resp = in.nextLine();
			if (resp.equalsIgnoreCase("n") || resp.equalsIgnoreCase("no")) {
				return;
			} 
		} while (resp.equalsIgnoreCase("y") || resp.equalsIgnoreCase("yes"));

	}
}
