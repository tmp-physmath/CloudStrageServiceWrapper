package storage.cloudStorage;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.nio.channels.FileChannel;
//import java.util.List;
//
//import storage.IStorage;
//import storage.virtualStorage.VirtualFile;
//import system.IAuthorization;
//
public class LocalStorage {
//	private String dir_;
//	public LocalStorage(String dir){
//		dir_ = dir;
//	}
//	
//	@Override
//	public int add(File file, String name) {
//		FileChannel ifc = null;
//	    FileChannel ofc = null;
//	    try {
//	        // 入力元ファイルのストリームからチャネルを取得
//	        FileInputStream fis = new FileInputStream(file);
//	        ifc = fis.getChannel();
//	        // 出力先ファイルのチャネルを取得
//	        File outFile = new File(dir_ , name);
//	        FileOutputStream fos = new FileOutputStream(outFile);
//	        ofc = fos.getChannel();
//	        // バイトを転送します。
//	        ifc.transferTo(0, ifc.size(), ofc);
//	    } catch (FileNotFoundException e) {
//	        e.printStackTrace();
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    } 
//        if (ifc != null) {
//            try {
//                // 入力チャネルを close します。
//                ifc.close();
//            } catch (IOException e) {
//            }
//        }
//        if (ofc != null) {
//            try {
//                // 出力チャネルを close します。
//                ofc.close();
//            } catch (IOException e) {
//            }
//        }
//    
//	    return 0;
//	}
//
//	@Override
//	public int delete(VirtualFile file) {
//		// TODO 自動生成されたメソッド・スタブ
//		return 0;
//	}
//
//	@Override
//	public int rename(VirtualFile target, String name) {
//		return 0;
//	}
//
//	@Override
//	public long getFreeSpace() {
//		return Long.MAX_VALUE;
//	}
//
//	@Override
//	public int refreshAuth() {
//		return 0;
//	}
//
//	@Override
//	public boolean isAuthed() {
//		return true;
//	}
//
//	@Override
//	public List<VirtualFile> fileList() {
//		return null;
//	}
//
//	@Override
//	public boolean exist(VirtualFile file) {
//		return false;
//	}
//
//	@Override
//	public int download(VirtualFile target, File distinct) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public List<IAuthorization> getAuthorization() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
}
