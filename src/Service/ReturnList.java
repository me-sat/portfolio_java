package Service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import dao.Dao;

public class ReturnList implements Dbaccess{

	public void execute(HttpServletRequest request) throws SQLException {//executeメソッド
		
		
		Dao dao = null;
		
		
		String input = request.getParameter("id");
		System.out.println(input);

		try {
			dao = new Dao();//Daoクラスのコンストラクタでdbとつなげる
			if(dao.ReAddData(input) > 0) {//inputは選択されたid
			
				System.out.println("DONE");
			}else {//なんらかの理由によりdone出来なかった
			
				System.out.println("NOT FINISH");
			}
		}finally {
			if(dao != null) dao.close();
		}


	}


	
	
}
