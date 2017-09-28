package myOrder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import myOrder.vo.myOrderInfo;
import myOrder.vo.orderdishes;

public class odDao {
	public boolean updatedstate(int odId,int dstate){
		BaseDao bd = new BaseDao(); 
		Connection conn = bd.getConn();
		try{
			String sql1 = "update orderdishes set dstate = ? where odId = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql1);
			if(dstate==0){
				pstmt.setInt(1, 1);
			}else if(dstate==1){
				pstmt.setInt(1, 2);
			}else if(dstate==2){
				pstmt.setInt(1, 3);
			}else{
				return false;
			}
			pstmt.setInt(2, odId);
			pstmt.executeUpdate();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		};
		return true;
	}
	public orderdishes[] selectkitchen(int pageIndex, int everyPageDataCount , int a[],String b[],String c[]){
		orderdishes[] odArr = null;
		myOrderDao m =new myOrderDao();
		List<orderdishes> fur = new ArrayList<orderdishes>();
		BaseDao bd = new BaseDao();   
		dishDao d = new dishDao();
		odDao o = new odDao();
		Connection conn = bd.getConn();
		try{
			String sql1 = "select * from orderdishes where dstate = 2";
			PreparedStatement pstmt = conn.prepareStatement(sql1);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				orderdishes n =new orderdishes();
				n.setOdId(rs.getInt(1));
				n.setOrderReference(rs.getInt(2));
				n.setDishes(rs.getInt(3));
				n.setNum(rs.getInt(4));
				n.setDstate(rs.getInt(5));
				if(m.dishesEndDate(rs.getInt(2))){
					fur.add(n);
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		};
		try{
			String sql2 = "select * from orderdishes where dstate = 1";
			PreparedStatement pst = conn.prepareStatement(sql2);
			ResultSet r = pst.executeQuery();
			while(r.next()){
				orderdishes n =new orderdishes();
				n.setOdId(r.getInt(1));
				n.setOrderReference(r.getInt(2));
				n.setDishes(r.getInt(3));
				n.setNum(r.getInt(4));
				n.setDstate(r.getInt(5));
				if(m.dishesEndDate(r.getInt(2))){
					fur.add(n);
				}
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		};
		try{
			String sql3 = "select * from orderdishes where dstate = 0";
			PreparedStatement pst = conn.prepareStatement(sql3);
			ResultSet rr = pst.executeQuery();
			while(rr.next()){
				orderdishes n =new orderdishes();
				n.setOdId(rr.getInt(1));
				n.setOrderReference(rr.getInt(2));
				n.setDishes(rr.getInt(3));
				n.setNum(rr.getInt(4));
				n.setDstate(rr.getInt(5));
				if(m.dishesEndDate(rr.getInt(2))){
					fur.add(n);
				}
			}
			conn.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		};
		if(fur.size()>0){
			int num = fur.size();
			odArr = new orderdishes[fur.size()];
			fur.toArray(odArr);
			if((fur.size() - everyPageDataCount) <= (pageIndex * everyPageDataCount)){
				orderdishes[] need=new orderdishes[fur.size() - (pageIndex * everyPageDataCount)];
				
				for(int i=pageIndex * everyPageDataCount,j=0;i<num;i++,j++)
				{
					need[j]=odArr[i];
					a[j]=m.selecttableId(odArr[i].getOrderReference());
					b[j]=d.getdish(odArr[i].getDishes()).getDishesName();
					c[j]=o.getalert(odArr[i].getDstate());
				}
				return need;
			}else{
				orderdishes[] need=new orderdishes[everyPageDataCount];
				for(int i=pageIndex * everyPageDataCount,j=0;i<pageIndex * everyPageDataCount+everyPageDataCount;i++,j++)
				{
					need[j]=odArr[i];
					a[j]=m.selecttableId(odArr[i].getOrderReference());
					b[j]=d.getdish(odArr[i].getDishes()).getDishesName();
					c[j]=o.getalert(odArr[i].getDstate());
				}
				return need;
			}
		}
		return null;
	}
	public String getalert(int a){
		String alert="";
		if(a==0){
			alert="等待烹饪中";
		}else if(a==1){
			alert="正在烹制";
		}else if(a==2){
			alert="菜品完成，已经通知服务员传菜";
		}else{
			alert="系统错误";
		}
		return alert;
	}
	public int odnum(){
		BaseDao b = new BaseDao();
		myOrderDao m =new myOrderDao();
		Connection co = b.getConn();
		int num=0;
		try{
			String sql1 = "select * from orderdishes where dstate = 2";
			PreparedStatement pstmt = co.prepareStatement(sql1);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				if(m.dishesEndDate(rs.getInt(2))){
					num++;
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		};
		try{
			String sql2 = "select * from orderdishes where dstate = 1";
			PreparedStatement pst = co.prepareStatement(sql2);
			ResultSet r = pst.executeQuery();
			while(r.next()){
				if(m.dishesEndDate(r.getInt(2))){
					num++;
				}
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		};
		try{
			String sql3 = "select * from orderdishes where dstate = 0";
			PreparedStatement pst = co.prepareStatement(sql3);
			ResultSet rr = pst.executeQuery();
			while(rr.next()){
				if(m.dishesEndDate(rr.getInt(2))){
					num++;
				}
			}
			co.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		};
		return num;
	}
	public static void main(String[] args){
		odDao id = new odDao();
		//	System.out.print(id.addorderinfo("2017-06-28 10:15:31", 2, 0, 3));
		   System.out.print( id.updatedstate(51, 0));
	}

}
