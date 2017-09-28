package myOrder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import myOrder.vo.mydishes;

public class dishDao {
	public mydishes[] selectOrder(int pageIndex, int everyPageDataCount){
		mydishes[] dishesArr =null;
		List<mydishes> fur=new ArrayList<mydishes>();
    	BaseDao bd=new BaseDao();
    	try{
    		Connection conn=bd.getConn();
    		String sql="select * from dishesinfo where state = 1 ";
    		PreparedStatement pstmt=conn.prepareStatement(sql);
    		ResultSet rs=pstmt.executeQuery();
    		while(rs.next()){
    		    mydishes n = new mydishes();
    		    n.setDishesId(rs.getInt(1));
    		    n.setDishesName(rs.getString(2));
    		    n.setDishesDiscript(rs.getString(3));
    		    n.setDishesImg(rs.getString(4));
    		    n.setDishesTxt(rs.getString(5));
    		    n.setRecommend(rs.getInt(6));
    		    n.setDishesPrice(rs.getFloat(7));
    		    fur.add(n);    		
    		    }
    		conn.close();
    	}catch (SQLException e){
    		e.printStackTrace();
    	};
		if (fur.size() > 0) {
			// 根据list的大小分配实体类的长度
			int num=fur.size();
				dishesArr = new mydishes[fur.size()];
				// 给实体类数据赋值
				fur.toArray(dishesArr);
			if((fur.size() - everyPageDataCount) <= (pageIndex * everyPageDataCount)){
				mydishes[] need=new mydishes[fur.size() - (pageIndex * everyPageDataCount)];
				
				for(int i=pageIndex * everyPageDataCount,j=0;i<num;i++,j++)
				{
					need[j]=dishesArr[i];
				}
				return need;
			}else{
				mydishes[] need=new mydishes[everyPageDataCount];
				for(int i=pageIndex * everyPageDataCount,j=0;i<pageIndex * everyPageDataCount+everyPageDataCount;i++,j++)
				{
					need[j]=dishesArr[i];
				}
				return need;
			}
		}
		return null;
	}
	public mydishes getdish(int dishesId){
		BaseDao bd = new BaseDao();
		mydishes m = new mydishes();
		try{
			Connection conn = bd.getConn();
			String sql = "select * from dishesinfo where dishesId = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dishesId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				m.setDishesId(rs.getInt(1));
				m.setDishesName(rs.getString(2));
				m.setDishesDiscript(rs.getString(3));
				m.setDishesImg(rs.getString(4));
				m.setDishesTxt(rs.getString(5));
				m.setRecommend(rs.getInt(6));
				m.setDishesPrice(rs.getFloat(7));
			}
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		return m;
	}
	public int dishesnum(){
		BaseDao bd = new BaseDao();
		int num=0;
		try{
			Connection conn = bd.getConn();
			String sql = "select count(*) from dishesinfo where state=1";
			PreparedStatement pstmt=conn.prepareStatement(sql);
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
		dishDao id = new dishDao();
	//	System.out.print(id.addorderinfo("2017-06-28 10:15:31", 2, 0, 3));
		mydishes[] list = id.selectOrder(0, 4);
		for(mydishes d :list){
			System.out.println(d.getDishesDiscript());
		//	id.updateorderinfo(d.getOrderId(), "2017-06-28 14:31:00",1);
		}
	}
}
