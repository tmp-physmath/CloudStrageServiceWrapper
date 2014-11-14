
package system;
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
		VirtualStorage vstorage = VirtualStorageFactory.getInstance().create(null);
		VirtualStorageManager.setVirtualStorage(vstorage);
		String ret = VirtualStorageManager.getInstance().operate(args);
		System.out.println(ret);
	}
	/*private static int operateVirtualStorage(String[] args){
		IVirtualStorageCommand command = new DoNothing();
		command.
		return 0;
		
	}
	*/

}
