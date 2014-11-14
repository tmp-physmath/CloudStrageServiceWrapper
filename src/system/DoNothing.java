
package system;

import storage.virtualStorage.VirtualStorage;
import storage.virtualStorage.command.IVirtualStorageCommand;

public class DoNothing implements IVirtualStorageCommand{
	@Override
	public String exec(VirtualStorage target) {
		// TODO 自動生成されたメソッド・スタブ
		return "Do Nothing!";
	}
}
