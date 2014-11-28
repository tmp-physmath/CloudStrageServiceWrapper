package system;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Logger {
	
	static long startTime = Long.MIN_VALUE;
	
	/**
	 * 開始時刻を設定する。
	 * システム起動時に実行してください。
	 */
	public static void start() {
		if (startTime == Long.MIN_VALUE) {
			startTime = System.currentTimeMillis();
		}
	}
	
	/**
	 * システム稼働からどれだけの時間が立ったか調べる
	 * @return
	 */
	public static long getTotalTime() {
		if (startTime == Long.MIN_VALUE) {
			return 0L;
		}
		
		return (System.currentTimeMillis() - startTime);
	}
	
	//局所的な時間計測の開始時刻を設定する
	private static Map<String, Long> localTimeMap =  Collections.synchronizedMap(new HashMap<String, Long>());
	
	/**
	 * 局所的な時間計測を開始します。 <br />
	 * 																										<br />
	 * (使い方)																								<br />
	 * A -> B ->  D とプロセスが実行される過程でBの実行時間が知りたい。(Bを局所的な動作といいます。)					<br /><br />
	 * 
	 * この場合、																								<br />
	 * A -> startLocalTime(hoge) -> B -> getLocalTime(hoge) とすることでBの実行時間を取得出来ます。
	 * 
	 * @param localTimeName
	 */
	public static void startLocalTime(String localTimeName) {
		localTimeMap.put(localTimeName, System.currentTimeMillis());
	}
	
	/**
	 * 局所的な動作時間を返す
	 * @param localTimeName
	 * @return
	 */
	public static long getLocalTime(String localTimeName) {
		if (!localTimeMap.containsKey(localTimeName)) {
			return 0L;
		}
		
		return (System.currentTimeMillis() -  localTimeMap.get(localTimeName));
	}
	
	public static void printLog(String log) {
		System.out.println(log);
	}

	public static void printLog(Throwable e) {
		e.printStackTrace();
	}
	
	
}
