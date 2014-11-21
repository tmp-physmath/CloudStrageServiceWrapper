package system;

public class ErrorConst {
	public static final int SUCCESS_PROCESS = 0;
	
	public static final int FAILED_PROCESS = 1 << 1;

	public static final int CANNOT_AUTH = 1 << 2; 
	
	public static final int IOEXCEPTION = 1 << 3;

	public static final int EXCEPTION = 1 << 4;

	public static final int DBXEXCEPTION = 1 << 5;
	
	public static final int NOT_FREE_SPACE = 1 << 6;
	
	public static final int TO_BIG_FILE_SIZE = 1 << 7;
	
	//すでにチェック済みだがファイルが存在しないという不可解なとき。通常は起こりえません。
	public static final int NOT_EXIST_ERROR = 1 << 8;
}
