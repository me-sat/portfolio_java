package Service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.Dao;
import dto.Dto;

//�f�[�^�x�[�X�֓o�^����N���X
public class Insert implements Dbaccess {
	
	public void execute(HttpServletRequest request) throws SQLException{
		
		Dao dao = null;
		
		String input = request.getParameter("todo");//���[�U�[����̓��͂��󂯎���Ă���
		Date input2 = Date.valueOf(request.getParameter("deadline"));
		
		try {
			dao = new Dao();//Dao�N���X�̃R���X�g���N�^��db�ƂȂ���
				if(dao.insertData(input,input2) > 0) {//input�Ƃ̓��[�U�[���e�L�X�g��ADD������
					request.setAttribute("message", "Add completed!");
					System.out.println("Insert success!");
				}else {//�Ȃ�炩�̗��R�ɂ��|�X�g���o���Ȃ������ꍇ
					request.setAttribute("message", "Add failure...");
					System.out.println("Insert failed...");
				}
		}finally {
			if(dao != null) dao.close();
		}
	}

}
