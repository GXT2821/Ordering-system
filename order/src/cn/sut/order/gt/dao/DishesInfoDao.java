package cn.sut.order.gt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.sut.order.gt.vo.DishesInfo;


public class DishesInfoDao {
	public List<DishesInfo> SelectAllDishes(){   //查询所有菜品信息
		BaseDao bd = new BaseDao();
		List<DishesInfo> list = new ArrayList<DishesInfo>();
		try {
			Connection conn = bd.getConn();
			String sql = "select * from dishesInfo where state=1";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				DishesInfo order = new DishesInfo();
				order.setDishesId(rs.getInt(1));
				order.setDishesName(rs.getString(2));
				order.setDishesDiscript(rs.getString(3));
				order.setDeshesImg(rs.getString(4));
				order.setDishesTxt(rs.getString(5));
				order.setRecommend(rs.getInt(6));
				order.setDishesPrice(rs.getFloat(7));
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public List<DishesInfo> SelectDishes(int start, int end){
		BaseDao bd= new BaseDao();
		List<DishesInfo> list = new ArrayList<DishesInfo>();
		try {
			Connection conn = bd.getConn();
			String sql = "select * from dishesinfo  where state=1 order by dishesId desc limit ?,?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setObject(1,start);
			psmt.setObject(2,end);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()){
				DishesInfo order = new DishesInfo();
				order.setDishesId(rs.getInt(1));
				order.setDishesName(rs.getString(2));
				order.setDishesDiscript(rs.getString(3));
				order.setDeshesImg(rs.getString(4));
				order.setDishesTxt(rs.getString(5));
				order.setRecommend(rs.getInt(6));
				order.setDishesPrice(rs.getFloat(7));
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public List<DishesInfo> SelectAllRecommendDishes(){    //查询推荐菜品
		BaseDao bd = new BaseDao();
		List<DishesInfo> list = new ArrayList<DishesInfo>();
		try {
			Connection conn = bd.getConn();
			String sql = "select * from dishesInfo where recommend=1 and state=1";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				DishesInfo order = new DishesInfo();
				order.setDishesId(rs.getInt(1));
				order.setDishesName(rs.getString(2));
				order.setDishesDiscript(rs.getString(3));
				order.setDeshesImg(rs.getString(4));
				order.setDishesTxt(rs.getString(5));
				order.setRecommend(rs.getInt(6));
				order.setDishesPrice(rs.getFloat(7));
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public List<DishesInfo> SelectDishesById(int id){    //通过菜单号查询菜品信息
		BaseDao bd = new BaseDao();
		List<DishesInfo> list = new ArrayList<DishesInfo>();
		try {
			Connection conn = bd.getConn();
			String sql = "select * from dishesInfo where dishesid=? and state=1";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				DishesInfo order = new DishesInfo();
				order.setDishesId(rs.getInt(1));
				order.setDishesName(rs.getString(2));
				order.setDishesDiscript(rs.getString(3));
				order.setDeshesImg(rs.getString(4));
				order.setDishesTxt(rs.getString(5));
				order.setRecommend(rs.getInt(6));
				order.setDishesPrice(rs.getFloat(7));
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public List<DishesInfo> SelectDishesByName(String dishesName){    //通过菜单号查询菜品信息
		BaseDao bd = new BaseDao();
		List<DishesInfo> list = new ArrayList<DishesInfo>();
		try {
			Connection conn = bd.getConn();
			String sql = "select * from dishesInfo where dishesName=? and state=1";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, dishesName);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				DishesInfo order = new DishesInfo();
				order.setDishesId(rs.getInt(1));
				order.setDishesName(rs.getString(2));
				order.setDishesDiscript(rs.getString(3));
				order.setDeshesImg(rs.getString(4));
				order.setDishesTxt(rs.getString(5));
				order.setRecommend(rs.getInt(6));
				order.setDishesPrice(rs.getFloat(7));
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean deleteDishes(int id){    //删除菜品，将state置为0（0表示已删除，1表示未删除）
		BaseDao bd = new BaseDao();
		try{
			Connection conn = bd.getConn();
			String sql = "update dishesinfo set state=0 where dishesId=?";
			
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			int i = psmt.executeUpdate();
			
			if( i> 0){
				return true;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean UpdateDishes(int id, String dishesName, String dishesDiscript, String dishesTxt, int recommend, double dishesPrice, String dishesImg){    //修改菜品信息
		BaseDao bd = new BaseDao();
		try{
			Connection conn = bd.getConn();
			String sql = "update dishesinfo set dishesName=?, dishesDiscript=?, dishesTxt=?, recommend=?, dishesPrice=?, dishesImg=? where dishesId=?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, dishesName);
			psmt.setString(2, dishesDiscript);
			psmt.setString(3, dishesTxt);
			psmt.setInt(4, recommend);
			psmt.setDouble(5, dishesPrice);
			psmt.setString(6, dishesImg);
			psmt.setInt(7, id);
			int i = psmt.executeUpdate();
			
			if( i > 0){
				return true;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	public boolean addDishes(String dishesName, String dishesDiscript, String dishesImg, String dishesTxt, int recommend, double dishesPrice){  //添加新菜品
		BaseDao bd = new BaseDao();
		try{
			Connection conn = bd.getConn();
			String sql = "insert into dishesinfo (dishesName, dishesDiscript, dishesImg, dishesTxt, recommend, dishesPrice) values(?,?,?,?,?,?)";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, dishesName);
			psmt.setString(2, dishesDiscript);
			psmt.setString(3, dishesImg);
			psmt.setString(4, dishesTxt);
			psmt.setInt(5, recommend);
			psmt.setDouble(6, dishesPrice);
			int i = psmt.executeUpdate();
			
			if( i > 0){
				return true;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public int count(){
		BaseDao bd= new BaseDao();
		int sum = 0;
		try{
			Connection conn = bd.getConn();
			String sql = "select count(*) from dishesInfo where state=1";
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
		DishesInfoDao di = new DishesInfoDao();
		List<DishesInfo> list = di.SelectAllDishes();
		for(DishesInfo d : list){
			System.out.println(d.getDishesId() + "\t" + d.getDishesName() + "\t" 
					+ d.getDishesDiscript() + "\t" + d.getDeshesImg() + "\t" 
					+ d.getDishesTxt() + "\t" +d.getRecommend() + "\t" + d.getDishesPrice());
		}
		
		System.out.println(di.count());
		
		/*List<DishesInfo> list = di.SelectAllRecommendDishes();
		for(DishesInfo d : list){
			System.out.println(d.getDishesId() + "\t" + d.getDishesName() + "\t" 
					+ d.getDishesDiscript() + "\t" + d.getDeshesImg() + "\t" 
					+ d.getDishesTxt() + "\t" +d.getRecommend() + "\t" + d.getDishesPrice());
		}*/
		
		/*List<DishesInfo> list = di.SelectDishesById(1);
		for(DishesInfo d : list){
			System.out.println(d.getDishesId() + "\t" + d.getDishesName() + "\t" 
					+ d.getDishesDiscript() + "\t" + d.getDeshesImg() + "\t" 
					+ d.getDishesTxt() + "\t" +d.getRecommend() + "\t" + d.getDishesPrice());
		}*/
		
		System.out.println(di.deleteDishes(6));
		
		//System.out.println(di.addDishes("测试菜品", "测试", "1.jpg", "测试", 0, 23.8));
	}

}
