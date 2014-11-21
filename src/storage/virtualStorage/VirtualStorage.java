
package storage.virtualStorage;

import java.util.ArrayList;
import java.util.List;

import storage.IStorage;
import system.IAuthorization;

public abstract class VirtualStorage  implements IStorage{
	protected List<IStorage> storageList_ = new ArrayList<IStorage>();
	@Override
	public boolean isAuthed(){
		for(IStorage storage : storageList_){
			if(!storage.isAuthed())
				return false;
		}
		return true;
	}
	@Override
	public int refreshAuth(){
		int ret=0;
		for(IStorage storage: storageList_){
			ret += storage.refreshAuth();
		}
		return ret;
	}
	
	@Override
	public List<VirtualFile> fileList() {
		List<VirtualFile> ret = new ArrayList<VirtualFile>();
		for(IStorage storage : storageList_){
			ret.addAll(storage.fileList());
		}
		return ret;
	}	
	
	@Override
	public List<IAuthorization> getAuthorization() {
		ArrayList<IAuthorization> authList = new ArrayList<IAuthorization>();
		
		for (IStorage storage : storageList_) {
			authList.addAll(storage.getAuthorization());
		}
		return authList;
	}

}
