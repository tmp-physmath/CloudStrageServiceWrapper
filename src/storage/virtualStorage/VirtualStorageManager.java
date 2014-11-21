
package storage.virtualStorage;

import java.util.ArrayList;

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
	public String operate(ArrayList<String> argList){
		IVirtualStorageCommand command = CommandParser.getInstance().createCommand(argList);
		return command.exec(storage_);
	}
	
}
