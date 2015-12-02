import java.util.*;

public class user {
	private String name;
	private List<application> apps = new ArrayList<application>();
	
	public user(String name){
		this.name = name;
	}
	
	public void addApp(application app){
		this.apps.add(app);
	}
	
	public String getInfo(){
		return this.name;
	}
	
	public List<application> getApps() {
		return this.apps;
	}
}
