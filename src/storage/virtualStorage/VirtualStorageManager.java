
package storage.virtualStorage;

import storage.virtualStorage.command.IVirtualStorageCommand;
import system.CommandParser;


public class VirtualStorageManager{
	private VirtualStorage storage_ = null;
	
	private VirtualStorageManager(){
		super();
	}
	
	static private VirtualStorageManager instance = new VirtualStorageManager();
	static public VirtualStorageManager getInstance(){
		return instance;
	}
	public void setVirtualStorage(VirtualStorage storage){
		storage_ = storage;
	}
	public String operate(String[] args){
		IVirtualStorageCommand command = CommandParser.getInstance().createCommand(args);
		return command.exec(storage_);
	}
	
}
