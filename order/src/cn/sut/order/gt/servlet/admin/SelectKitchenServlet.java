package cn.sut.order.gt.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sut.order.gt.dao.UserInfoDao;
import cn.sut.order.gt.vo.UserInfo;

/**
 * Servlet implementation class SelectKitchenServlet
 */
@WebServlet("/SelectKitchenServlet")
public class SelectKitchenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectKitchenServlet() {
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
		
		UserInfoDao ud = new UserInfoDao();
		int count = ud.Lockedcount();
		int kitchencount = ud.Kitchencount();
		List<UserInfo> list = ud.SelectKitchen();
		request.setAttribute("count", count);
		request.setAttribute("kitchencount", kitchencount);
		request.setAttribute("list", list);
		request.getRequestDispatcher("pages/admin/onlinekitchens.jsp").forward(request, response);
	}

}
