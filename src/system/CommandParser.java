
package system;

import java.io.File;
import java.util.HashMap;

import storage.virtualStorage.VirtualFile;
import storage.virtualStorage.command.Add;
import storage.virtualStorage.command.Auth;
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
		cmdMap.put("add", 3);
		cmdMap.put("delete", 2);
		cmdMap.put("pull", 3);
		cmdMap.put("exist", 2);
		cmdMap.put("list", 1);
		cmdMap.put("rename", 3);
		cmdMap.put("freespace", 1);
		cmdMap.put("auth", 1);
	}
	static public CommandParser getInstance(){
		return parser_;
	}

	HashMap<String, Integer> cmdMap = new HashMap<String, Integer>();;
	
	/**
	 * 操作クラスのインスタンスを返します。第一引数が不正または、ない場合はnullを返します。
	 * @param argList
	 * @return
	 */
	public IVirtualStorageCommand createCommand(java.util.List<String> argList){
		if (argList == null || argList.size() == 0) {
			System.out.println("第一引数が存在しません。");
			return null;
		}
		
		//コマンド名
		String cmdName = argList.get(0);
		
		if (!cmdMap.containsKey(cmdName)) {
			System.out.println("コマンドが未対応です。");
			return null;
		}
		
		//引数の数
		int size = cmdMap.get(cmdName);
		//引数の数があってなければnullを返す
		if (size != argList.size()) {
			System.out.println("第2引数以降の引数の数が違います。コマンド:" + cmdName + "は" + (size - 1) + "個の引数を必要とします。");
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
		case "auth":
			return new Auth();
		default:
			return null;
		}
	}
}
