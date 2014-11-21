
package system;
import java.util.HashMap;
import java.util.Map;
/*
import storage.virtualStorage.command.IVirtualStorageCommand;
*/
public class UserInterface {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("does the auth directory exist : " + AuthPropertiesManager.getInstance().existAuthInfoDir());
		/*
		Map<String,String> map = new HashMap<String,String>();
		map.put("test_key2", "test_value2");
		map.put("test_key3", "test_value3");
		boolean flag = AuthPropertiesManager.getInstance().set("testuser", "testservice",  map);
		System.out.println("does set succes : " + flag);
		*/
		Map<String,String> readmap = AuthPropertiesManager.getInstance().get("testuser", "testservice");
		if(readmap == null){
			System.out.println("プロパティファイルを読み込めませんでした");
			return;
		}
		for(Map.Entry<String, String> entry : readmap.entrySet() ){
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		
	}
	/*private static int operateVirtualStorage(String[] args){
		IVirtualStorageCommand command = new DoNothing();
		command.
		return 0;
		
	}
	*/

}
