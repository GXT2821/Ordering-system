package cn.sut.order.gt.servlet.login;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sut.order.gt.dao.UserInfoDao;
import cn.sut.order.gt.tool.MD5;
import cn.sut.order.gt.vo.UserInfo;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String userAccount = request.getParameter("userAccount");
		String userPass = MD5.getInstance().md5(request.getParameter("userPass"));
		UserInfoDao ud = new UserInfoDao();
		List<UserInfo> list = ud.login(userAccount, userPass);
		if(list.size()>0){
			request.getSession().setAttribute("ORDER_SYS_NAME", "点餐系统");
			if(list.get(0).getRole()==1){
				request.getSession().setAttribute("user", list.get(0));
				request.getRequestDispatcher("OrderInfoServlet").forward(request, response);
			}else if(list.get(0).getRole()==3){
				request.getSession().setAttribute("user", list.get(0));
				request.getRequestDispatcher("pages/waiters/takeorder.jsp").forward(request, response);
			}else{
				request.getSession().setAttribute("user", list.get(0));
				request.getRequestDispatcher("pages/kitchen/kitchenmain.jsp").forward(request, response);
			}
		}else{
			request.setAttribute("ERROR_MSG", "账户或密码错误");
			request.getRequestDispatcher("pages/login.jsp").forward(request, response);
		}
	}

}
