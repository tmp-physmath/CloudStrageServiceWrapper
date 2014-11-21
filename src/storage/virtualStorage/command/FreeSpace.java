package storage.virtualStorage.command;

import java.math.BigDecimal;

import storage.virtualStorage.VirtualStorage;
import system.Logger;

public class FreeSpace implements IVirtualStorageCommand {

	@Override
	public String exec(VirtualStorage target) {
		try {
			long freeSpace = target.getFreeSpace();
			BigDecimal decimal = new BigDecimal(freeSpace);
			decimal = decimal.divide(new BigDecimal(1000_000_000));
			return decimal.setScale(3, BigDecimal.ROUND_DOWN).toString() + " GB";
		} catch (Exception e) {
			Logger.printLog(e);
			return "エラーが発生したため空き容量を取得できませんでした。";
		}
		
	}

}