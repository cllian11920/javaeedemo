package com.jdbcdemo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbcdemo.model.User;


public class UserDao {
	
	//��ѯ�� ��������ID���в�ѯ
	public User getById(int id){
		User result = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from user where id = ?";
		try {
			//1. �������ݿ������
			Class.forName("com.mysql.jdbc.Driver");
			//2. �������ݿ⣬ȡ������
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis", "root", "root");
			// 3.�����ӵĻ�����ȡ��������
			pst = conn.prepareStatement(sql);
			// 4. ����ռλ����ֵ
			pst.setInt(1, id);
			// 5. ִ�в�ѯexecuteQuery
			rs = pst.executeQuery();
			while(rs.next()){
				result = new User();
				result.setId(rs.getInt("id"));
				result.setAddress(rs.getString("address"));
				result.setBirthday(rs.getDate("birthday"));
				result.setSex(rs.getString("sex"));
				result.setUsername(rs.getString("username"));
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			//6. ��finally�йر�������Դ
			try {
				rs.close();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
		
	}
	
	//ɾ��
	public void deleteById(int id){
		Connection conn = null;
		PreparedStatement pst = null;
		String sql = "delete from user where id = ?";
		try {
			//1. �������ݿ������
			Class.forName("com.mysql.jdbc.Driver");
			//2. �������ݿ⣬ȡ������
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis", "root", "root");
			// 3.�����ӵĻ�����ȡ��������
			pst = conn.prepareStatement(sql);
			// 4. ����?��Ӧ��ֵ
			pst.setInt(1, id);
			// 5. executeUpdate������ ִ�����ݿ����ɾ�Ĳ���
			pst.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			//6. ��finally�йر�������Դ
			try {
				
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		UserDao dao = new UserDao();
		//User user = dao.getById(1);
		//System.out.println(user);
		dao.deleteById(26);
		System.out.println("ɾ���ɹ�");
	}

}
