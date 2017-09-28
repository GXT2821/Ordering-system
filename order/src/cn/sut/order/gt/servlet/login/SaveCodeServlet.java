package cn.sut.order.gt.servlet.login;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.sut.order.gt.tool.SaveCode;

/**
 * Servlet implementation class SaveCodeServlet
 */
@WebServlet("/SaveCodeServlet")
public class SaveCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SaveCode saveCode=new SaveCode("abcdefghijklmnopqrstuvwxyz123456789".toUpperCase().toCharArray(),
				100, 25, 6);
		// ͼƬ����Ҫ�������Ӧͷ
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		// ͼƬ����Ҫ�������Ӧͷ
		response.setDateHeader("Expires", 0);
		// ������ӦMIME����ΪJPEGͼƬ
		response.setContentType("image/jpeg");
		// ������֤��ͼƬ
		saveCode.createSaveCodeImage();
		// ��ȡ��֤��ͼƬ
		BufferedImage img = saveCode.getImage();
		// ��ȡ��֤���ַ���
		String codeString = saveCode.getCodeString();
		// ��ȡ�Ự����
		HttpSession session = request.getSession();
		// ����֤���ַ�������Ự
		session.setAttribute("checkCode", codeString);
		try {
			// ������ͼƬ����Ϊ����ͼƬ���ݲ�����Ӧ�������������ͻ���
			ImageIO.write(img, "JPEG", response.getOutputStream());
			// �����쳣
		} catch (Exception e) {

			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
