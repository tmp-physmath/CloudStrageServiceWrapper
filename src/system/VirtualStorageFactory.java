package system;


import java.io.File;

import storage.cloudStorage.LocalStorage;
import storage.virtualStorage.VirtualStorage;
import storage.virtualStorage.VirtualStorageTestImpl;

public class VirtualStorageFactory {
	private static VirtualStorageFactory factory_ = new VirtualStorageFactory();
	protected VirtualStorageFactory(){
	}
	public VirtualStorage create(File config){
		LocalStorage str = new LocalStorage("~/storage");
		return new VirtualStorageTestImpl(str);
		
	}
	
	public VirtualStorageFactory getInstance(){
		return factory_;
	}
	
}
