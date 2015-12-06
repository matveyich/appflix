import java.util.*;

public class appflix {
	private static List<developer> devs = new ArrayList<developer>();
	private static List<user> users = new ArrayList<user>();
	private static List<application> storeApps = new ArrayList<application>();
	private static int numberOfDevelopers = 30;
	private static int numberOfUsers = 1000000;
	
	private static int maxAppsPerUser = 20;
	private static int maxAppsPerDeveloper = 1;
	private static float maxAppPrice = (float) 1000.0;
	private static float minAppPrice = (float) 0.0;

	private static float userMonthlyFee = (float) 20.0;
	private static float platformFeePart = (float) 0.1;
	
	public static void main(String[] args) {
		generateDevelopers(numberOfDevelopers);
		generateUsers(numberOfUsers);
		
//		System.out.println("----- Store settings -----\n");
//
//		
//		
//		System.out.println("------Users------\n");
//		
//		for (user u: users){
//			System.out.println(u.getInfo());
//			System.out.println("-------\n");
//		}
//		
//		System.out.println("------Applications------\n");
//		
//		for (application app: storeApps){
//			System.out.println(app.getInfo());
//			System.out.println("revenue: " + app.getMonthlyRevenue(platformFeePart));
//			System.out.println("-------\n");
//		}
		
		System.out.println("------Developers------\n");
		
		for (developer d: devs){
			System.out.println(d.getInfo() + "\n");
			System.out.println("revenue: " + d.getRevenue(platformFeePart));
			System.out.println("-------\n");
		}
		
		System.out.println("finished");
	}
	
	public static void generateDevelopers(int numberOfDevelopers){
		int i = 0;
		developer dev;
		Random randomno = new Random();
		int devAppsNumber;
		
		while (i < numberOfDevelopers){
			devAppsNumber = 0;
			while (devAppsNumber == 0){
				if (maxAppsPerDeveloper == 1 ) {
					devAppsNumber = maxAppsPerDeveloper;
				}
				else {
					devAppsNumber = randomno.nextInt(maxAppsPerDeveloper);
				}
			}
			dev = new developer("dev" + i, devAppsNumber);
			devs.add(dev);
			storeApps.addAll(dev.getApps());
			i++;
		}
		
		// User can't have more apps than the store has, so we limit maxAppsPerUser param
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
		Integer appIndex = 0;
		List<Integer> userAppIndexes = new ArrayList<Integer>();
		
		while (i < numberOfUsers){
			u = new user("user" + i, userMonthlyFee);
			numberOfApps = 0;
			while (numberOfApps == 0){ // user should have more than 0 apps
				numberOfApps = randomno.nextInt(maxAppsPerUser);	
			}
			k = 0;
			appIndex = 0;
			userAppIndexes.clear(); // store app indexes that user already have, so that user doesn't have the same multiple times
			
			// add apps to user
			while (k < numberOfApps){
				
				do {
					appIndex = randomno.nextInt(storeApps.size());	
				} while (userAppIndexes.contains(appIndex) == true);
				u.addApp(storeApps.get(appIndex));
				
				userAppIndexes.add(appIndex);
				k++;
			}
			
			users.add(u);
			u.distributFeeByApps();
			i++;
		}
	}
	
}
