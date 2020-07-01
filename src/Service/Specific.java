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
		
		
		Dao dao = null; // Dao�^��dao�ϐ���錾
		try {

			dao = new Dao(); // Dao�N���X�̃C���X�^���X��

			// dao�N���X��getSpecific�Ɠ��l�ɁA�f�[�^�x�[�X�̊e�s�̔z��̎擾
			ArrayList<Dto> list = dao.getSpecific(spDate);

			if (list.size() > 0) { // list(TODOLIST)��null����Ȃ�������
				request.setAttribute("list", list); // request�X�R�[�v��"list"�Ƃ������O��list�ϐ���ݒ�
			} else { // list(TODOLIST)�������Ȃ�������
				request.setAttribute("message", "�f�[�^������܂���");
			}
		} finally {
			if (dao != null)
				dao.close(); // dao��null����Ȃ�������f�[�^�x�[�X�Ƃ̒ʐM��ؒf
		}

	}
}
