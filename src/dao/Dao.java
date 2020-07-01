package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Dto;

//SQLへの処理
public class Dao {

	private Connection con;
	private String sql;
	/**
	 * DB接続コンストラクタ<br>
	 * インスタンス化時にDB接続が行われる
	 * @throws SQLException
	 */
	public Dao() throws SQLException{//Daoクラスのコンストラクタ/データーベースに接続するためのコンストラクタ
		// ここに処理を記入してください
		String url ="jdbc:mysql://localhost:3306/todolist?serverTimezone=UTC";//dataベースがある場所
		String user = "root";//ユーザー名
		String pass = "root";//パスワード
		con = DriverManager.getConnection(url, user, pass);//3つの仮引数の情報を使ってデーターベースへアクセスする
		System.out.println("Connection success!");//接続成功するとコンソールに現れる
	}
	
	
	public void close() { 
		try {
			if(con != null) con.close();//DB接続を切るためのメソッド 
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	/**
	 * <br>
	 * DBから取得後、件数分のdtoに1レコードずつ情報を持たせてしてArrayListに格納<br>
	 * @return ArrayList
	 * @throws SQLException
	 */
	
	
	public ArrayList<Dto> getListAll(Boolean yetswitch) throws SQLException{//DBに保存されているデータを全件取得するメソッド/メッセージdto.javaが一行分のデータを取得する
		// ここに処理を記入してください
		sql = " select * from todo where YET=?;";//YET列が未達成のsql文を文字列として配置
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Dto> list = null;
		try {
			ps = con.prepareStatement(sql);//sql文の実行準備
			
			ps.setBoolean(1, yetswitch);//？に０0/1を代入
			
			rs = ps.executeQuery();//実際の実行
			list = new ArrayList<>();//ArrayListをインスタンス化
			Dto dto;//大きい入れ物
			while(rs.next()) {//rs.nextによってカーソルが移動する
				dto = new Dto();//dtoにインスタンス化したものを与え、メッセージｄｔoのインスタンス化をしている
				dto.setDO(rs.getString("DO"));//do列の値を取得
				dto.setDEADLINE(rs.getDate("DEADLINE"));//date列の値を取得
				dto.setYET(rs.getBoolean("YET"));//yet列の値を取得
				dto.setID(rs.getInt("id"));
				list.add(dto);
			}
			rs.close();//SQL自体必要がなくなったためリソースを開放する
		}finally {//どの
			ps.close();//SQL自体必要がなくなったためリソースを開放する
		}
		return list;
	}
	
	
	/**
	 * データ登録メソッド<br>
	 * SQL文とパラメータをexecuteUpdateメソッドに渡す
	 * @param input (受け取った入力値)
	 * @return 成功件数
	 * @throws SQLException
	 */
		public int insertData(String input, Date input2) throws SQLException{//取得したデータの登録するためのメソッド/String inputはユーザーが打ち込んだ内容/int型として戻ってくる
			PreparedStatement ps = null;//psSQLをどのデータベースにどのようなクエリを送るのか定義
			int n = 0;//トライブロックの中にいると戻り値として認識されない
			try {
				String sql = "INSERT INTO  todo (YET,DO,DEADLINE)VALUES (false,?,?)";//?はユーザーが打ち込んだ値
				ps = con.prepareStatement(sql);
				ps.setString(1,  input);//
				ps.setDate(2,  input2);//
				n = ps.executeUpdate();//sqlの実行文
		}finally {
			ps.close();
		}
		// ここに処理を記入してください
		return n;//コード認証が成功した数を返す戻り式
		}
		
		

		/**
		 * タスク完了メソッド<br>
		 * SQL文とパラメータをexecuteUpdateメソッドに渡す
		 * @param 
		 * @return 成功件数
		 * @throws SQLException
		 */
		
		public int finishData(String id) throws SQLException {
			String sql = "update todo set YET = 1 where id = ?";
			
			PreparedStatement ps = null;
			int n = 0;
			
			try {
				ps = con.prepareStatement(sql);
				
				ps.setInt(1,  Integer.parseInt(id));

				n = ps.executeUpdate();
				
			}finally {
				ps.close();
			}
			return n;
			
			
		}


		/**
		 * リストへ再追加するメソッド<br>
		 * SQL文とパラメータをexecuteUpdateメソッドに渡す
		 * @param 
		 * @return 成功件数
		 * @throws SQLException
		 */
		
		public int ReAddData(String id) throws SQLException {
			String sql = "update todo set YET = 0 where id = ?";
			
			PreparedStatement ps = null;
			int n = 0;
			
			try {
				ps = con.prepareStatement(sql);
				
				ps.setInt(1,  Integer.parseInt(id));

				n = ps.executeUpdate();
				
			}finally {
				ps.close();
			}
			return n;
			
		}

		
		/**
		 * データ削除メソッド<br>
		 * SQL文とパラメータをexecuteUpdateメソッドに渡す
		 * @param id (削除するデータのid)
		 * @return 成功件数
		 * @throws SQLException
		 */
		public int deleteData(String id) throws SQLException {
			String sql = "delete from todo where id = ?";
			
			PreparedStatement ps = null;
			int n = 0;
			
			try {
				ps = con.prepareStatement(sql);
				
				ps.setInt(1,  Integer.parseInt(id));

				n = ps.executeUpdate();
				
			}finally {
				ps.close();
			}
			return n;

			}
	
		 
		 	
		/**
		 * <br>
		 * DBから取得後、件数分のdtoに1レコードずつ情報を持たせてしてArrayListに格納<br>
		 * @return ArrayList
		 * @throws SQLException
		 */
		//日付は文字列(sql文)
		public ArrayList<Dto> getSpecific(String deadline) throws SQLException{//DBに保存されている特定のデータを取得するメソッド/メッセージdto.javaが一行分のデータを取得する
			
			sql = " select * from todo where YET=0 and deadline=?;";//ユーザーが選択した締め切り日タスクを文字列として配置
			PreparedStatement ps = null;
			ResultSet rs = null;
			ArrayList<Dto> list = null;
		
			try {
				ps = con.prepareStatement(sql);//sql文の実行準備
				
				ps.setString(1, deadline);//？に０0/1を代入
				
				rs = ps.executeQuery();//実際の実行
				list = new ArrayList<>();//ArrayListをインスタンス化
				Dto dto;//大きい入れ物
				while(rs.next()) {//rs.nextによってカーソルが移動する
					dto = new Dto();//dtoにインスタンス化したものを与え、メッセージｄｔoのインスタンス化をしている
					dto.setDO(rs.getString("DO"));//do列の値を取得
					dto.setDEADLINE(rs.getDate("DEADLINE"));//date列の値を取得
					dto.setYET(rs.getBoolean("YET"));//yet列の値を取得
					dto.setID(rs.getInt("id"));
					list.add(dto);
				}
				rs.close();//SQL自体必要がなくなったためリソースを開放する
			}finally {//どの
				ps.close();//SQL自体必要がなくなったためリソースを開放する
			}
			return list;
		}
			
		
		
}
		
		
		

		
		
		


