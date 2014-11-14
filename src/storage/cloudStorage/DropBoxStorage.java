package storage.cloudStorage;

import static system.ErrorConst.CANNOT_AUTH;
import static system.ErrorConst.DBXEXCEPTION;
import static system.ErrorConst.EXCEPTION;
import static system.ErrorConst.IOEXCEPTION;
import static system.ErrorConst.SUCCESS_PROCESS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import storage.IStorage;
import storage.virtualStorage.VirtualFile;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;
import com.dropbox.core.DbxWriteMode;

public class DropBoxStorage implements IStorage {
	
	final static String APP_KEY = "y41d8ridamc9uqv";
	final static String APP_SECRET = "kx3i1y2vagtqzvj";
	
	String accessToken = "83aJRCgbahQAAAAAAAALtSkm-YNn13rYd2_UCxUz0etYZev6TuLYdFGII6tENQo_";
	
	@Override
	public int add(File inputFile, String name) {
		//認証しているか確認
		if (!assertAuth()) {
			return CANNOT_AUTH;
		}
		
		try (FileInputStream inputStream = new FileInputStream(inputFile);) {
			//アップロード実行
			getClient().uploadFile("/" + name, DbxWriteMode.add(), inputFile.length(), inputStream);
		} catch (IOException e) {
			return IOEXCEPTION;
		} catch (Exception e) {
			return EXCEPTION;
		}
		return 1;
	}

	/**
	 * 認証されているか確認して、認証していなければ認証を行う
	 * @return
	 */
	private boolean assertAuth() {
		//リフレッシュ認証できないので認証が切れているときは常に失敗にする
		return isAuthed();
	}

	@Override
	public int delete(VirtualFile file) {
		//認証しているか確認
		if (!assertAuth()) {
			return CANNOT_AUTH;
		}
		
		try {
			getClient().delete("/" + file.path_);
		} catch (DbxException e) {
			return DBXEXCEPTION;
		} catch (Exception e) {
			return EXCEPTION;
		}
		return SUCCESS_PROCESS;
	}

	@Override
	public int rename(VirtualFile target, String name) {
		return 0;
	}

	@Override
	public int download(VirtualFile target) {
		File distinct = new File("");
		try (FileOutputStream outputStream = new FileOutputStream(distinct)) {
			getClient().getFile("/" + target.path_, null, outputStream);
		} catch (IOException e) {
			return IOEXCEPTION;
		} catch (Exception e) {
			return EXCEPTION;
		}
		return SUCCESS_PROCESS;
	}

	@Override
	public long getEmptySize() {
		return 0;
	}

	@Override
	public int refreshAuth() {
		
		return 0;
	}

	//TODO ちゃんとアクセストークンが正しいか調べるべき
	@Override
	public boolean isAuthed() {
		return accessToken != null;
	}
	
	/**
	 * DropBoxクライアントを取得
	 * @return
	 */
	protected DbxClient getClient() {
		return new DbxClient(getConfig(), accessToken);
	}
	
	//リクエストコンフィグ
	protected static DbxRequestConfig requestConfig = null;
	
	/**
	 * リクエストコンフィグを取得
	 * @return
	 */
	protected DbxRequestConfig getConfig() {
		if (requestConfig == null) {
			requestConfig = new DbxRequestConfig("TrendUser", Locale.getDefault().toString());
		}
		return requestConfig;
	}

	@Override
	public List<VirtualFile> fileList() {
		return null;
	}

	@Override
	public boolean exist(VirtualFile file) {
		return false;
	}
	
	//TODO 必要なければ、場所を移すかあとで消します
	/**
	 * web認証を行うための一時的なメソッド
	 * @throws DbxException 
	 */
	public void webAuth() throws DbxException {
		DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

		DbxRequestConfig config = getConfig();
		DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
		
		//URL発行
		String authorizeUrl = webAuth.start();
		System.out.println("1. Go to: " + authorizeUrl);
		System.out.println("2. Click \"Allow\" (you might have to log in first)");
		System.out.println("3. Copy the authorization code.");
		
		//webのコードを入力させる
		String code = null;
		try (Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)))) {
			code = sc.next();
		}
		
		//コードチェック
		if (code == null) {
			System.err.println("入力エラー");
			return;
		}
		
		DbxAuthFinish authFinish = webAuth.finish(code);
		String accessToken = authFinish.accessToken;
		
		System.out.println("アクセストークン:" + accessToken);
		
		this.accessToken = accessToken;
	}
}
