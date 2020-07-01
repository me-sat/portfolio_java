package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Dto;

//SQL�ւ̏���
public class Dao {

	private Connection con;
	private String sql;
	/**
	 * DB�ڑ��R���X�g���N�^<br>
	 * �C���X�^���X������DB�ڑ����s����
	 * @throws SQLException
	 */
	public Dao() throws SQLException{//Dao�N���X�̃R���X�g���N�^/�f�[�^�[�x�[�X�ɐڑ����邽�߂̃R���X�g���N�^
		// �����ɏ������L�����Ă�������
		String url ="jdbc:mysql://localhost:3306/todolist?serverTimezone=UTC";//data�x�[�X������ꏊ
		String user = "root";//���[�U�[��
		String pass = "root";//�p�X���[�h
		con = DriverManager.getConnection(url, user, pass);//3�̉������̏����g���ăf�[�^�[�x�[�X�փA�N�Z�X����
		System.out.println("Connection success!");//�ڑ���������ƃR���\�[���Ɍ����
	}
	
	
	public void close() { 
		try {
			if(con != null) con.close();//DB�ڑ���؂邽�߂̃��\�b�h 
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	/**
	 * <br>
	 * DB����擾��A��������dto��1���R�[�h�������������Ă���ArrayList�Ɋi�[<br>
	 * @return ArrayList
	 * @throws SQLException
	 */
	
	
	public ArrayList<Dto> getListAll(Boolean yetswitch) throws SQLException{//DB�ɕۑ�����Ă���f�[�^��S���擾���郁�\�b�h/���b�Z�[�Wdto.java����s���̃f�[�^���擾����
		// �����ɏ������L�����Ă�������
		sql = " select * from todo where YET=?;";//YET�񂪖��B����sql���𕶎���Ƃ��Ĕz�u
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Dto> list = null;
		try {
			ps = con.prepareStatement(sql);//sql���̎��s����
			
			ps.setBoolean(1, yetswitch);//�H�ɂO0/1����
			
			rs = ps.executeQuery();//���ۂ̎��s
			list = new ArrayList<>();//ArrayList���C���X�^���X��
			Dto dto;//�傫�����ꕨ
			while(rs.next()) {//rs.next�ɂ���ăJ�[�\�����ړ�����
				dto = new Dto();//dto�ɃC���X�^���X���������̂�^���A���b�Z�[�W����o�̃C���X�^���X�������Ă���
				dto.setDO(rs.getString("DO"));//do��̒l���擾
				dto.setDEADLINE(rs.getDate("DEADLINE"));//date��̒l���擾
				dto.setYET(rs.getBoolean("YET"));//yet��̒l���擾
				dto.setID(rs.getInt("id"));
				list.add(dto);
			}
			rs.close();//SQL���̕K�v���Ȃ��Ȃ������߃��\�[�X���J������
		}finally {//�ǂ�
			ps.close();//SQL���̕K�v���Ȃ��Ȃ������߃��\�[�X���J������
		}
		return list;
	}
	
	
	/**
	 * �f�[�^�o�^���\�b�h<br>
	 * SQL���ƃp�����[�^��executeUpdate���\�b�h�ɓn��
	 * @param input (�󂯎�������͒l)
	 * @return ��������
	 * @throws SQLException
	 */
		public int insertData(String input, Date input2) throws SQLException{//�擾�����f�[�^�̓o�^���邽�߂̃��\�b�h/String input�̓��[�U�[���ł����񂾓��e/int�^�Ƃ��Ė߂��Ă���
			PreparedStatement ps = null;//psSQL���ǂ̃f�[�^�x�[�X�ɂǂ̂悤�ȃN�G���𑗂�̂���`
			int n = 0;//�g���C�u���b�N�̒��ɂ���Ɩ߂�l�Ƃ��ĔF������Ȃ�
			try {
				String sql = "INSERT INTO  todo (YET,DO,DEADLINE)VALUES (false,?,?)";//?�̓��[�U�[���ł����񂾒l
				ps = con.prepareStatement(sql);
				ps.setString(1,  input);//
				ps.setDate(2,  input2);//
				n = ps.executeUpdate();//sql�̎��s��
		}finally {
			ps.close();
		}
		// �����ɏ������L�����Ă�������
		return n;//�R�[�h�F�؂�������������Ԃ��߂莮
		}
		
		

		/**
		 * �^�X�N�������\�b�h<br>
		 * SQL���ƃp�����[�^��executeUpdate���\�b�h�ɓn��
		 * @param 
		 * @return ��������
		 * @throws SQLException
		 */
		
		public int finishData(String id) throws SQLException {
			String sql = "update todo set YET = 1 where id = ?";
			
			PreparedStatement ps = null;
			int n = 0;
			
			try {
				ps = con.prepareStatement(sql);
				
				ps.setInt(1,  Integer.parseInt(id));

				n = ps.executeUpdate();
				
			}finally {
				ps.close();
			}
			return n;
			
			
		}


		/**
		 * ���X�g�֍Ēǉ����郁�\�b�h<br>
		 * SQL���ƃp�����[�^��executeUpdate���\�b�h�ɓn��
		 * @param 
		 * @return ��������
		 * @throws SQLException
		 */
		
		public int ReAddData(String id) throws SQLException {
			String sql = "update todo set YET = 0 where id = ?";
			
			PreparedStatement ps = null;
			int n = 0;
			
			try {
				ps = con.prepareStatement(sql);
				
				ps.setInt(1,  Integer.parseInt(id));

				n = ps.executeUpdate();
				
			}finally {
				ps.close();
			}
			return n;
			
		}

		
		/**
		 * �f�[�^�폜���\�b�h<br>
		 * SQL���ƃp�����[�^��executeUpdate���\�b�h�ɓn��
		 * @param id (�폜����f�[�^��id)
		 * @return ��������
		 * @throws SQLException
		 */
		public int deleteData(String id) throws SQLException {
			String sql = "delete from todo where id = ?";
			
			PreparedStatement ps = null;
			int n = 0;
			
			try {
				ps = con.prepareStatement(sql);
				
				ps.setInt(1,  Integer.parseInt(id));

				n = ps.executeUpdate();
				
			}finally {
				ps.close();
			}
			return n;

			}
	
		 
		 	
		/**
		 * <br>
		 * DB����擾��A��������dto��1���R�[�h�������������Ă���ArrayList�Ɋi�[<br>
		 * @return ArrayList
		 * @throws SQLException
		 */
		//���t�͕�����(sql��)
		public ArrayList<Dto> getSpecific(String deadline) throws SQLException{//DB�ɕۑ�����Ă������̃f�[�^���擾���郁�\�b�h/���b�Z�[�Wdto.java����s���̃f�[�^���擾����
			
			sql = " select * from todo where YET=0 and deadline=?;";//���[�U�[���I���������ߐ؂���^�X�N�𕶎���Ƃ��Ĕz�u
			PreparedStatement ps = null;
			ResultSet rs = null;
			ArrayList<Dto> list = null;
		
			try {
				ps = con.prepareStatement(sql);//sql���̎��s����
				
				ps.setString(1, deadline);//�H�ɂO0/1����
				
				rs = ps.executeQuery();//���ۂ̎��s
				list = new ArrayList<>();//ArrayList���C���X�^���X��
				Dto dto;//�傫�����ꕨ
				while(rs.next()) {//rs.next�ɂ���ăJ�[�\�����ړ�����
					dto = new Dto();//dto�ɃC���X�^���X���������̂�^���A���b�Z�[�W����o�̃C���X�^���X�������Ă���
					dto.setDO(rs.getString("DO"));//do��̒l���擾
					dto.setDEADLINE(rs.getDate("DEADLINE"));//date��̒l���擾
					dto.setYET(rs.getBoolean("YET"));//yet��̒l���擾
					dto.setID(rs.getInt("id"));
					list.add(dto);
				}
				rs.close();//SQL���̕K�v���Ȃ��Ȃ������߃��\�[�X���J������
			}finally {//�ǂ�
				ps.close();//SQL���̕K�v���Ȃ��Ȃ������߃��\�[�X���J������
			}
			return list;
		}
			
		
		
}
		
		
		

		
		
		


