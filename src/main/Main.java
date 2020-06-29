package main;

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
import Service.Insert;
import Service.Select;
import Service.Update;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dbaccess dbAccess; //Dbaccess(�C���^�[�t�F�C�X)�^�̕ϐ�

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//�S�f�[�^�̒��o
		dbAccess = new Select(); 
		try {
			dbAccess.execute(request);//�S�f�[�^�̏���������execute�N���X���Ăяo���Ă���
	}catch(SQLException e) {
		e.printStackTrace();
	}	
		
		
		ServletContext context = getServletContext();
		RequestDispatcher dis = context.getRequestDispatcher("/Main.jsp");
		dis.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		String btn = request.getParameter("button");
		System.out.println(btn);
		
		String ToDo = request.getParameter("todo");
		String DeadLine = request.getParameter("deadline");
		
	try {	
		if (btn.equals("Add List")) {
			dbAccess= new Insert();
			
			if (ToDo.equals("") || DeadLine.equals("")) { //AddList�̓��e�����͂���ĂȂ������ꍇ
				
				request.setAttribute("message2", "���͂��Ă�������");
				doGet(request, response);
			}
			
		
		}else if(btn.equals("Done")) {
			dbAccess= new Update();
			
		
		}
		
			
			dbAccess.execute(request);//execute�N���X���Ăяo���Ă���
		
		// �S�f�[�^���o����
			doGet(request, response);
	}catch(SQLException e) {
		System.out.println("Exception occured...");
		System.out.println(e);
		}
		
	}

}
