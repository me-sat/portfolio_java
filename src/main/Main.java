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
	private Dbaccess dbAccess; //Dbaccess(インターフェイス)型の変数

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//全データの抽出
		dbAccess = new Select(); 
		try {
			dbAccess.execute(request);//全データの情報を持ったexecuteクラスを呼び出している
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
			
			if (ToDo.equals("") || DeadLine.equals("")) { //AddListの内容が入力されてなかった場合
				
				request.setAttribute("message2", "入力してください");
				doGet(request, response);
			}
			
		
		}else if(btn.equals("Done")) {
			dbAccess= new Update();
			
		
		}
		
			
			dbAccess.execute(request);//executeクラスを呼び出している
		
		// 全データ抽出処理
			doGet(request, response);
	}catch(SQLException e) {
		System.out.println("Exception occured...");
		System.out.println(e);
		}
		
	}

}
