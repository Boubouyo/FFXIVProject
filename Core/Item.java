package Core;


public class Item implements Look {
	private final String name;
	private String description = "It's an item. It could be usefull";
	

	public Item(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void look()
	{
		System.out.println("You are looking at "+this.name+".");
		System.out.println(this.description);
	}
}
