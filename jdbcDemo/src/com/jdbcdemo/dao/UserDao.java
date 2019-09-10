package com.jdbcdemo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbcdemo.model.User;


public class UserDao {
	
	//查询： 根据主键ID进行查询
	public User getById(int id){
		User result = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from user where id = ?";
		try {
			//1. 加载数据库的驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2. 连接数据库，取得连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis", "root", "root");
			// 3.在连接的基础上取得语句对象
			pst = conn.prepareStatement(sql);
			// 4. 设置占位符的值
			pst.setInt(1, id);
			// 5. 执行查询executeQuery
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
			//6. 在finally中关闭连接资源
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
	
	//删除
	public void deleteById(int id){
		Connection conn = null;
		PreparedStatement pst = null;
		String sql = "delete from user where id = ?";
		try {
			//1. 加载数据库的驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2. 连接数据库，取得连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis", "root", "root");
			// 3.在连接的基础上取得语句对象
			pst = conn.prepareStatement(sql);
			// 4. 设置?对应的值
			pst.setInt(1, id);
			// 5. executeUpdate（）： 执行数据库的增删改操作
			pst.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			//6. 在finally中关闭连接资源
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
		System.out.println("删除成功");
	}

}
