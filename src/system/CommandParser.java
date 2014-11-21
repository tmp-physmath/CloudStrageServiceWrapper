
package system;

import java.io.File;
import java.util.ArrayList;

import storage.virtualStorage.command.Add;
import storage.virtualStorage.command.IVirtualStorageCommand;


public class CommandParser {
	static private CommandParser parser_= new CommandParser();
	private CommandParser(){
		super();
	}
	static public CommandParser getInstance(){
		return parser_;
	}
	public IVirtualStorageCommand createCommand(ArrayList<String> argList){
		return new Add(new File(argList.get(1)),argList.get(2));
	}
}
