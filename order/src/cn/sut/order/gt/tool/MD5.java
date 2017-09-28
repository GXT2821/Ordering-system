package cn.sut.order.gt.tool;

import java.io.UnsupportedEncodingException;
import java.security.*;

import org.apache.log4j.Logger;

/**
 * <p>
 * Title: MD5
 * </p>
 * <p>
 * Description:������MD5����
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: ChinaSoft International Ltd.
 * </p>
 * 
 * @author etc
 * @version 1.0
 */
public class MD5 {
	protected Logger logger = Logger.getLogger(MD5.class.getName());
	private static MD5 md5;  
	private MD5(){}
	public static MD5 getInstance() {  
	    if (md5 == null) {  
	    	md5 = new MD5();  
	    }  
	    return md5;  
	}  
	public String md5(String str)  {
		if(str==null){
			return "";
		}
		// ��ȡժҪ����
		MessageDigest m = null;
		try {
			// MD5ժҪ����
			m = MessageDigest.getInstance("MD5");
			// ���±��ĸ�������λԪ��
			m.update(str.getBytes("UTF8"));
			// ����֧��ժҪ�쳣
		} catch (NoSuchAlgorithmException e) {
			logger.error(e);
			// ����һ��MD5��Ϣ�ĸ� ��ʱ�����
			e.printStackTrace();
			// ����֧���ַ����쳣
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
			// ���±��ĸ�������λԪ�� ��ʱ�����
			e.printStackTrace();
		}
		// ������ʹ��λԪ��ı�����������,Ȼ�������ժ����
		byte s[] = m.digest();
		// System.out.println(s); // ������ܺ��λԪ��
		// ��������ַ�������
		StringBuilder result = new StringBuilder("");
		// ������ժ
		for (int i = 0; i < s.length; i++) {
			// ����ʮ������ת��
			result.append(Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6));
		}
		// ���ؼ��ܽ��
		return result.toString();

	}
}