import java.util.*;

public class appflix {
	private static List<developer> devs = new ArrayList<developer>();
	private static List<user> users = new ArrayList<user>();
	private static List<application> storeApps = new ArrayList<application>();
	private static int numberOfDevelopers = 2;
	private static int numberOfUsers = 1;
	
	private static int maxAppsPerUser = 20;
	private static int maxAppsPerDeveloper = 6;
	private static float maxAppPrice = (float) 1000.0;
	private static float minAppPrice = (float) 0.0;

	private static float userMonthlyFee = (float) 20.0;
	private static float platformFeePart = (float) 0.1;
	
	public static void main(String[] args) {
		generateDevelopers(numberOfDevelopers);
		generateUsers(numberOfUsers);
		
		System.out.println("------Users------\n");
		
		for (user u: users){
			System.out.println(u.getInfo());
			System.out.println("-------\n");
		}
		
		System.out.println("------Applications------\n");
		
		for (application app: storeApps){
			System.out.println(app.getInfo());
			System.out.println("revenue: " + app.getMonthlyRevenue(platformFeePart));
			System.out.println("-------\n");
		}
		System.out.println("finished");
	}
	
	public static void generateDevelopers(int numberOfDevelopers){
		int i = 0;
		developer dev;
		Random randomno = new Random();
		
		while (i < numberOfDevelopers){
			dev = new developer("dev" + i, randomno.nextInt(maxAppsPerDeveloper));
			devs.add(dev);
			storeApps.addAll(dev.getApps());
			i++;
		}
		
		int storeSize = storeApps.size();
		if (storeSize < maxAppsPerUser) {
			maxAppsPerUser = storeSize;
		}
	}
	
	public static void generateUsers(int numberOfUsers){
		int i = 0; 
		int	k = 0;
		user u;
		Random randomno = new Random();
		int numberOfApps = 0;
		
		while (i < numberOfUsers){
			u = new user("user" + i, userMonthlyFee);
			numberOfApps = randomno.nextInt(maxAppsPerUser);
			k = 0;
			
			while (k < numberOfApps){
				u.addApp(storeApps.get(randomno.nextInt(storeApps.size())));
				k++;
			}
			
			users.add(u);
			u.distributFeeByApps();
			i++;
		}
	}
	
}
