import java.util.*;

public class appflix {
	private static List<developer> devs = new ArrayList<developer>();
	private static List<user> users = new ArrayList<user>();
	private static List<application> storeApps = new ArrayList<application>();
	
	
	public static void main(String[] args) {
		generateDevelopers(2);
		generateUsers(1);
		System.out.println("finished");
	}
	
	public static void generateDevelopers(int numberOfDevelopers){
		int i = 0;
		developer dev;
		
		Random randomno = new Random();
		while (i < numberOfDevelopers){
			dev = new developer("dev" + i, randomno.nextInt(6));
			devs.add(dev);
			storeApps.addAll(dev.getApps());
			i++;
		}
	}
	
	public static void generateUsers(int numberOfUsers){
		int i = 0;
		while (i < numberOfUsers){
			users.add(new user("user" + i));
			i++;
		}
	}
	
}
