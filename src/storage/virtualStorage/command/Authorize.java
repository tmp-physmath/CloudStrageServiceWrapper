package storage.virtualStorage.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import storage.virtualStorage.VirtualStorage;
import system.IAuthorization;
import system.Logger;

//初回認証を意味するコマンド。
public class Authorize implements IVirtualStorageCommand {
	@Override
	public String exec(VirtualStorage target) {
		System.out.println("Web認証を開始します。表示されるURLにアクセスして、ユーザ認証を行って下さい。\n"
				+ "認証をスキップしたいときは、何も入力せずに改行してください。");
		String key=null;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		boolean flag = false;
		for(IAuthorization auth : target.getAuthorization()){
			System.out.print(flag ? "次の認証に移ります。以下のURLを開いて下さい。" : "");
			System.out.println("\n"+auth.authURL());
			do{
				System.out.print("認証キーを入力して下さい:");
		        try {
					key = in.readLine();
				} catch (IOException e) {
					Logger.printLog(e);
				}
				if(auth.auth(key)){
					System.out.println("認証に成功しました。");
					break;
				}else{
					System.out.println("認証に失敗しました。認証をやり直してください。\n"
							+ "hint: 間違ったユーザでログインしていませんか？ 一度ログアウトしてみて下さい。" );
				}
			}while(!key.equals(""));
			flag = true;
		}
		System.out.print("\r\n");
		
		return "認証処理をを終了します";
	}

}
