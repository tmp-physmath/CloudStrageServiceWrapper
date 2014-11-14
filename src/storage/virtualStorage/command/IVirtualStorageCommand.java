
package storage.virtualStorage.command;

import storage.virtualStorage.VirtualStorage;


public interface IVirtualStorageCommand {
	String exec(VirtualStorage target);
}
