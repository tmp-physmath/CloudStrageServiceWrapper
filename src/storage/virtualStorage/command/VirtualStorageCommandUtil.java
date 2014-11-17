package storage.virtualStorage.command;

import java.io.IOException;

import system.ErrorConst;

public class VirtualStorageCommandUtil {
	
	/**
	 * 共通のメッセージを取得する。Exception, 認証失敗、IOExceptionの場合だけ行います
	 * @param bit
	 * @return
	 */
	public static String getCommonCommand(int bit) {
		StringBuilder sb = new StringBuilder();
		if ((bit & ErrorConst.EXCEPTION) != 0) {
			sb.append("処理中に" + Exception.class.getCanonicalName() + "が発生しました。\n");
		}
		
		if ((bit & ErrorConst.CANNOT_AUTH) != 0) {
			sb.append("認証に失敗しました。\n");
		}
		
		if ((bit & ErrorConst.IOEXCEPTION) != 0) {
			sb.append("処理中に" + IOException.class.getCanonicalName() + "が発生しました。\n");
		}
		
		return sb.toString();
	}
	
	/**
	 * 不正なファイル名ならTRUE
	 * @param name ファイル名
	 * @return
	 */
	public static boolean isInvalidFileName(String name) {
		if (isEmpty(name)) {
			return true;
		}
		
		if (name.contains("/")) {
			return true;
		}
		if (name.contains("\\")) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
}
