package com.game.core;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteOrder;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.mina.core.buffer.IoBuffer;

import com.game.constant.D;
import com.game.exception.LogicException;

public class DecodeHelper {

	public static void decodeUrl(HttpServletRequest req) throws IOException {
		String qstr = req.getQueryString();
		if (qstr != null && !qstr.isEmpty()) {
			int p = qstr.indexOf("#");
			if (p > 0)
				qstr = qstr.substring(0, p);
		}
		decodeUrl(req, qstr);
	}

	@SuppressWarnings( { "unchecked", "deprecation" })
	public static void decodeUrl(HttpServletRequest req, String q)
			throws IOException {
		if (q == null)
			return;

		if (q.length() < 1)
			return;

		q = java.net.URLDecoder.decode(q, "UTF-16LE");
		
		Hashtable ht = javax.servlet.http.HttpUtils.parseQueryString(q);
		Enumeration keys = ht.keys();

		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			String[] value = (String[]) ht.get(key);
			if (value != null && value.length > 0) {
				if (value.length == 1)
					req.setAttribute(key, value[0]);
				else
					req.setAttribute(key, value);
			}
		}

	}

	@SuppressWarnings("unused")
	private static String s(byte[] a) throws UnsupportedEncodingException {
		byte[] b = new byte[(a.length + (a.length % 2)) + 2];
		b[0] = (byte) 0xFF;
		b[1] = (byte) 0xFE;
		System.arraycopy(a, 0, b, 2, a.length);
		return new String(b, "Unicode");
	}

	private static final int _MAXUPLOAD = 100 * 1024;
	private static final int _BUFFSIZE = 128 * 1024;
	public static int MAX_FILE_IN_MEMERY = 1024 * 1024 * 2;

	public static byte[] getPostBuffer(HttpServletRequest request) throws IOException {
		ServletInputStream in = request.getInputStream();
		byte[] b = new byte[_BUFFSIZE];
		int ret = in.read(b);
		if (ret != -1) {
			IoBuffer ioBuf = IoBuffer.wrap(b);
			ioBuf.order(ByteOrder.LITTLE_ENDIAN);
			int len = ioBuf.getInt();
			if (len > _MAXUPLOAD)
				return null;
			byte[] buff = new byte[len];
			ioBuf.get(buff);
			if (buff == null || buff.length < 1)
				return null;
			return buff;
		}
		return null;
	}

	public static Map<String, Object> checkGetPostBuffer(HttpServletRequest request) throws LogicException, IOException{
		//gzip解压并作数据处理
		byte[] bytes = DecodeHelper.getPostBuffer(request);
		if(bytes == null)
			throw new LogicException(D.CODE_PARAM_ERROR,"请求参数为空");
	
		Map<String, Object> postdataMap = BioHelper.mapFromBytes(bytes);
		if(postdataMap == null){ 
			throw new LogicException(D.CODE_PARAM_ERROR,"登陆参数为空");
		}
		return postdataMap;
	}
	
//	@SuppressWarnings("unchecked")
//	public static boolean decodePost(HttpServletRequest request) {
//
//		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//		String sys="";
//		String _qs=null;
//		try {
//
//			if (!isMultipart) {
//
//				decodeUrl(request);
//
//				byte[] buf = getPostBuffer(request);
//				if (buf != null) {
//					String queryString = new String(buf);
//					char[] _qss = Hex.encodeHex(buf);
//					_qs = new String(_qss);
//					sys=queryString;
//					decodeUrl(request, queryString);
//				}
//				return true;
//			}
//
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		} catch (Error er) {
//			er.printStackTrace();
//			return false;
//		}
//
//		return true;
//	}
}
