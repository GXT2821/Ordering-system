package cn.sut.order.gt.servlet.admin;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.sut.order.gt.dao.DishesInfoDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Servlet implementation class FileServlet
 */
@WebServlet("/FileServlet")
public class FileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileServlet() {
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
		response.setContentType("text/html;charset=UTF-8");
		//json对象
		JSONObject json = new JSONObject();
		//返回休息设置为空字符串（如果最后返回的是空字符串，说明上传成功）
		json.put("message","");
		json.put("url","");
		//最终结果为true说明上传完成
		boolean glStrError = true;
		// 文件保存目录路径
		String savePath = request.getServletContext().getRealPath("/") + "photo/";
		// 文件保存目录URL
		String saveUrl = request.getContextPath() + "/photo/";

		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");

		// 最大文件大小
		long maxSize = 4000000;
		File uploadDir = new File(savePath);
		
		// 检查目录，没有就创建目录
		if (!uploadDir.exists() && !uploadDir.isDirectory()) {
			uploadDir.mkdirs();
		}
		//String dirName="image";
		if (!ServletFileUpload.isMultipartContent(request)) {//判断是否选择了上传的头像
			json.put("message", "请选择头像。");
			
		}  else if (!uploadDir.canWrite()) {// 检查目录写权限
			json.put("message", "上传目录没有写权限。");
		} 
		else {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			//设置编码避免中文乱码
			upload.setHeaderEncoding("UTF-8");
			try {
				
				List<?> items = upload.parseRequest(request);
				Iterator<?> itr = items.iterator();
				while (itr.hasNext()) {
					FileItem item = (FileItem) itr.next();
					String fileName = item.getName();
					if (!item.isFormField()) {
						// 检查扩展名
						String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
						// 检查文件大小
						if (item.getSize() > maxSize) {
						
							json.put("message", "上传文件大小超过限制。");
						} else if (!Arrays.<String> asList(extMap.get("image").split(",")).contains(fileExt)) {
							json.put("message", "上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get("image") + "格式。");
						} else {
							//对上传文件重新命名（防止文件名重复）（同时也是对时间类型的处理）
							/**
							 * //对获取的时间进行格式化
							 *SimpleDateFormat df11 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							 *String newFileName11 = df11.format(new Date());
							 *System.out.println(newFileName11);
							 */
							SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
							String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "."
									+ fileExt;
							try {
								//上传文件
								File uploadedFile = new File(savePath, newFileName);
								item.write(uploadedFile);
							} catch (Exception e) {
								glStrError = false;
								json.put("message", "上传文件失败。");
							}
							if (glStrError) {
								/*把url插入用户对应的数据库表（略 自行处理）
								 * 当用户再次登录的时候，判断是否有头像
								 * 如果有头像取出响应给客户端
								 * 客户端把URL放入到img的src属性显示头像
								 * */
								String url= saveUrl + newFileName;
								json.put("url",url);
									
							}
						}
					}
					
				}
			} catch (FileUploadException e1) {
				// TODO Auto-generated catch block
				e1.getStackTrace();
			}
		}
		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		out.close();
	}

}
