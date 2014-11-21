
package system;
import java.util.ArrayList;
import java.util.Arrays;

import storage.virtualStorage.VirtualStorage;
import storage.virtualStorage.VirtualStorageManager;
/*
import storage.virtualStorage.command.DoNothing;
import storage.virtualStorage.command.IVirtualStorageCommand;
*/
public class UserInterface {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ArrayList<String> argList = (ArrayList<String>) Arrays.asList(args);
		
		System.out.println("running test\n");
		VirtualStorage vstorage = VirtualStorageFactory.getInstance().create(null);
		VirtualStorageManager.getInstance().setVirtualStorage(vstorage);
		String ret = VirtualStorageManager.getInstance().operate(argList);
		System.out.println(ret);
	}
	/*private static int operateVirtualStorage(String[] args){
		IVirtualStorageCommand command = new DoNothing();
		command.
		return 0;
		
	}
	*/

}
