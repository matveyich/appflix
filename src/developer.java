import java.util.*;

public class developer {
	private String name;
	private Integer numberOfApps;
	private List<application> apps = new ArrayList<application>();
	
	public developer(String name, Integer numberOfApps){
		this.name = name;
		this.numberOfApps = numberOfApps;
		this.generateApps();
	}
	
	private void generateApps(){
		Integer i = 0;
		float numberOfLaunches;
		float cost;
		Random randomno = new Random();
		String appName;
		application app;
		
		while (i < this.numberOfApps){
			numberOfLaunches = (float) (randomno.nextInt(120)*0.033);
			cost = randomno.nextInt(1000)/30;
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
}
