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
		LocalStorage strage = new LocalStorage("~/storage");
		return new VirtualStorageTestImpl(strage);
		
	}
	
	static public VirtualStorageFactory getInstance(){
		return factory_;
	}
	
}
