
public class application {
	private String name;
	private float launchesPerDay;
	private float cost;
	
	public application(String name, float numberOfLaunches, Float cost){
		this.cost = cost;
		this.launchesPerDay = numberOfLaunches;
		this.name = name;
	}
	
	public String getInfo(){
		return "name: " + this.name + "; cost: " + this.cost + " ; launches per day: " + this.launchesPerDay + "\n"; 
	}
}
