import java.util.*;

public class developer {
	private Integer devId;
	private String name;
	private Integer numberOfApps;
	private List<application> apps = new ArrayList<application>();
	private float revenue = 0;
	private Integer numberOfUsers = 0;
	
	public developer(String name, Integer numberOfApps, Integer devId){
		this.name = name;
		this.numberOfApps = numberOfApps;
		this.generateApps();
		this.devId = devId;
	}
	
	private void generateApps(){
		Integer i = 0;
		Integer numberOfLaunches = 1;
		float cost;
		Random randomno = new Random();
		String appName;
		application app;
		
		while (i < this.numberOfApps){
			appName = this.name + "app" + i.toString();
			app = new application(appName);
			this.apps.add(app);
			i++;
		}
	}
	
	public Integer getNumberOfApps(){
		return this.numberOfApps;
	}
	
	public String getName(){
		return this.name;
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
	
	public Integer getNumberOfUsers(){
		for (application app: this.apps){
			this.numberOfUsers += app.getNumberOfUsers();
		}
		return this.numberOfUsers;
	}
}
