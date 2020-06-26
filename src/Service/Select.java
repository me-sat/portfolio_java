package Service;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dao.Dao;
import dto.Dto;

//Dbaccessインターフェースを実装
public class Select implements Dbaccess  {

	public void execute(HttpServletRequest request) throws SQLException{
		
		Dao dao=null; //Dao型のdao変数を宣言
		try {
		
		dao = new Dao(); //Daoクラスのインスタンス化
		
		//daoクラスのgetlistAllと同様に、データベースの各行の配列の取得
		ArrayList<Dto>list =dao.getListAll(false);  
		
		if(list.size() > 0) { //list(TODOLIST)がnullじゃなかったら
			request.setAttribute("list",list); //requestスコープに"list"という名前でlist変数を設定
		}else { //list(TODOLIST)が何もなかったら
			request.setAttribute("message","まだデータがありません");
		}
	}finally {
		if(dao !=null)dao.close(); //daoがnullじゃなかったらデータベースとの通信を切断
	}
	
}
			
		
		
		
}

	
	

