package step3vmgit;

public class Utils {
	
	public String getCurrentPath() {
		String currentPath = System.getProperty("user.dir");
		System.out.println(currentPath);
		return currentPath;
	}

}
