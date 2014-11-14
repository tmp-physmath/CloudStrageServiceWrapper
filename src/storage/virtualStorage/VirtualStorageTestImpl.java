
package storage.virtualStorage;

import java.io.File;

public class VirtualStorageTestImpl extends VirtualStorage{

	@Override
	public int add(File file) {
		return storageList_.get(0).add(file);
	}

	@Override
	public int delete(VirtualFile file) {
		return storageList_.get(0).delete(file);
	}

	@Override
	public int rename(VirtualFile target, String name) {
		return storageList_.get(0).rename(target, name);
	}

	@Override
	public int download(VirtualFile target) {
		return storageList_.get(0).download(target);
	}

	@Override
	public long getEmptySize() {
		return storageList_.get(0).getEmptySize();
	}

}
