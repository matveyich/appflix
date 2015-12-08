import java.util.*;

public class appflix {
	public static List<developer> devs = new ArrayList<developer>();
	public static List<user> users = new ArrayList<user>();
	public static List<application> storeApps = new ArrayList<application>(); // список приложений всего appFlix
	public static List<application> storeAppsWithPopularity = new ArrayList<application>(); // список приложений заполненный с учетом популярности, чем выше популярность приложения тем чаще оно встречается в этом списке
	public static float sumDevRevenue = (float) 0.0; // суммарный доход всех разработчиков
	
	
	// -------- Переменные которые интересно менять 
	
	public static int numberOfDevelopers = 100; // кол-во разработчико в appFlix 
	public static int numberOfUsers = 1000000; // кол-во пользователей в appFlix
	public static int maxAppsPerUser = 20; // ограничиваем кол-во приложений которое будет использовать каждый пользователь
	public static int maxAppsPerDeveloper = 1; // ограничиваем кол-во приложений на разработчика - чем больше приложений, тем более популярный разработчик при нормальном распределении пользователей между приложениями
	
	public static int maxPopulariryIndex = 15; // максимальный индекс популярности приложения чем больше число, тем больше будет разброс в доходах самого популярного и наименее популярного приложения
	public static float popularAppssPart = (float) 0.05; // доля самых популярных приложений от всего кол-ва приложений, 0.05 значит что 5 процентов приложений самые популярные, на маленьких объемах данных может быть не репрезентативно и нужно будет повысить этот показатель
	public static int popularityForce = 3; // индекс который используется при установлении относительной популярности, этот коэфициент станавливает во сколько раз самое популярное приложение может быть популярнее остальных
	
	public static float userMonthlyFee = (float) 20.0; // ежемесячная плата пользователя
	public static float platformFeePart = (float) 0.1; // доля от доходов которую берет AppFlix

	
	public static void main(String[] args) {
		generateDevelopers(numberOfDevelopers);
		generateUsers(numberOfUsers);
		
		System.out.println("------Developers------\n");
		System.out.println("Name, Number of Apps, Revenue, Revenue in M, Number of Users, Sum Popularity Index");
		
		for (developer d: devs){
			float devRevenue = d.getRevenue(platformFeePart);
			System.out.println(d.getName() + "," + d.getNumberOfApps() + "," + Math.round(devRevenue) + "," + devRevenue/1000000 + "," + d.getNumberOfUsers() + "," + d.getSumPopularityIndex());
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
					devAppsNumber = randomno.nextInt(maxAppsPerDeveloper);				
				}
			}
			
			dev = new developer("dev" + i, devAppsNumber, i);
			devs.add(dev);
			i++;
		}
		
		for (application app: storeApps){
			app.setPopularityIndex();
			int _appI = 0;
			int _maxAppI = app.getPopularityIndex();
			// Popularity index симулируется как большее кол-во приложений у разработчика (это приложение просто чаще начинает встречаться в массиве storeAppsWithPopularity) при нормальном распределении пользователей по приложениям
			while (_appI < _maxAppI){
				storeAppsWithPopularity.add(app);
				_appI++;
			}
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
					appIndex = randomno.nextInt(storeAppsWithPopularity.size());
				} while (userAppIndexes.contains(appIndex) == true);
				
				application _app = storeAppsWithPopularity.get(appIndex);
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