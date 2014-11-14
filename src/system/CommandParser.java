
package system;

import java.io.File;

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
	public IVirtualStorageCommand createCommand(String[] command){
		return new Add(new File(command[1]),command[2]);
	}
}
