package storage.virtualStorage.command;

import storage.virtualStorage.VirtualFile;
import storage.virtualStorage.VirtualStorage;
import system.ErrorConst;

public class Rename implements IVirtualStorageCommand{
	VirtualFile targetFile;
	String name;
	
	/**
	 * @param targetFile 変更対象のクラウドのファイル
	 * @param name 変更後の名前
	 */
	public Rename(VirtualFile targetFile, String name) {
		this.targetFile = targetFile;
		this.name = name;
	}
	
	
	@Override
	public String exec(VirtualStorage target) {
		if (VirtualStorageCommandUtil.isInvalidFileName(name)) {
			return "パラメータが不正です。";
		}
		
		if (VirtualStorageCommandUtil.isInvalidFileName(targetFile.path_)) {
			return "パラメータが不正です。";
		}
		
		//ファイルの存在チェック
		if (target.exist(targetFile)) {
			return "クラウド上に選択したファイルは存在しません。";
		}
		
		int result = target.rename(targetFile, name);
		
		StringBuilder sb = new StringBuilder();
		//共通コメントを設定する
		sb.append(VirtualStorageCommandUtil.getCommonCommand(result));
		
		if ((result & ErrorConst.SUCCESS_PROCESS) != 0) {
			sb.append("リネームに成功しました。");
		} else {
			sb.append("リネームに失敗しました。");
		}
		
		return sb.toString();
	}

}
