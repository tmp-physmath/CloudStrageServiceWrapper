
package storage.virtualStorage;

import java.util.List;

import storage.IStorage;

public abstract class VirtualStorage  implements IStorage{
	protected List<IStorage> storageList_;
	
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
}
