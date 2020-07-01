package Service;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dao.Dao;
import dto.Dto;

public class Specific implements Dbaccess {

	public void execute(HttpServletRequest request) throws SQLException {

		String spYear = request.getParameter("year");
		String spMonth = request.getParameter("month");
		String spDay = request.getParameter("day");

		System.out.println(spYear + spMonth + spDay);

		String spDate = spYear + "-" + spMonth + "-" + spDay;
		request.setAttribute("spDate", spDate);
		
		
		Dao dao = null; // Dao型のdao変数を宣言
		try {

			dao = new Dao(); // Daoクラスのインスタンス化

			// daoクラスのgetSpecificと同様に、データベースの各行の配列の取得
			ArrayList<Dto> list = dao.getSpecific(spDate);

			if (list.size() > 0) { // list(TODOLIST)がnullじゃなかったら
				request.setAttribute("list", list); // requestスコープに"list"という名前でlist変数を設定
			} else { // list(TODOLIST)が何もなかったら
				request.setAttribute("message", "データがありません");
			}
		} finally {
			if (dao != null)
				dao.close(); // daoがnullじゃなかったらデータベースとの通信を切断
		}

	}
}
