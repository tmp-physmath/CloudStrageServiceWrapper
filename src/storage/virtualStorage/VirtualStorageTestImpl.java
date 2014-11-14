
package storage.virtualStorage;

import java.io.File;

import storage.IStorage;

public class VirtualStorageTestImpl extends VirtualStorage{
	
	public VirtualStorageTestImpl(IStorage storage){
		super();
		storageList_.add(storage);
	}
	@Override
	public int add(File file, String name) {
		return storageList_.get(0).add(file,name);
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
