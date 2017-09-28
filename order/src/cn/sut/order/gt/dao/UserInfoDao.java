package cn.sut.order.gt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.sut.order.gt.vo.AllUser;
import cn.sut.order.gt.vo.UserInfo;

public class UserInfoDao {
	public List<AllUser> SelectAllUser(){
		BaseDao bd = new BaseDao();
		List<AllUser> list = new ArrayList<AllUser>();
		
		try {
			Connection conn = bd.getConn();
			String sql ="select userId, userAccount, userPass, roleName, faceimg"
					+" from userinfo, roleinfo"
					+" where role=roleId and userState=1";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				AllUser user = new AllUser();
				user.setUserId(rs.getInt(1));
				user.setUserAccount(rs.getString(2));
				user.setUserPass(rs.getString(3));
				user.setRoleName(rs.getString(4));
				user.setFaceimg(rs.getString(5));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public List<AllUser> SelectUserById(int userId){
		BaseDao bd = new BaseDao();
		List<AllUser> list = new ArrayList<AllUser>();
		
		try {
			Connection conn = bd.getConn();
			String sql ="select userId, userAccount, userPass, roleName, faceimg"
					+" from userinfo, roleinfo"
					+" where role=roleId  and userId=? and userState=1";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, userId);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				AllUser user = new AllUser();
				user.setUserId(rs.getInt(1));
				user.setUserAccount(rs.getString(2));
				user.setUserPass(rs.getString(3));
				user.setRoleName(rs.getString(4));
				user.setFaceimg(rs.getString(5));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<UserInfo> SelectUserId(int userId){
		BaseDao bd = new BaseDao();
		List<UserInfo> list = new ArrayList<UserInfo>();
		
		try {
			Connection conn = bd.getConn();
			String sql ="select* from userInfo where userId=? and userState=1";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, userId);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				UserInfo user = new UserInfo();
				user.setUserId(rs.getInt(1));
				user.setUserAccount(rs.getString(2));
				user.setUserPass(rs.getString(3));
				user.setRole(rs.getInt(4));
				user.setLocked(rs.getInt(5));
				user.setFaceimg(rs.getString(6));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	public int Lockedcount(){
		BaseDao bd= new BaseDao();
		int sum = 0;
		try{
			Connection conn = bd.getConn();
			String sql = "select count(*) from userInfo where userState=1 and locked=0";
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				sum +=rs.getInt(1);
			}
			
				
		}catch(SQLException e){
			e.printStackTrace();
		}
		return sum;
	}
	
	public List<UserInfo> SelectKitchen(){
		BaseDao bd = new BaseDao();
		List<UserInfo> list = new ArrayList<UserInfo>();
		
		try {
			Connection conn = bd.getConn();
			String sql ="select* from userInfo where role=2 and userState=1";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				UserInfo user = new UserInfo();
				user.setUserId(rs.getInt(1));
				user.setUserAccount(rs.getString(2));
				user.setUserPass(rs.getString(3));
				user.setRole(rs.getInt(4));
				user.setLocked(rs.getInt(5));
				user.setFaceimg(rs.getString(6));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int Kitchencount(){
		BaseDao bd= new BaseDao();
		int sum = 0;
		try{
			Connection conn = bd.getConn();
			String sql = "select count(*) from userInfo where userState=1 and role=2 and locked=0";
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				sum +=rs.getInt(1);
			}
			
				
		}catch(SQLException e){
			e.printStackTrace();
		}
		return sum;
	}
	
	public List<UserInfo> SelectWaiter(){
		BaseDao bd = new BaseDao();
		List<UserInfo> list = new ArrayList<UserInfo>();
		
		try {
			Connection conn = bd.getConn();
			String sql ="select* from userInfo where role=3 and userState=1";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				UserInfo user = new UserInfo();
				user.setUserId(rs.getInt(1));
				user.setUserAccount(rs.getString(2));
				user.setUserPass(rs.getString(3));
				user.setRole(rs.getInt(4));
				user.setLocked(rs.getInt(5));
				user.setFaceimg(rs.getString(6));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int Waitercount(){
		BaseDao bd= new BaseDao();
		int sum = 0;
		try{
			Connection conn = bd.getConn();
			String sql = "select count(*) from userInfo where userState=1 and role=3 and locked=0";
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				sum +=rs.getInt(1);
			}
			
				
		}catch(SQLException e){
			e.printStackTrace();
		}
		return sum;
	}
	
	
	public List<UserInfo> login(String userAccount, String userPass){
		BaseDao bd = new BaseDao();
		List<UserInfo> list = new ArrayList<UserInfo>();
		
		try {
			Connection conn = bd.getConn();
			String sql ="select* from userInfo where userAccount=? and userPass=? and userState=1";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, userAccount);
			pstm.setString(2, userPass);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				UserInfo user = new UserInfo();
				user.setUserId(rs.getInt(1));
				user.setUserAccount(rs.getString(2));
				user.setUserPass(rs.getString(3));
				user.setRole(rs.getInt(4));
				user.setLocked(rs.getInt(5));
				user.setFaceimg(rs.getString(6));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<UserInfo> SelectUserName(String userAccount){
		BaseDao bd = new BaseDao();
		List<UserInfo> list = new ArrayList<UserInfo>();
		
		try {
			Connection conn = bd.getConn();
			String sql ="select* from userInfo where UserAccount=? and userState=1";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, userAccount);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				UserInfo user = new UserInfo();
				user.setUserId(rs.getInt(1));
				user.setUserAccount(rs.getString(2));
				user.setUserPass(rs.getString(3));
				user.setRole(rs.getInt(4));
				user.setLocked(rs.getInt(5));
				user.setFaceimg(rs.getString(6));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<AllUser> SelectUser(int start, int end){
		BaseDao bd= new BaseDao();
		List<AllUser> list = new ArrayList<AllUser>();
		try {
			Connection conn = bd.getConn();
			String sql = "select userId, userAccount, userPass, roleName, faceimg from userinfo, roleinfo  where roleId=role and userState=1 order by userId desc limit ?,?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setObject(1,start);
			psmt.setObject(2,end);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()){
				AllUser user = new AllUser();
				user.setUserId(rs.getInt(1));
				user.setUserAccount(rs.getString(2));
				user.setUserPass(rs.getString(3));
				user.setRoleName(rs.getString(4));
				user.setFaceimg(rs.getString(5));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean addUser(String userAccount,String userPass, int role, String faceimg){
		BaseDao bd = new BaseDao();
		
		try {
			Connection conn = bd.getConn();
			String sql = "insert into userinfo(userAccount, userPass, role, faceimg) values(?,?,?,?)";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, userAccount);
			pstm.setString(2, userPass);
			pstm.setInt(3, role);
			pstm.setString(4, faceimg);
			int i = pstm.executeUpdate();
			if(i > 0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	public boolean updateUser(String userPass, int role, String faceimg, int userId){
		BaseDao bd = new BaseDao();
		int i;
		try {
			Connection conn = bd.getConn();
			String sql = "update userinfo set faceimg=?, role=?";
			if(!(userPass=="")){
				sql += ", userPass=? where userId=?";
				PreparedStatement pstm = conn.prepareStatement(sql);
				pstm.setString(1, faceimg);
				pstm.setInt(2, role);
				pstm.setString(3, userPass);
				pstm.setInt(4, userId);
				i = pstm.executeUpdate();
			}else{
				sql += " where userId=?";
				PreparedStatement pstm = conn.prepareStatement(sql);
				pstm.setString(1, faceimg);
				pstm.setInt(2, role);
				pstm.setInt(3, userId);
				i = pstm.executeUpdate();
			}
			
			if(i > 0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	public boolean updateUserByuser(String userPass, String faceimg, int userId){
		BaseDao bd = new BaseDao();
		int i;
		try {
			Connection conn = bd.getConn();
			String sql = "update userinfo set faceimg=?, userPass=? where userId=?";
				PreparedStatement pstm = conn.prepareStatement(sql);
				pstm.setString(1, faceimg);
				pstm.setString(2, userPass);
				pstm.setInt(3, userId);
				i = pstm.executeUpdate();
			
			if(i > 0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	public boolean deleteUser(int userId){
		BaseDao bd = new BaseDao();
		
		try {
			Connection conn = bd.getConn();
			String sql = "update userinfo set userState=0 where userId=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, userId);
			int i = pstm.executeUpdate();
			if(i > 0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	public int count(){
		BaseDao bd= new BaseDao();
		int sum = 0;
		try{
			Connection conn = bd.getConn();
			String sql = "select count(*) from userInfo where userState=1";
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				sum +=rs.getInt(1);
			}
			
				
		}catch(SQLException e){
			e.printStackTrace();
		}
		return sum;
	}
	
	public static void main(String[] args){
		UserInfoDao ud = new UserInfoDao();
		System.out.println(ud.updateUser(null,2,"/order/photo/20170703190819_49.jpg" ,21 ));
		
		//System.out.println(ud.updateUser("2d86bdac01a3315b95794ffa7360edc3", 2, "default.jpg", 15));
		
		//System.out.println(ud.deleteUser(20));
	}

}
