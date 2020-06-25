package finish;

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
import Service.Delete;
import Service.FinishList;
import Service.Insert;
import Service.ReturnList;
import Service.Update;


/**
 * Servlet implementation class Finish
 */
@WebServlet("/Finish")
public class Finish extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dbaccess dbAccess; //Dbaccess(�C���^�[�t�F�C�X)�^�̕ϐ�


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//�S�f�[�^�̒��o
				dbAccess = new FinishList(); 
				try {
					dbAccess.execute(request);//�S�f�[�^�̏���������execute�N���X���Ăяo���Ă���
			}catch(SQLException e) {
				e.printStackTrace();
			}	
				
				
		ServletContext context = getServletContext();
		RequestDispatcher dis = context.getRequestDispatcher("/Finish.jsp");
		dis.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String btn2 = request.getParameter("button");
		
		System.out.println(btn2);
		
		try {	
		
			if(btn2.equals("Return")) {
			
				dbAccess= new ReturnList();
				
			}else if(btn2.equals("Delete")) {
				
				dbAccess= new Delete();
			}
			
				
			dbAccess.execute(request);//execute�N���X���Ăяo���Ă���
			
			
				
		}catch(SQLException e) {
			System.out.println("Exception occured...");
			System.out.println(e);
			}
		
		doGet(request, response);	
	
	}
}
