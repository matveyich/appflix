import java.util.*;

public class appflix {
	private static List<developer> devs = new ArrayList<developer>();
	private static List<user> users = new ArrayList<user>();
	private static List<application> storeApps = new ArrayList<application>();
	private static int numberOfDevelopers = 100;
	private static int numberOfUsers = 1000000;
	
	private static int maxAppsPerUser = 20; // ограничиваем кол-во приложений которое будет использовать каждый пользователь
	private static int maxAppsPerDeveloper = 11; // ограничиваем кол-во приложений на разработчика - чем больше приложений, тем более популярный разработчик при нормальном распределении пользователей между приложениями

	private static float userMonthlyFee = (float) 20.0; // ежемесячная плата пользователя
	private static float platformFeePart = (float) 0.1; // доля от доходов которую берет AppFlix
	private static float sumDevRevenue = (float) 0.0; // суммарный доход всех разработчиков
	
	public static void main(String[] args) {
		generateDevelopers(numberOfDevelopers);
		generateUsers(numberOfUsers);
		
		System.out.println("------Developers------\n");
		System.out.println("Name, Popularity Index, Revenue, Revenue in M, Number of Users");
		
		// Popularity index симулируется как большее кол-во приложений у разработчика при нормальном распределении пользователей по приложениям
		for (developer d: devs){
			float devRevenue = d.getRevenue(platformFeePart);
			System.out.println(d.getName() + "," + d.getNumberOfApps() + "," + Math.round(devRevenue) + "," + devRevenue/1000000 + "," + d.getNumberOfUsers());
			sumDevRevenue += d.getRevenue(platformFeePart);
		}
		
		System.out.println("\nTotal Dev revenue: " + sumDevRevenue + "\n");
		
		System.out.println("finished");
	}
	
	public static void generateDevelopers(int numberOfDevelopers){
		int i = 0;
		developer dev;
		Random randomno = new Random();
		int devAppsNumber;
		
		while (i < numberOfDevelopers){
			devAppsNumber = 0;

			// Генерируем приложения каждому разработчику
			while (devAppsNumber == 0){
				if (maxAppsPerDeveloper == 1 ) {
					devAppsNumber = maxAppsPerDeveloper;
				}
				else {
					// симулируем, что у часть разработчиков более популярна (у них будет выше Popularity index)
					if (i > numberOfDevelopers*0.85){
						devAppsNumber = randomno.nextInt(maxAppsPerDeveloper);
					} else {
						devAppsNumber = randomno.nextInt(maxAppsPerDeveloper-Math.round(maxAppsPerDeveloper/2));	
					}
				}
			}
			
			dev = new developer("dev" + i, devAppsNumber, i);
			devs.add(dev);
			storeApps.addAll(dev.getApps());
			i++;
		}
		
		// у пользователя не может быть приложений больше чем в сторе
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
			while (numberOfApps == 0){ // у юзера должно быть больше 0 приложений
				numberOfApps = randomno.nextInt(maxAppsPerUser);	
			}
			k = 0;
			appIndex = 0;
			userAppIndexes.clear(); // храним id приложений, которые есть у юзера, чтоб он не использовал одно и то же два раза
			
			// генерим приложения юзерам
			while (k < numberOfApps){
				
				do {
					appIndex = randomno.nextInt(storeApps.size());
				} while (userAppIndexes.contains(appIndex) == true);
				
				application _app = storeApps.get(appIndex);
				u.addApp(_app); //добавляем пользователю приложение
				_app.increaseNumberOfUsers(); // у приложения становится на +1 юзера больше
				
				userAppIndexes.add(appIndex);
				k++;
			}
			
			users.add(u);
			u.distributFeeByApps();
			i++;
		}
	}
	
}
