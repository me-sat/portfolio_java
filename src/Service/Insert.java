package Service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.Dao;
import dto.Dto;

//データベースへ登録するクラス
public class Insert implements Dbaccess {
	
	public void execute(HttpServletRequest request) throws SQLException{
		
		Dao dao = null;
		
		String input = request.getParameter("todo");//ユーザーからの入力を受け取っている
		Date input2 = Date.valueOf(request.getParameter("deadline"));
		
		try {
			dao = new Dao();//Daoクラスのコンストラクタでdbとつなげる
				if(dao.insertData(input,input2) > 0) {//inputとはユーザーがテキストをADDした回数
					request.setAttribute("message", "Add completed!");
					System.out.println("Insert success!");
				}else {//なんらかの理由によりポストが出来なかった場合
					request.setAttribute("message", "Add failure...");
					System.out.println("Insert failed...");
				}
		}finally {
			if(dao != null) dao.close();
		}
	}

}
