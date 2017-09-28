package cn.sut.order.gt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.sut.order.gt.vo.UserInfo;



public class LoginDao {
	public List<UserInfo> login(String name, String password){
		BaseDao bd = new BaseDao();
		List<UserInfo> list = new ArrayList<UserInfo>();
		try {
			Connection conn = bd.getConn();
			String sql = "select* from userinfo where userAccount=? and userPass = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			pstm.setString(2, password);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				UserInfo user = new UserInfo();
				/*user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setNumber(rs.getString(4));*/
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}
