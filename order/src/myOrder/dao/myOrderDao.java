package myOrder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import myOrder.vo.myOrderInfo;



public class myOrderDao {
	public int selecttableId(int orderId){
		BaseDao b = new BaseDao();
		int tableId = 0;
		try{
    		Connection conn=b.getConn();
    		String sql="select * from orderinfo where orderId = ?";
    		PreparedStatement pstmt=conn.prepareStatement(sql);
    		pstmt.setInt(1, orderId);
    		ResultSet rs=pstmt.executeQuery();
    		while(rs.next()){
    		    tableId = rs.getInt(6);	
    		    }
    		conn.close();
    	}catch (SQLException e){
    		e.printStackTrace();
    	}
    	return tableId;
	}
	public myOrderInfo[] selectOrder(int pageIndex, int everyPageDataCount, int waiterId){
		myOrderInfo[] OrderinfoArr=null;
		myOrderDao m=new myOrderDao();
		List<myOrderInfo> result = m.selectAllOrderinfo(waiterId, 0);
		if (result.size() > 0) {
			// 根据list的大小分配实体类的长度
			int num=result.size();
				OrderinfoArr = new myOrderInfo[result.size()];
				// 给实体类数据赋值
				result.toArray(OrderinfoArr);
			if((result.size() - everyPageDataCount) <= (pageIndex * everyPageDataCount)){
				myOrderInfo[] need=new myOrderInfo[result.size() - (pageIndex * everyPageDataCount)];
				
				for(int i=pageIndex * everyPageDataCount,j=0;i<num;i++,j++)
				{
					need[j]=OrderinfoArr[i];
				}
				return need;
			}else{
				myOrderInfo[] need=new myOrderInfo[everyPageDataCount];
				for(int i=pageIndex * everyPageDataCount,j=0;i<pageIndex * everyPageDataCount+everyPageDataCount;i++,j++)
				{
					need[j]=OrderinfoArr[i];
				}
				return need;
			}
		}
		return null;
	}
	 public List<myOrderInfo> selectAllOrderinfo(int waiterId,int orderState){
	    	List<myOrderInfo> fur=new ArrayList<myOrderInfo>();
	    	BaseDao bd=new BaseDao();
	    	try{
	    		Connection conn=bd.getConn();
	    		String sql="select * from orderinfo where waiterId = ? and orderState = ?";
	    		PreparedStatement pstmt=conn.prepareStatement(sql);
	    		pstmt.setInt(1, waiterId);
	    		pstmt.setInt(2, orderState);
	    		ResultSet rs=pstmt.executeQuery();
	    		while(rs.next()){
	    		    myOrderInfo n = new myOrderInfo();
	    		    n.setOrderId(rs.getInt(1));
	    		    n.setOrderBeginDate(rs.getString(2));
	    		    n.setOrderEndDate(rs.getString(3));
	    		    n.setWaiterId(rs.getInt(4));
	    		    n.setOrderState(rs.getInt(5));
	    		    n.setTableId(rs.getInt(6));
	    		    fur.add(n);    		
	    		    }
	    		conn.close();
	    	}catch (SQLException e){
	    		e.printStackTrace();
	    	}
	    	return fur;
	    }
	 public boolean dishesEndDate(int orderId){
		 BaseDao b = new BaseDao();
		 int flag = 0;
		 try{
			 Connection con = b.getConn();
			 String sql = "select * from orderinfo where orderId = ?";
			 PreparedStatement pstmt = con.prepareStatement(sql);
			 pstmt.setInt(1, orderId);
			 ResultSet rs = pstmt.executeQuery();
			 while(rs.next()){
				 if(rs.getString(3)==null){
					 flag = 1;
				 }
			 }
			 con.close();
		 }catch(SQLException e){
			 e.printStackTrace();
		 }
		 if(flag == 1){
			 return true;
		 }else return false;
		 
	 }
	 public int selectorderEndDate(int tableId){
		 BaseDao b = new BaseDao();
		 int flag = 0;
		 try{
			 Connection con = b.getConn();
			 String sql = "select * from orderinfo where tableId = ?";
			 PreparedStatement pstmt = con.prepareStatement(sql);
			 pstmt.setInt(1, tableId);
			 ResultSet rs = pstmt.executeQuery();
			 while(rs.next()){
				 if(rs.getString(3)==null){
					 flag = rs.getInt(1);
					 break;
				 }
			 }
			 con.close();
			 
		 }catch(SQLException e){
			 e.printStackTrace();
		 }
		 return flag;
	 }
	public boolean addorderinfo(String orderBeginDate,int waiterId,int orderState,int tableId){
		BaseDao ad = new BaseDao();
		try{
			Connection con=ad.getConn();
			String sql="insert into orderinfo(orderBeginDate,waiterId,orderState,tableId) values(?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,orderBeginDate);
			ps.setInt(2, waiterId);
			ps.setInt(3, orderState);
			ps.setInt(4, tableId);
			ps.executeUpdate();
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public myOrderInfo selectoneorder(String orderBeginDate,int waiterId,int tableId){
		myOrderInfo n=new myOrderInfo();
    	BaseDao bd=new BaseDao();
    	try{
    		Connection conn=bd.getConn();
    		String sql="select * from orderinfo where orderBeginDate = ? and waiterId = ? and tableId = ?";
    		PreparedStatement pstmt=conn.prepareStatement(sql);
    		pstmt.setString(1, orderBeginDate);
    		pstmt.setInt(2, waiterId);
    		pstmt.setInt(3, tableId);
    		ResultSet rs=pstmt.executeQuery();
    		while(rs.next()){
    		    n.setOrderId(rs.getInt(1));
    		    n.setOrderBeginDate(rs.getString(2));
    		    n.setOrderEndDate(rs.getString(3));
    		    n.setWaiterId(rs.getInt(4));
    		    n.setOrderState(rs.getInt(5));
    		    n.setTableId(rs.getInt(6));    		
    		    }
    		conn.close();
    	}catch (SQLException e){
    		e.printStackTrace();
    	}
    	return n;
	}
	public boolean updateexistorder(int orderId,String orderBeginDate){
		BaseDao bd = new BaseDao();
		try{
			Connection con = bd.getConn();
			String sql = "update orderinfo set orderBeginDate = ? where orderId = ?";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, orderBeginDate);
			psmt.setInt(2, orderId);
			psmt.executeUpdate();
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;

	}
	public boolean updateorderinfo(int orderId,String orderEndDate,int orderState){
		BaseDao bd = new BaseDao();
		try{
			Connection con = bd.getConn();
			String sql = "update orderinfo set orderEndDate = ?,orderState= ? where orderId = ?";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, orderEndDate);
			psmt.setInt(2, orderState);
			psmt.setInt(3, orderId);
			psmt.executeUpdate();
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;

	}
	public int Ordernum(int waiterId,int orderState){
		BaseDao bd = new BaseDao();
		int num=0;
		try{
			Connection conn = bd.getConn();
			String sql = "select count(*) from orderinfo where waiterId = ? and orderState = ?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
    		pstmt.setInt(1, waiterId);
    		pstmt.setInt(2, orderState);
    		ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				num=rs.getInt(1);
			}
			conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		return num;
		}
	public static void main(String[] args){
		myOrderDao id = new myOrderDao();
	//	System.out.print(id.addorderinfo("2017-06-28 10:15:31", 2, 0, 3));
		myOrderInfo[] list = id.selectOrder(3, 3, 17);
		for(myOrderInfo d :list){
			System.out.println(d.getOrderBeginDate());
		//	id.updateorderinfo(d.getOrderId(), "2017-06-28 14:31:00",1);
		}
	}

}
