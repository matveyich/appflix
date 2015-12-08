import java.util.*;

public class user {
	private String name;
	private List<application> apps = new ArrayList<application>();
	private float monthlyFee;
	
	public user(String name, float fee){
		this.name = name;
		this.monthlyFee = fee;
	}
	
	public void addApp(application app){
		this.apps.add(app);
	}
	
	public String getInfo(){
		String info = "";
		info = "name: " + this.name + "\n";
		for (application app: this.apps){
			info = info + "-app: " + app.getName() + "\n";
		}
		return info;
	}
	
	public List<application> getApps() {
		return this.apps;
	}
	
	public void distributFeeByApps(){
		for (application app: this.apps){
				app.increaseRevenue(this.monthlyFee/this.apps.size());	
		}
	}
}
