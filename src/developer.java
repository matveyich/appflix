import java.util.*;

public class developer {
	private Integer devId;
	private String name;
	private Integer numberOfApps;
	private List<application> apps = new ArrayList<application>();
	private float revenue = 0;
	
	public developer(String name, Integer numberOfApps, Integer devId){
		this.name = name;
		this.numberOfApps = numberOfApps;
		this.generateApps();
		this.devId = devId;
	}
	
	private void generateApps(){
		Integer i = 0;
		float numberOfLaunches;
		float cost;
		Random randomno = new Random();
		String appName;
		application app;
		
		while (i < this.numberOfApps){
			numberOfLaunches = (float) 1.0; // assume that at least once this app will be launched
			cost = randomno.nextInt(1000)/30; // random app cost
			appName = this.name + "app" + i.toString();
			app = new application(appName, numberOfLaunches, cost);
			this.apps.add(app);
			i++;
		}
	}
	
	public String getInfo(){
		String info = "";
		info = info + "name: " + this.name + "\n";
		info = info + "apps: \n";
		for (application app : this.apps){
			info = info + "-" + app.getInfo();	
		}
		return info; 
	}
	
	public List<application> getApps(){
		return this.apps;
	}
	
	public float countRevenue(){
		float revenue = 0;
		for (application app: this.apps){
			revenue += app.getRevenue();
		}
		return revenue;
	}
	
	public float getRevenue(float platformFeePart){
		this.revenue = this.countRevenue()*(1-platformFeePart);
		return this.revenue;
	}
}
