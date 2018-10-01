package com.twitter.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import com.twitter.db.DBException;
import com.twitter.model.UserPojo;

public class TwitterDAO {
	
	public static Connection getConnection() throws Exception {
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/dbTwitter";
			String username = "SKDoss";
			String password = "Kd9789812616;";
			Class.forName(driver);

			Connection connection = DriverManager.getConnection(url, username, password);
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static UserPojo selectUser(String userId) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		UserPojo pojo = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from twitter_user2 where user_id = ?");
			ps.setString(1, userId);
			res = ps.executeQuery();
			if (res != null) {
				while (res.next()) {
			       pojo = new UserPojo();
	               pojo.setUser_id(res.getInt(1));
	               pojo.setTwitter_user_id(res.getInt(2));
	               pojo.setTwitter_screen_name(res.getString(3));
	               pojo.setAccess_token(res.getString(4));
	               pojo.setAccess_token_secret(res.getString(5));
				}
			}
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			conn.close();
			//throw new DBException("Excepion while accessing database");
		}
		return pojo;
	}
	
	
	public static UserPojo selectTwitterUser(long user_id) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet res=null;
		UserPojo pojo = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from twitter_user2 where twitter_user_id = ?");
			ps.setLong(1, user_id);
			res = ps.executeQuery();
			if (res != null) {
				while (res.next()) {
				   pojo = new UserPojo();
	               pojo.setUser_id(res.getInt(1));
	               pojo.setTwitter_user_id(res.getInt(2));
	               pojo.setTwitter_screen_name(res.getString(3));
	               pojo.setAccess_token(res.getString(4));
	               pojo.setAccess_token_secret(res.getString(5));
				}
			}
			//conn.close(conn, ps, res);
		} catch (Exception e) {
			e.printStackTrace();
			//DBConn.close(conn, ps, res);
			//throw new DBException("Excepion while accessing database");
		}
		return pojo;
	}

	public static void updateAccessToken(UserPojo pojo) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("update twitter_user2 set access_token=?, access_token_secret=?  where twitter_user_id = ?");
			ps.setString(1, pojo.getAccess_token());
			ps.setString(2, pojo.getAccess_token_secret());
			ps.setLong(3, pojo.getTwitter_user_id());
			ps.executeUpdate();
			//DBConn.close(conn, ps);
		} catch (Exception e) {
			e.printStackTrace();
			//DBConn.close(conn, ps);
			//throw new DBException("Excepion while accessing database");
		}
	}
	
	
	// add users vote
	public static void insertRow(UserPojo pojo) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("insert into twitter_user2 (twitter_user_id, screen_name, access_token, access_token_secret) values (?,?,?,?)");
			ps.setLong(1,pojo.getTwitter_user_id());
			ps.setString(2,pojo.getTwitter_screen_name());
			ps.setString(3,pojo.getAccess_token());
			ps.setString(4,pojo.getAccess_token_secret());
			ps.executeUpdate();
			//DBConn.close(conn, ps);
		} catch (Exception e) {
			e.printStackTrace();
			//DBConn.close(conn, ps);
		}
	}


}
