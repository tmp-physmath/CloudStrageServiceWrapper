package system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;


public final class AuthPropertiesManager{
	File authinfo_dir_ = null;
	static private AuthPropertiesManager instance = new AuthPropertiesManager();
	static public AuthPropertiesManager getInstance(){
		return instance;
	}
	
	boolean existAuthInfoDir(){
		return authinfo_dir_ != null && authinfo_dir_.exists();
	}
	
	private AuthPropertiesManager(){
		File file = null;
		try{
			file  = new File(this.get_currentpath() + "/authinfo");
			if(!file.isDirectory()){
				System.out.println(file.getAbsolutePath() + "ディレクトリが存在しないので、自動的に作成します");
				file.mkdir();
			}
		}catch(Exception e){
			System.out.println("error: " + file.getAbsolutePath() + "にアクセスできません\n" + e.toString());
		}
		authinfo_dir_ = file;
	}
	

	public Map<String,String> get(String user, String service){
		try {
			File authinfo = new File(authinfo_dir_.getAbsolutePath() + "/" + user + "." + service);
			if(authinfo.canRead()) {
				Properties prop = new Properties();
				try (InputStream authinfo_fio = new FileInputStream(authinfo) ){
					prop.load(authinfo_fio);
				}
				HashMap<String, String> hashMap = new HashMap<String, String>();
				for (Entry<Object, Object> e : prop.entrySet()) {
					hashMap.put(e.getKey().toString(), e.getValue().toString());
				}
				return hashMap;
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return null;
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		} catch (NullPointerException e){
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public boolean set(String user, String service, Map<String,String> data){
		try {
			File authinfo = new File(authinfo_dir_.getAbsolutePath() + "/" + user + "." + service);
			if(!authinfo.exists()){
				authinfo.createNewFile();
			}
			if(authinfo.canWrite()){
				Properties prop = new Properties();
				for( Map.Entry<String,String> e  : data.entrySet()){
					prop.setProperty(e.getKey(), e.getValue());
				}
				try(FileOutputStream authinfo_fos= new FileOutputStream(authinfo)){
					prop.store(authinfo_fos, "propertie : " + user + "." + service);
				}
				return true;
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		} catch(NullPointerException e){
			e.printStackTrace();
			return false;
		}
		System.out.println("error! : in AuthPropertiesManager.set()");
		return false;
	}
	private String get_currentpath(){

		String dirPath = System.getProperty("user.dir");
		return dirPath;
	}

}
