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
import cn.sut.order.gt.vo.AllUser;


/**
 * Servlet implementation class SelectUserInfoServlet
 */
@WebServlet("/SelectUserInfoServlet")
public class SelectUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectUserInfoServlet() {
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
		JSONObject json = new JSONObject();
		json.put("myDate", "");
		
		int pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
		int everyPageDataCount=Integer.parseInt(request.getParameter("everyPageDataCount"));
		UserInfoDao ud = new UserInfoDao();
		
		int intCount=ud.count();
		json.put("dataCount", intCount);
		
		int intallPage = 1;
		
		if ((intCount % everyPageDataCount) == 0) {
			intallPage = intCount / everyPageDataCount;
		} else {
			intallPage = intCount / everyPageDataCount + 1;
		}
		// 防止页码越界
		if (pageIndex < 0) {
			pageIndex = 0;
		} else if (pageIndex >= intallPage) {
			pageIndex = intallPage - 1;
		}
		json.put("pageIndex", pageIndex);
		
		List<AllUser> list= ud.SelectUser(Integer.valueOf(pageIndex * everyPageDataCount), Integer.valueOf(everyPageDataCount));
		AllUser[] usersDtoArr = null;
		if (list.size() > 0) {
			// 根据list的大小分配实体类的长度
			usersDtoArr = new AllUser[list.size()];
			// 给实体类数据赋值
			list.toArray(usersDtoArr);
			json.put("myData", usersDtoArr);
		}
		
		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		out.close();
	}

}
