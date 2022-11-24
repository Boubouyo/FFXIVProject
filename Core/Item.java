package Core;


public class Item {
	private final String name;
	private String description = "It's an item. It could be usefull";
	

	public Item(String name) {
		this.name = name;
		this.description = initDescription();
	}
	
	public String initDescription() {
		return "hey this is a description of an item but im too stupid to do something smart sorry";
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
