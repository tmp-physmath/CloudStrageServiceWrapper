
package storage.virtualStorage;

import java.util.List;

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
	public String operate(List<String> argList){
		IVirtualStorageCommand command = CommandParser.getInstance().createCommand(argList);
		if(command == null){
			return "コマンド書式が間違っています。入力し直しなおしてください。";
		}
		if(!argList.get(0).equals("auth") && !storage_.isAuthed()){
			return "認証に失敗しました。初回認証が行われていない、あるいはネットワークに接続されていません。";
		}
		return command.exec(storage_);
	}
	
}
