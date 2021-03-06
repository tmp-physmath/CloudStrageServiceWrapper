package storage.virtualStorage.command;

import storage.virtualStorage.VirtualFile;
import storage.virtualStorage.VirtualStorage;

public class List implements IVirtualStorageCommand{
	@Override
	public String exec(VirtualStorage target) {
		
		java.util.List<VirtualFile> fileList = target.fileList();
		//nullの時は異常状態
		if (fileList == null) {
			if (!target.isAuthed()) {
				return "認証に失敗したため処理をおこなえませんでした。";
			} else {
				return "処理中にエラーが発生しました。";
			}
		}
		
		if (fileList.size() == 0) {
			return "ファイルがひとつも存在しません。";
		}
		
		//カンマ区切りでファイル名を格納
		StringBuilder sb = new StringBuilder();
		for (VirtualFile virtualFile : fileList) {
			sb.append(virtualFile.path_);
			sb.append(", ");
		}
		
		//最後のカンマ+空白を削除する
		if (sb.length() > 2) {
			return sb.substring(0, sb.length() - 2);
		} else {
			return sb.toString();
		}
	}

}
