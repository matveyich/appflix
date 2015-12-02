import java.util.*;

public class appflix {
	private static List<developer> devs = new ArrayList<developer>();
	private static List<user> users = new ArrayList<user>();
	
	public static void main(String[] args) {
		generateDevelopers(30);
		generateUsers(10000);
		System.out.println("finished");
	}
	public static void generateDevelopers(int numberOfDevelopers){
		int i = 0;
		Random randomno = new Random();
		while (i < numberOfDevelopers){
			devs.add(new developer("dev" + i, randomno.nextInt(6)));
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
