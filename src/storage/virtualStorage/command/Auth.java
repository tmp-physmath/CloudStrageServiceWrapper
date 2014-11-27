package storage.virtualStorage.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import storage.virtualStorage.VirtualStorage;
import system.IAuthorization;
import system.Logger;

public class Auth implements IVirtualStorageCommand{

	@Override
	public String exec(VirtualStorage target) {
		try {
			List<IAuthorization> authorization = target.getAuthorization();
			//リストが空なら全て認証済みとする
			if (authorization.isEmpty()) {
				return "全てのクラウドで認証済みです。";
			}
			
			for (IAuthorization auth : authorization) {
				System.out.println("ユーザー名:" + auth.getUserId() + "のWeb認証を開始します。");
				System.out.println("以下のURLにアクセスしてアクセスキーを入力してください。認証をスキップしたいときは、何も入力せずに改行してください。");
				System.out.println(auth.authURL());
				
				String accessToken = null;
				try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
					accessToken = bf.readLine();
				} catch (IOException e) {
					Logger.printLog(e);
					System.out.println("IOExceptionが発生したため認証に失敗しました。 ユーザー名:" + auth.getUserId()+ "はスキップされました。");
					continue;
				}
				
				if (accessToken == null) {
					System.out.println("ユーザー名:" + auth.getUserId()+ "はスキップされました。");
					continue;
				}
				
				//認証成功
				if (auth.auth(accessToken)) {
					System.out.println("認証に成功しました。ユーザー名:" + auth.getUserId());
				//認証失敗
				} else {
					System.out.println("認証に失敗しました。ユーザー名:" + auth.getUserId()+ "はスキップされました。");
				}
			}
			return "認証が終了しました。";
		} catch (Exception e) {
			Logger.printLog(e);
			return "認証中にExceptionが発生したため認証を完了できませんでした。認証を終了します";
		}
	}

}
