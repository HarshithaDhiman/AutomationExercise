package pageObject;

public class ColorLog {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	//info
	public static void i(String className, String message) {
		System.out.println(ANSI_GREEN + message + ANSI_RESET);
	}
	//error
	public static void e(String className, String message) {
		System.out.println(ANSI_RED +message + ANSI_RESET);
	}
	//debug
	public static void d(String message) {
		System.out.println(ANSI_BLUE + message + ANSI_RESET);
	}
	//warning
	public static void w(String className, String message) {
		System.out.println(ANSI_YELLOW + message + ANSI_RESET);
	}

}