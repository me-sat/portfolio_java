package Service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

//�f�[�^�x�[�X�Ɛڑ�����@�i�C���^�[�t�F�[�X�j
public interface Dbaccess {
	
		//SQL�����s���邽�߂̃��\�b�h�iselect.java�Ŏ����j
		public void execute(HttpServletRequest request) throws SQLException;


}
