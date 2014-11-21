package storage.virtualStorage.command;

import java.io.File;

import storage.virtualStorage.VirtualStorage;
import system.ErrorConst;

public class Add implements IVirtualStorageCommand {
	
	File targetFile;
	
	String name;
	
	//20MB
	public static final long MAX_UPLOAD_SIZE = 20_000_000;
	
	/**
	 * @param file アップロードを行うファイル
	 */
	public Add(File file, String name) {
		targetFile = file;
		this.name = name;
	}
	
	@Override
	public String exec(VirtualStorage target) {
		//パラメータチェック
		if (!targetFile.exists()) {
			return "指定したファイルは存在しません。";
		}
		
		if (VirtualStorageCommandUtil.isInvalidFileName(name)) {
			return "指定したファイル名は使えません。";
		}
		
		//ファイルのサイズチェック
		if (targetFile.length() > MAX_UPLOAD_SIZE) {
			return "ファイルが大きすぎるためアップロードできません。";
		}
		
		int resultCode = target.add(targetFile, name);

		StringBuilder resultText = new StringBuilder();
		//共通コマンドを設定
		resultText.append(VirtualStorageCommandUtil.getCommonCommand(resultCode));

		if (resultCode == ErrorConst.SUCCESS_PROCESS) {
			resultText.append("正常にアップロードされました。");
		} else {
			resultText.append("アップロードに失敗しました。");
		}
		return resultText.toString();
	}

}
