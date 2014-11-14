
package storage.virtualStorage;

import java.io.File;
import java.util.List;

import storage.IStorage;
import system.IAuthorization;

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
	public int download(VirtualFile target, File distinct) {
		return storageList_.get(0).download(target,distinct);
	}

	@Override
	public long getFreeSpace() {
		return storageList_.get(0).getFreeSpace();
	}
	@Override
	public List<IAuthorization> getAuthorization() {
		// TODO Auto-generated method stub
		return null;
	}

}
