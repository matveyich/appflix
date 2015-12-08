
public class application {
	private String name;
	private float revenue = (float) 0.0;
	
	public application(String name){
		this.name = name;
	}
	
	public String getInfo(){
		return "name: " + this.name + "\n"; 
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
