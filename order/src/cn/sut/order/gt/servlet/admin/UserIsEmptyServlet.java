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
import cn.sut.order.gt.dao.UserInfoDao;
import cn.sut.order.gt.vo.UserInfo;

/**
 * Servlet implementation class UserIsEmptyServlet
 */
@WebServlet("/UserIsEmptyServlet")
public class UserIsEmptyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserIsEmptyServlet() {
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
		
		String userAccount = request.getParameter("userAccount");

		
		JSONObject json = new JSONObject();
		json.put("msg", "");
		
		UserInfoDao ud = new UserInfoDao();
		List<UserInfo> list = ud.SelectUserName(userAccount);
		if(list.size() > 0){
			json.put("msg", "NO");
		}else{
			json.put("msg", "OK");
		}
		
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
		
	}

}
