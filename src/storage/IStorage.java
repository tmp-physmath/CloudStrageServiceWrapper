
package storage;

import java.io.File;
import java.util.List;

import storage.virtualStorage.VirtualFile;
import system.IAuthorization;

public interface IStorage {
	int add(File file, String name);
	int delete(VirtualFile file);
	int rename(VirtualFile target, String name);
	int download(VirtualFile target, File distinct);
	long getEmptySize();
	int refreshAuth();
	boolean isAuthed();	
	List<VirtualFile> fileList();
	boolean exist(VirtualFile file);
	List<IAuthorization> getAuthorization();
	
}
