package specificDate;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.Dbaccess;
import Service.Select;
import Service.Specific;

/**
 * Servlet implementation class SpecificDate
 */
@WebServlet("/SpecificDate")
public class SpecificDate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dbaccess dbAccess; //Dbaccess(インターフェイス)型の変数
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SpecificDate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			//特定のデータの抽出
			dbAccess = new Specific(); 
			try {
					dbAccess.execute(request);//全データの情報を持ったexecuteクラスを呼び出している
			}catch(SQLException e) {
				e.printStackTrace();
			}	
		
		
		
		ServletContext context = getServletContext();
		RequestDispatcher dis = context.getRequestDispatcher("/Specific.jsp");
		dis.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		//	カレンダーの日付を押されたときにの処理
		
		
		
		doGet(request, response);
	}

}
