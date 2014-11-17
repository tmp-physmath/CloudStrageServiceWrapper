package storage.virtualStorage.command;

import storage.virtualStorage.VirtualFile;
import storage.virtualStorage.VirtualStorage;

public class Exist implements IVirtualStorageCommand{
	VirtualFile targetFile;
	
	public Exist(VirtualFile targetFile) {
		this.targetFile = targetFile;
	}
	
	
	@Override
	public String exec(VirtualStorage target) {
		if (VirtualStorageCommandUtil.isEmpty(targetFile.path_)) {
			return "パラメータが不正です。";
		}
		
		boolean exist = target.exist(targetFile);
		if (exist) {
			return "ファイルは存在します。";
		} else {
			return "ファイルが存在しない、もしくは処理中にエラーが発生しました。";
		}
	}

}
