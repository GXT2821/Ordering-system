package cn.sut.order.gt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.sut.order.gt.vo.ModifyDeshes;
import cn.sut.order.gt.vo.PendingAccount;

public class OrderInfoDao {
	public List<PendingAccount> SelectAllOrder(){  //查询所有待结账的订单
		BaseDao bd = new BaseDao();
		List<PendingAccount> list = new ArrayList<PendingAccount>();
		try {
			Connection conn = bd.getConn();
			String sql = "select orderId, tableId, orderBeginDate, orderEndDate, sum(num*dishesPrice), userAccount" 
					+" from orderinfo, orderdishes, dishesinfo, userinfo" 
					+" where orderState='1' and delorder='0' and orderId=orderReference and dishes=dishesId and waiterId=userId"
					+" group by orderId";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				PendingAccount order = new PendingAccount();
				order.setOrderId(rs.getInt(1));
				order.setTableId(rs.getInt(2));
				order.setOrderBeginDate(rs.getString(3));
				order.setOrderEndDate(rs.getString(4));
				order.setTotal(rs.getDouble(5));
				order.setUserAccount(rs.getString(6));
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public List<PendingAccount> SelectAllOrderByDate(String startDate, String endDate){    //通过时间查询所有的订单
		BaseDao bd = new BaseDao();
		List<PendingAccount> list = new ArrayList<PendingAccount>();
		try {
			Connection conn = bd.getConn();
			String sql = "select orderId, tableId, orderBeginDate, orderEndDate, sum(num*dishesPrice), userAccount" 
					+" from orderinfo, orderdishes, dishesinfo, userinfo" 
					+" where orderState='2' and delorder='0' and orderId=orderReference and dishes=dishesId and waiterId=userId and orderEndDate between ? and ?"
					+" group by orderId";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, startDate);
			pstm.setString(2, endDate);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				PendingAccount order = new PendingAccount();
				order.setOrderId(rs.getInt(1));
				order.setTableId(rs.getInt(2));
				order.setOrderBeginDate(rs.getString(3));
				order.setOrderEndDate(rs.getString(4));
				order.setTotal(rs.getDouble(5));
				order.setUserAccount(rs.getString(6));
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public List<PendingAccount> SelectOrderById(int id){   //通过订单号查询订单信息
		BaseDao bd = new BaseDao();
		List<PendingAccount> list = new ArrayList<PendingAccount>();
		try {
			Connection conn = bd.getConn();
			String sql = "select orderId, tableId, orderBeginDate, orderEndDate, sum(num*dishesPrice), userAccount" 
					+" from orderinfo, orderdishes, dishesinfo, userinfo" 
					+" where  orderId=? and orderState='1' and orderId=orderReference and dishes=dishesId and waiterId=userId"
					+" group by orderId";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				PendingAccount order = new PendingAccount();
				order.setOrderId(rs.getInt(1));
				order.setTableId(rs.getInt(2));
				order.setOrderBeginDate(rs.getString(3));
				order.setOrderEndDate(rs.getString(4));
				order.setTotal(rs.getDouble(5));
				order.setUserAccount(rs.getString(6));
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public List<PendingAccount> SelectOrderedById(int id){   //通过订单号查询订单信息
		BaseDao bd = new BaseDao();
		List<PendingAccount> list = new ArrayList<PendingAccount>();
		try {
			Connection conn = bd.getConn();
			String sql = "select orderId, tableId, orderBeginDate, orderEndDate, sum(num*dishesPrice), userAccount" 
					+" from orderinfo, orderdishes, dishesinfo, userinfo" 
					+" where  orderId=? and orderState='2' and orderId=orderReference and dishes=dishesId and waiterId=userId"
					+" group by orderId";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				PendingAccount order = new PendingAccount();
				order.setOrderId(rs.getInt(1));
				order.setTableId(rs.getInt(2));
				order.setOrderBeginDate(rs.getString(3));
				order.setOrderEndDate(rs.getString(4));
				order.setTotal(rs.getDouble(5));
				order.setUserAccount(rs.getString(6));
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<ModifyDeshes> SelectAllDishesByOrderId(int id){    //通过订单号查询该订单的菜品信息
		BaseDao bd = new BaseDao();
		List<ModifyDeshes> list = new ArrayList<ModifyDeshes>();
		try {
			Connection conn = bd.getConn();
			String sql = "select dishesName, num, dishesPrice" 
					+" from orderdishes, dishesinfo" 
					+" where orderReference=? and dishes=dishesId";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				ModifyDeshes order = new ModifyDeshes();
				order.setDishesName(rs.getString(1));
				order.setNum(rs.getInt(2));
				order.setDishesPrice(rs.getDouble(3));
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean deleteOrder(int id){    //删除订单，将数据库中delorder状态置为1（0表示未删除， 1表示已删除）
		BaseDao bd = new BaseDao();
		try{
			Connection conn = bd.getConn();
			String sql= "update orderinfo set delorder=1 where orderid=?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			int j = psmt.executeUpdate();
			
			if(j > 0){
				return true;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateOrder(int id){      //确认 结账，将orderState置为2（0表示未结账，1表示待结账，2表示已结账）
		BaseDao bd = new BaseDao();
		try{
			Connection conn = bd.getConn();
			String sql = "update orderinfo set orderState=2 where orderId=?";
			
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			int i = psmt.executeUpdate();
			
			if( i > 0){
				return true;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}

	
	public static void main(String[] args){
		OrderInfoDao of = new OrderInfoDao();
		List<PendingAccount> list = of.SelectOrderById(14);
		for(PendingAccount p : list){
			System.out.println(p.getOrderId() + "\t" +p.getTableId() + "\t" + p.getOrderBeginDate() + "\t" + p.getOrderEndDate() + "\t" + p.getTotal() + "\t" + p.getUserAccount());
		}
		
		List<ModifyDeshes> lists = of.SelectAllDishesByOrderId(14);
		for(ModifyDeshes m : lists){
			System.out.println(m.getDishesName() + "\t" +m.getNum() + "\t" + m.getDishesPrice());
		}
		
		//System.out.println(of.deleteOrder(20));
		//System.out.println(of.updateOrder(7));
		
		/*List<PendingAccount> lists = of.SelectAllOrderByDate("2015-09-09 11:49:57", "2015-10-12 12:42:36");
		for(PendingAccount m : lists){
			System.out.println(m.getTableId() + "\t" +m.getOrderEndDate() + "\t" + m.getUserAccount() + "\t" + m.getTotal());
		}*/
	}
}
