
package system;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import storage.virtualStorage.VirtualStorage;
import storage.virtualStorage.VirtualStorageManager;
/*
import storage.virtualStorage.command.IVirtualStorageCommand;
*/
public class UserInterface<T> {

	
	public static void main(String[] args) {
		Logger.start();
		
		if(args.length == 0){
			System.out.println("コマンドライン引数が足りません");
			return;
		}
		List<String> args_list = StringToLinkedList(args);
		File config = getConfigFile(args_list);
		if(config == null){
			System.out.println("コマンドの書式が間違っています。入力し直してください。");
			return;
		}
		VirtualStorage vstorage = null;
		try {
			vstorage = VirtualStorageFactory.getInstance().create(config);
		} catch (Exception e) {
			System.out.println("異常終了しました");
			Logger.printLog(e);
		}
		
		
		if(vstorage == null){
			return;
		}
		VirtualStorageManager.getInstance().setVirtualStorage(vstorage);
		String result = VirtualStorageManager.getInstance().operate(args_list);
		System.out.println(result);
		
		Logger.printLog("実行時間:" + Logger.getTotalTime() + "ms");
	}
	
	public static LinkedList<String> StringToLinkedList (String[] args){
		LinkedList<String> args_list = new LinkedList<String>();
		for(String str : args){
			args_list.add(str);			
		}
		return args_list;
	}
	
	//コマンドライン引数を解析してコンフィグファイル名を返す
	private static File getConfigFile(List<String> args){
		String file_name = System.getProperty("user.dir") + "/config.csv";
		if(args.get(0) == "-c" || args.get(0) == "--config" ){
			if(args.size() > 2){
				file_name = args.get(1);
				args.remove(0);
				args.remove(0);
			} else {
				return null;
			}
		}
		return new File(file_name);
	}
	

}
