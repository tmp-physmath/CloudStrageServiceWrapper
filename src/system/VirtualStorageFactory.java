package system;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import storage.IStorage;
import storage.cloudStorage.DropBoxStorage;
import storage.virtualStorage.VirtualStorage;
import storage.virtualStorage.VirtualStorageMaximum;

public class VirtualStorageFactory {
	private static VirtualStorageFactory factory_ = new VirtualStorageFactory();
	protected VirtualStorageFactory(){
	}
	
	public VirtualStorage create(File config) throws Exception{
		VirtualStorage vstorage = null;
		if(!config.isFile()  || !config.canRead()){
			System.out.println("コンフィグファイル\n"
					+ config.getAbsolutePath()
					+ "\nが存在しない、あるいは読み込む権限がありません。");
			return null;
		}
		try(FileInputStream fis = new FileInputStream(config);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr) ){
			String line;
			ArrayList<String[]> csvData = new ArrayList<String[]>();
			while ( ( line = br.readLine()) != null ) {
				String[] cols = line.split(",");
				csvData.add(cols);
			}
			if(csvData.get(0).length == 1){
				String vstorage_type = csvData.get(0)[0];
				
				csvData.remove(0);
				//IStorageインスタンスの生成
				List<IStorage> storage_list = Collections.synchronizedList(new ArrayList<IStorage>());
				int index = 0;
				for ( String[] row : csvData ) {
					index++;
					if(row.length != 2){
						System.out.println("コンフィグファイルの" + index + "行目が不正です。この行を無視します");
					} else if(row[0].equals("dropbox")){
						CloudStorageInstance thread = new CloudStorageInstance(this, row[1], storage_list);
						thread.start();
					} else {
						System.out.println(index + "行目のクラウドストレージサービス : " + row[0] + " は未サポートです。この行を無視します。");
					}
				}
				
				// 子の終了を待つ
				waitSync();
				
				if(vstorage_type.equals("maximum")){
					vstorage = new VirtualStorageMaximum(storage_list);
				} else {
					System.out.println("ストレージ管理方式 : " + vstorage_type + " は未サポートです。終了します。");
				}
			}
		} catch (FileNotFoundException e){
			System.out.println("error : コンフィグファイルが見つかりせん。");
			e.getStackTrace();
			return null;
		}
		
		return vstorage;
	}

	//アクティブスレッド数
	volatile int activeThreadCount = 0;
	
	/**
	 * 小スレッドが終了するまで待ちます。必ずメインスレッドで呼び出してください
	 */
	protected synchronized void waitSync() {
		// 同期オブジェクトの待ち合わせ（子がすべて終了するまで待つ）
		while (activeThreadCount > 0) {
			try {
				wait();
			} catch (Exception e) {
				Logger.printLog(e);
				break;
			}
		}
	}
	
	/**
	 * 小スレッドの追加処理
	 */
	public synchronized void addSync() {
		// 同期オブジェクトの追加
		activeThreadCount++;
	}
	
	/**
	 * 子スレッドの削除処理
	 */
	public synchronized void delSync() {
		// 同期オブジェクトの削除
		activeThreadCount--;
		try {
			notify();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	static public VirtualStorageFactory getInstance(){
		return factory_;
	}
	
	/**
	 * クラウド・ストレージインスタンスを作成する
	 */
	class CloudStorageInstance extends Thread {
		VirtualStorageFactory vs;
		String userId;
		List<IStorage> storage_list;
		
		public CloudStorageInstance(VirtualStorageFactory vs, String userId, List<IStorage> storage_list) {
			vs.addSync();
			this.vs = vs;
			this.userId = userId;
			this.storage_list = storage_list;
		}
		
		
		@Override
		public void run() {
			try {
				storage_list.add(new DropBoxStorage(userId));
			} finally {
				vs.delSync();
			}
		}
	}
}
