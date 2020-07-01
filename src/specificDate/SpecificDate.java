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
	private Dbaccess dbAccess; //Dbaccess(�C���^�[�t�F�C�X)�^�̕ϐ�
       
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
		
			//����̃f�[�^�̒��o
			dbAccess = new Specific(); 
			try {
					dbAccess.execute(request);//�S�f�[�^�̏���������execute�N���X���Ăяo���Ă���
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
		
		
		
		//	�J�����_�[�̓��t�������ꂽ�Ƃ��ɂ̏���
		
		
		
		doGet(request, response);
	}

}
