package system;

public class Logger {
	public static void printLog(String log) {
		System.out.println(log);
	}

	public static void printLog(Throwable e) {
		e.printStackTrace();
	}
	
	
}
