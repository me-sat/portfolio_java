package Service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

//データベースと接続する　（インターフェース）
public interface Dbaccess {
	
		//SQLを実行するためのメソッド（select.javaで実装）
		public void execute(HttpServletRequest request) throws SQLException;


}
