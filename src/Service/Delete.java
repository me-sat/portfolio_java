package Service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import dao.Dao;

public class Delete implements Dbaccess {
	

	@Override
	public void execute(HttpServletRequest request) throws SQLException {

		// ‚±‚±‚Éˆ—‚ð‹L“ü‚µ‚Ä‚­‚¾‚³‚¢
		Dao dao = null;
		int n = 0;
		
		String code = request.getParameter("id");
		
		try {
			dao = new Dao();
			n = dao.deleteData(code);
			
			if(n > 0) {
				request.setAttribute("message", "Complete the deletion");
			}else {	
				request.setAttribute("message", "Delete failed");
			}
		}finally {
			if(dao != null) dao.close();
		}
	}
	
	

}
