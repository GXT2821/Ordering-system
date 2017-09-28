package myOrder.serlvet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myOrder.dao.odDao;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class kitchenserlvet
 */
@WebServlet("/kitchenserlvet")
public class kitchenserlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public kitchenserlvet() {
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
		JSONObject object=new JSONObject();
		object.put("myData", "");
		int pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
		int everyPageDataCount=Integer.parseInt(request.getParameter("everyPageDataCount"));
		odDao pd=new odDao();
	//	int waiterId=Integer.parseInt(request.getParameter("id"));
		int[] table=new int[everyPageDataCount];
		String[] dishname=new String[everyPageDataCount];
		String[] alert = new String[everyPageDataCount];
		int intCount=pd.odnum();
		object.put("dataCount", intCount);
		int intallPage = 1;
		// 计算总页数
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
			//	System.out.print(pd.selectPage(pageIndex * everyPageDataCount, everyPageDataCount));
				object.put("pageIndex", pageIndex);
				object.put("myData",pd.selectkitchen(pageIndex, everyPageDataCount,table,dishname,alert));
				object.put("tableId", table);
				object.put("dishname", dishname);
				object.put("alert", alert);
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
		doGet(request, response);
	}

}
