package myOrder.serlvet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import myOrder.dao.myOrderDao;
import myOrder.dao.shopDao;
import myOrder.vo.myOrderInfo;

/**
 * Servlet implementation class addorderserlvet
 */
@WebServlet("/addorderserlvet")
public class addorderserlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addorderserlvet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int tableId=Integer.parseInt(request.getParameter("tablenum"));
		int waiterId=Integer.parseInt(request.getParameter("waiterId"));
		System.out.print(tableId);
		myOrderDao m = new myOrderDao();
		shopDao s = new shopDao();
		JSONObject object=new JSONObject();
		object.put("myData", "");
		if(request.getParameter("tablenum")!=""){
			
			int orderId = m.selectorderEndDate(tableId);
			if(orderId==0){
				Date date=new Date();
				DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time=format.format(date);
				
				if(s.selectAllShopinfo().isEmpty()){
					object.put("myData","0");
					object.put("msg", "点餐车内未添加菜品!");
				}else{
				if(m.addorderinfo(time, waiterId, 0, tableId)){
					myOrderInfo myo = m.selectoneorder(time, waiterId, tableId);
					s.addorderdishes(myo.getOrderId());
					s.deletAllShopinfo();
					object.put("myData", "1");
					object.put("msg", "订单提交成功，用餐愉快！");
				}else{
					object.put("msg", "订单添加失败！");
					object.put("myData", "2");
					
				}
				}
			}else{
				Date date=new Date();
				DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time=format.format(date);
			//	int waiterId=Integer.parseInt(request.getParameter("waiterid"));
				if(s.selectAllShopinfo().isEmpty()){
					object.put("myData","0");
					object.put("msg", "点餐车内未添加菜品!");
				}else{
				if(m.updateexistorder(orderId, time)){
					s.addorderdishes(orderId);
					s.deletAllShopinfo();
					object.put("myData", "1");
					object.put("msg", "订单已更新成功，用餐愉快！");
				}else{
					object.put("msg", "订单更新失败！");
					object.put("myData", "2");
					
				}
				}
			}
		}
		PrintWriter out = response.getWriter();
		out.write(object.toString());
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
