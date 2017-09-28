package cn.sut.order.gt.servlet.password;

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
import cn.sut.order.gt.tool.MD5;
import cn.sut.order.gt.vo.UserInfo;

/**
 * Servlet implementation class OldPassServlet
 */
@WebServlet("/OldPassServlet")
public class OldPassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OldPassServlet() {
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
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		String userPass = MD5.getInstance().md5(request.getParameter("userOldPass"));

		
		JSONObject json = new JSONObject();
		json.put("msg", "");
		
		UserInfoDao ud = new UserInfoDao();
		List<UserInfo> list = ud.SelectUserId(userId);
		if(list.get(0).getUserPass().equals(userPass)){
			json.put("msg", "OK");
		}else{
			json.put("msg", "NO");
		}
		
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}

}
