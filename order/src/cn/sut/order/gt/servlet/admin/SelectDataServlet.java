package cn.sut.order.gt.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import cn.sut.order.gt.dao.OrderInfoDao;
import cn.sut.order.gt.vo.ModifyDeshes;
import cn.sut.order.gt.vo.PendingAccount;

/**
 * Servlet implementation class SelectDataServlet
 */
@WebServlet("/SelectDataServlet")
public class SelectDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		JSONObject json = new JSONObject();
		json.put("msg", "");
		
		OrderInfoDao od= new OrderInfoDao();
		List<PendingAccount> list = od.SelectAllOrderByDate(startDate, endDate);
		
		if (list.size() > 0 ) {
			PendingAccount[] usersDtoArr = new PendingAccount[list.size()];
			list.toArray(usersDtoArr);
			json.put("myData", usersDtoArr);
		}else{
			json.put("msg", "错误");
			
		}
		
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}

}
