package storage.virtualStorage.command;

import java.io.File;

import storage.virtualStorage.VirtualFile;
import storage.virtualStorage.VirtualStorage;
import system.ErrorConst;

public class Pull implements IVirtualStorageCommand{
	File localFile;
	VirtualFile file;
	
	/**
	 * @param localFile 保存先のローカルのディレクトリ
	 * @param file ダウンロード対象のクラウドのファイル
	 */
	public Pull(File localFile, VirtualFile file) {
		this.localFile = localFile;
		this.file = file;
	}
	
	@Override
	public String exec(VirtualStorage target) {
		//保存先のファイルの存在チェック
		if (localFile.exists()) {
			return "指定した保存先にはファイルが存在します。";
		}
		
		if (VirtualStorageCommandUtil.isInvalidFileName(file.path_)) {
			return "パラメータが不正です。";
		}
		
		//ダウンロード先にフォルダが存在しなければ作成する
		if (!localFile.getParentFile().mkdirs()) {
			return "ローカルのディレクトリ作成中にエラーが発生しました。";
		}
		//ダウンロード実行
		int result = target.download(file, localFile);
		
		StringBuilder sb = new StringBuilder();
		//共通コメントを設定する
		sb.append(VirtualStorageCommandUtil.getCommonCommand(result));
		
		if ((result & ErrorConst.SUCCESS_PROCESS) != 0) {
			sb.append("ダウンロードが完了しました。");
		} else {
			sb.append("ダウンロードが失敗しました。");
		}
		
		return sb.toString();
	}

}
