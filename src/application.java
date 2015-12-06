
public class application {
	private String name;
	private float launchesDayMonth;
	private float cost;
	private float revenue = (float) 0.0;
	
	public application(String name, float numberOfLaunches, Float cost){
		this.cost = cost;
		this.launchesDayMonth = numberOfLaunches;
		this.name = name;
	}
	
	public String getInfo(){
		return "name: " + this.name + "; cost: " + this.cost + " ; launches per day: " + this.launchesDayMonth + "\n"; 
	}
	
	public float getLaunches(){
		return this.launchesDayMonth;
	}
	
	public String getName(){
		return this.name;
	}
	
	public float getMonthlyRevenue(float platformFeePart){
		return this.revenue*(1-platformFeePart);
	}
	
	public void increaseRevenue(float amount){
		this.revenue += amount;
	}
	
	public float getRevenue(){
		return this.revenue;
	}
}
