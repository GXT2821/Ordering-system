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
 * Servlet implementation class dstateserlvet
 */
@WebServlet("/dstateserlvet")
public class dstateserlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dstateserlvet() {
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
		odDao o = new odDao();
		int dstate=Integer.parseInt(request.getParameter("dstate"));
		int odId=Integer.parseInt(request.getParameter("odId"));
		System.out.print(dstate);
		System.out.print(odId);
		if(o.updatedstate(odId, dstate)){
			object.put("myData", "1");
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
		doGet(request, response);
	}

}
