package Service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import dao.Dao;

public class ReturnList implements Dbaccess{

	public void execute(HttpServletRequest request) throws SQLException {//execute���\�b�h
		
		
		Dao dao = null;
		
		
		String input = request.getParameter("id");
		System.out.println(input);

		try {
			dao = new Dao();//Dao�N���X�̃R���X�g���N�^��db�ƂȂ���
			if(dao.ReAddData(input) > 0) {//input�͑I�����ꂽid
			
				System.out.println("DONE");
			}else {//�Ȃ�炩�̗��R�ɂ��done�o���Ȃ�����
			
				System.out.println("NOT FINISH");
			}
		}finally {
			if(dao != null) dao.close();
		}


	}


	
	
}
