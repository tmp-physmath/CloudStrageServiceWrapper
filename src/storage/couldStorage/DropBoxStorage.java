package storage.couldStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import storage.IStorage;
import storage.virtualStorage.VirtualFile;
import static system.ErrorConst.*;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWriteMode;

public class DropBoxStorage implements IStorage {
	
	final static String APP_KEY = "y41d8ridamc9uqv";
	final static String APP_SECRET = "kx3i1y2vagtqzvj";
	
	String accessToken = "83aJRCgbahQAAAAAAAALtSkm-YNn13rYd2_UCxUz0etYZev6TuLYdFGII6tENQo_";
	
	@Override
	public int add(File inputFile) {
		//認証しているか確認
		if (!assertAuth()) {
			return CANNOT_AUTH;
		}
		
		try (FileInputStream inputStream = new FileInputStream(inputFile);) {
			//アップロード実行
			getClient().uploadFile("/" + inputFile.getName(), DbxWriteMode.add(), inputFile.length(), inputStream);
		} catch (IOException e) {
			return EXCEPTION_IOEXCEPTION;
		} catch (Exception e) {
			return EXCEPTION_EXCEPTION;
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
		return 0;
	}

	@Override
	public int rename(VirtualFile target, String name) {
		return 0;
	}

	@Override
	public int download(VirtualFile target) {
		return 0;
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
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public boolean exist(VirtualFile file) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
}
