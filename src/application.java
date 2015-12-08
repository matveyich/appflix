import java.util.Random;

public class application {
	private String name;
	private float revenue = (float) 0.0;
	private Integer numberOfUsers = 0;
	private Integer popularityIndex = 1;
	private Integer appIndex;
	
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
	
	public void increaseNumberOfUsers(){
		this.numberOfUsers += 1;
	}
	
	public Integer getNumberOfUsers(){
		return this.numberOfUsers;
	}

	public Integer getPopularityIndex() {
		return popularityIndex;
	}

	public void setPopularityIndex() {
		int popularityIndex = 1;
		Random randomno = new Random();
		
		if (this.appIndex > (1-appflix.popularAppssPart)*appflix.storeApps.size()){
			// это приложение среди популярных
			while (popularityIndex < appflix.maxPopulariryIndex/appflix.popularityForce) 
				popularityIndex = randomno.nextInt(appflix.maxPopulariryIndex);
		} else {
			do {
				popularityIndex = randomno.nextInt(Math.round(appflix.maxPopulariryIndex/appflix.popularityForce));				
			} while (popularityIndex == 0);
			
		}
		this.popularityIndex = popularityIndex;
		
	}

	public Integer getAppIndex() {
		return appIndex;
	}

	public void setAppIndex(Integer appIndex) {
		this.appIndex = appIndex;
	}
}
