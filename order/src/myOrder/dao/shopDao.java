package myOrder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import myOrder.vo.shopping;

public class shopDao {
	public List<shopping> selectAllShopinfo(){
    	List<shopping> fur=new ArrayList<shopping>();
    	BaseDao bd=new BaseDao();
    	try{
    		Connection conn=bd.getConn();
    		String sql="select * from shopinfo ";
    		PreparedStatement pstmt=conn.prepareStatement(sql);
    		ResultSet rs=pstmt.executeQuery();
    		while(rs.next()){
    		    shopping n = new shopping();
    		    n.setShopId(rs.getInt(1));
    		    n.setDishesId(rs.getInt(2));
    		    n.setNum(rs.getInt(3));
    		    fur.add(n);   		
    		    }
    	}catch (SQLException e){
    		e.printStackTrace();
    	}
    	return fur;
    }
	public boolean deletAllShopinfo(){
		BaseDao bd = new BaseDao();
		try{
			Connection conn = bd.getConn();
			String sql="truncate table shopinfo";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean addorderdishes(int orderId){
		BaseDao bd = new BaseDao();
		shopDao s = new shopDao();
		try{
			Connection conn = bd.getConn();
            List<shopping> fur = s.selectAllShopinfo();
			
			for(shopping m :fur){
				String sql="insert into orderdishes(orderReference,dishes,num) values(?,?,?)";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, orderId);
				pstmt.setInt(2, m.getDishesId());
				pstmt.setInt(3, m.getNum());
				pstmt.executeUpdate();
			}    
			conn.close();
			
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean addshop(int dishesId,int num){
		BaseDao bd = new BaseDao();
		try{
			Connection conn = bd.getConn();
			String sql="insert into shopinfo(dishesId,num) values(?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dishesId);
			pstmt.setInt(2, num);
			pstmt.executeUpdate();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	

}
