
package storage;

import java.io.File;
import java.util.List;

import storage.virtualStorage.VirtualFile;

public interface IStorage {
	int add(File file);
	int delete(VirtualFile file);
	int rename(VirtualFile target, String name);
	int download(VirtualFile target);
	long getEmptySize();
	int refreshAuth();
	boolean isAuthed();	
	List<VirtualFile> fileList();
	boolean exist(VirtualFile file);
}
