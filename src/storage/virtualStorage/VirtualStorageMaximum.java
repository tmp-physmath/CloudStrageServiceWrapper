package storage.virtualStorage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import storage.IStorage;
import system.ErrorConst;
import system.IAuthorization;
import system.Logger;

public class VirtualStorageMaximum extends VirtualStorage{
	
	public VirtualStorageMaximum(List<IStorage> cloudStorageList) {
		storageList_ = cloudStorageList;
	}
	
	@Override
	public int add(File file, String name) {
		IStorage useStorage = null;
		//TODO リストのサイズ取得
		
		
		//空き容量が最大のものを調べる
		long maxFree = 0;
		for (IStorage storage : storageList_) {
			long freeSpace = storage.getFreeSpace();
			if (freeSpace > maxFree) {
				maxFree = freeSpace;
				useStorage = storage;
			}
		}
		
		//空き容量のあるクラウドが存在しない時
		if (useStorage == null) {
			return ErrorConst.NOT_FREE_SPACE;
		}
		
		//空き容量がアップロードファイルの容量よりも少ない時
		if (maxFree < file.length()) {
			return ErrorConst.TO_BIG_FILE_SIZE;
		}
		
		
		//すでに存在した場合は削除する
		IStorage storage = getStorageByVirtualFile(new VirtualFile(name));
		if (storage != null) {
			int deleteResult = ErrorConst.SUCCESS_PROCESS;
			System.out.println("クラウド上に同名ファイルが存在したため、元のデータを削除します。");
			deleteResult = storage.delete(new VirtualFile(name));

			//削除が失敗した場合はここで終了とする
			if (deleteResult != ErrorConst.SUCCESS_PROCESS) {
				return deleteResult;
			}
		} else {
			Logger.printLog("同名ファイルは存在しません");
		}
		
		//ファイルを追加する
		return useStorage.add(file, name);
	}

	@Override
	public int delete(VirtualFile file) {
		IStorage storage = getStorageByVirtualFile(file);
		
		if (storage != null) {
			return storage.delete(file);
		} else {
			return ErrorConst.NOT_EXIST_ERROR;
		}
	}

	@Override
	public int rename(VirtualFile target, String name) {
		IStorage storage = getStorageByVirtualFile(target);
		
		if (storage != null) {
			return storage.rename(target, name);
		} else {
			return ErrorConst.NOT_EXIST_ERROR;
		}
	}

	@Override
	public int download(VirtualFile target, File distinct) {
		IStorage storage = getStorageByVirtualFile(target);
		
		if (storage != null) {
			return storage.download(target, distinct);
		} else {
			return ErrorConst.NOT_EXIST_ERROR;
		}
	}

	@Override
	public long getFreeSpace() {
		long sumSize = 0;
		
		for (IStorage storage : storageList_) {
			if (storage.getFreeSpace() == -1) {
				return -1;
			}
			sumSize += storage.getFreeSpace();
		}
		return sumSize;
	}

	@Override
	public List<IAuthorization> getAuthorization() {
		ArrayList<IAuthorization> authList = new ArrayList<IAuthorization>();
		
		for (IStorage storage : storageList_) {
			authList.addAll(storage.getAuthorization());
		}
		return authList;
	}
	
	/**
	 * バーチャルファイルからそのファイルが存在するクラウドストレージインタンスを取得する.
	 * どのクラウドにも存在しない場合はnullを返す
	 * @param file
	 * @return
	 */
	public IStorage getStorageByVirtualFile(VirtualFile file) {
		for (IStorage storage : storageList_) {
			if (storage.exist(file)) {
				return storage;
			}
		}
		return null;
	}

	@Override
	public boolean exist(VirtualFile file) {
		return getStorageByVirtualFile(file) != null;
	}
}
