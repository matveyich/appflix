
public class application {
	private String name;
	private float launchesPerDay;
	private float cost;
	private float revenue = (float) 0.0;
	
	public application(String name, float numberOfLaunches, Float cost){
		this.cost = cost;
		this.launchesPerDay = numberOfLaunches;
		this.name = name;
	}
	
	public String getInfo(){
		return "name: " + this.name + "; cost: " + this.cost + " ; launches per day: " + this.launchesPerDay + "\n"; 
	}
	
	public void increaseRevenue(float amount){
		this.revenue += amount;
	}
}
