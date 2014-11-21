
package system;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import storage.virtualStorage.VirtualFile;
import storage.virtualStorage.command.Add;
import storage.virtualStorage.command.Delete;
import storage.virtualStorage.command.Exist;
import storage.virtualStorage.command.FreeSpace;
import storage.virtualStorage.command.IVirtualStorageCommand;
import storage.virtualStorage.command.List;
import storage.virtualStorage.command.Pull;
import storage.virtualStorage.command.Rename;


public class CommandParser {
	static private CommandParser parser_= new CommandParser();
	private CommandParser(){
		super();
	}
	static public CommandParser getInstance(){
		return parser_;
	}

	static HashMap<String, Integer> cmdMap;
	{
		cmdMap = new HashMap<String, Integer>();
		cmdMap.put("add", 3);
		cmdMap.put("delete", 2);
		cmdMap.put("pull", 3);
		cmdMap.put("exist", 2);
		cmdMap.put("list", 1);
		cmdMap.put("rename", 3);
		cmdMap.put("freespace", 1);
	}
	
	
	/**
	 * 操作クラスのインスタンスを返します。第一引数が不正または、ない場合はnullを返します。
	 * @param argList
	 * @return
	 */
	public IVirtualStorageCommand createCommand(ArrayList<String> argList){
		if (argList == null || argList.size() == 0) {
			System.out.println("引数の数が違う");
			return null;
		}
		
		//コマンド名
		String cmdName = argList.get(0);
		
		//引数の数
		int size = cmdMap.get(cmdName);
		//引数の数があってなければnullを返す
		if (size != argList.size()) {
			System.out.println("引数の数が違う２");
			return null;
		}
		
		switch (cmdName) {
		case "add":
			return new Add(new File(argList.get(1)), argList.get(2));
		case "delete":
			return new Delete(new VirtualFile(argList.get(1)));
		case "pull":
			return new Pull(new File(argList.get(1)), new VirtualFile(argList.get(2)));
		case "exist":
			return new Exist(new VirtualFile(argList.get(1)));
		case "list":
			return new List();
		case "rename":
			return new Rename(new VirtualFile(argList.get(1)), argList.get(2));
		case "freespace":
			return new FreeSpace();
		default:
			return null;
		}
	}
}
