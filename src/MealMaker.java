public class MealMaker {
	private Food breakfast = new Food(); 
	private Food lunch = new Food();
	private Food dinner = new Food();
	private Food snacks = new Food();

	private PrintWriter food;

	private Scanner data;
	private int total;

	public MealMaker() {
		retrieveData();
		
		total = breakfast.getTotal()
			+lunch.getTotal()
			+dinner.getTotal()
			+snacks.getTotal();
	}

	public void retrieveData() throws FileNotFoundException {
		int[] storage = new int[4];
		
		// if food.txt doesn't exist, create one
		if(!(new File("food.txt").exists())) {
			food = new PrintWriter("food.txt");
			food.print("0 0 0 0");
			food.close();
		}
		
		//import data from file into variable
		data = new Scanner(new File("food.txt"));
		
		//populate array with values from file
		for (int i = 0; data.hasNextInt() && i < storage.length; ++i) {
		storage[i] = data.nextInt();
		}
		data.close();
			  
		//assign values to objects based on array
		breakfast.setTotal(storage[0]);
		lunch.setTotal(storage[1]);
		dinner.setTotal(storage[2]);
		snacks.setTotal(storage[3]);		
	}

	public static void saveToFile() throws FileNotFoundException {
		if(!(new File("food.txt").exists())) {
			food = new PrintWriter("food.txt");
			food.print("0 0 0 0");
			food.close();
		}
		
		food = new PrintWriter("food.txt");
		food.print(breakfast.getTotal()+" "+lunch.getTotal()+" "+dinner.getTotal()+" "+snacks.getTotal());
		food.close();
		}
		
		public static void resetValues() throws FileNotFoundException {
		breakfast = new Food();
		lunch = new Food();
		dinner = new Food();
		snacks = new Food();
		total = breakfast.getTotal()+lunch.getTotal()+dinner.getTotal()+snacks.getTotal();
		saveToFile();
	}
}
