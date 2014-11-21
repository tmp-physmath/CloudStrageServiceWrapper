package system;


import java.io.File;

import storage.IStorage;
import storage.cloudStorage.DropBoxStorage;
import storage.virtualStorage.VirtualStorage;
import storage.virtualStorage.VirtualStorageTestImpl;

public class VirtualStorageFactory {
	private static VirtualStorageFactory factory_ = new VirtualStorageFactory();
	protected VirtualStorageFactory(){
	}
	public VirtualStorage create(File config){
		IStorage strage = new DropBoxStorage("USER_ID");
		return new VirtualStorageTestImpl(strage);
		
	}
	
	static public VirtualStorageFactory getInstance(){
		return factory_;
	}
	
}
