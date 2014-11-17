package system;

public class ErrorConst {
	public static final int SUCCESS_PROCESS = 1;
	
	public static final int FAILED_PROCESS = 1 << 1;

	public static final int CANNOT_AUTH = 1 << 2; 
	
	public static final int IOEXCEPTION = 1 << 3;

	public static final int EXCEPTION = 1 << 4;

	public static final int DBXEXCEPTION = 1 << 5;
}
