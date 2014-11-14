package storage.virtualStorage.command;

import java.io.File;
import java.io.IOException;

import storage.virtualStorage.VirtualStorage;
import system.ErrorConst;

public class Add implements IVirtualStorageCommand {
	
	File targetFile;
	
	String name;
	
	/**
	 * @param file アップロードを行うファイル
	 */
	public Add(File file, String name) {
		targetFile = file;
		this.name = name;
	}
	
	@Override
	public String exec(VirtualStorage target) {
		int resultCode = target.add(targetFile, name);
		
		StringBuilder sb = new StringBuilder();
		
		if((resultCode & ErrorConst.CANNOT_AUTH) != 0) {
			sb.append("認証に失敗しました。\n");
		}
		
		if ((resultCode & ErrorConst.IOEXCEPTION) != 0) {
			sb.append("入出力の際に" + IOException.class.getCanonicalName() + "が発生しました。\n");
		}
		
		if (resultCode == ErrorConst.SUCCESS_PROCESS) {
			sb.append("正常にアップロードされました。\n");
		}
		return sb.toString();
	}

}
