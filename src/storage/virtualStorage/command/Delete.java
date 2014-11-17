package storage.virtualStorage.command;

import storage.virtualStorage.VirtualFile;
import storage.virtualStorage.VirtualStorage;
import system.ErrorConst;

public class Delete implements IVirtualStorageCommand {
	VirtualFile targetFile;
	
	public Delete(VirtualFile targetFile) {
		this.targetFile = targetFile;
	}

	@Override
	public String exec(VirtualStorage target) {
		if (VirtualStorageCommandUtil.isEmpty(targetFile.path_)) {
			return "パラメータが不正です。";
		}
		
		//ファイルの存在を確認
		if (target.exist(targetFile)) {
			//削除を実行
			int result = target.delete(targetFile);
			
			//結果の文字列
			StringBuilder sb = new StringBuilder();
			sb.append(VirtualStorageCommandUtil.getCommonCommand(result));
			sb.append((result & ErrorConst.SUCCESS_PROCESS) != 0 ? "削除が正常に完了しました。" : "削除に失敗しました。");
			return sb.toString();
		} else {
			return "対象のファイルが存在しません。";
		}
	}

}
