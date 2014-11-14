
package storage.virtualStorage;

import storage.virtualStorage.command.IVirtualStorageCommand;
import system.CommandParser;


public class VirtualStorageManager{
	static private VirtualStorage storage_;
	
	private VirtualStorageManager(){
		super();
	}
	
	static private VirtualStorageManager instance;
	static public VirtualStorageManager getInstance(){
		return instance;
	}
	static void setVirtualStorage(VirtualStorage storage){
		storage_ = storage;
	}
	public String operate(String[] args){
		IVirtualStorageCommand command = CommandParser.getInstance().createCommand(args);
		return command.exec(storage_);
	}
	
}
