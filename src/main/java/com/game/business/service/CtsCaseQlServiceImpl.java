package com.game.business.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.base.util.ImageUtil;
import com.game.business.mapper.CtsCaseQlMapper;
import com.game.business.model.Admin;
import com.game.business.model.CtsCaseQl;
import com.game.game.mapper.GameCtsCaseQlMapper;




/** 
 * <li>ClassName:CtsCaseQlServiceImpl <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2016年9月21日 <br/> 
 * <li>@author   zzy       
 */
@Service
public class CtsCaseQlServiceImpl implements CtsCaseQlService{
	
	@Autowired 
	private CtsCaseQlMapper ctsCaseQlMapper;
	
	public int addCtsCaseQl(CtsCaseQl cases) {
		return ctsCaseQlMapper.addCtsCaseQl(cases);
	}

	public List<CtsCaseQl> getCtsCaseQL(Map<String,Object> map) {
		return ctsCaseQlMapper.getCtsCaseQl(map);
	}
	
	public String login(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException{
		req.setCharacterEncoding("utf-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String code = req.getParameter("code");
		code.toUpperCase();
		//验证验证码
		HttpSession session = req.getSession();
		String imgcode = (String) session.getAttribute("imgcode");
		if(code == null || !imgcode.equalsIgnoreCase(code)){
			req.setAttribute("codeerror", "验证码错误");
		}
		Admin admin = ctsCaseQlMapper.findByName(username);
		//System.out.println(admin);
		if(admin == null){
			req.setAttribute("nameerror", "用户名不存在！");
			return "login";
		}else if(!admin.getPassword().equals(password)){
			req.setAttribute("pwderror", "密码错误！");
			return "login";
		}else{
			session.setAttribute("admin", username);
			return "index";
		}
		
	}
	
	//生成验证码
	public void createImg(HttpServletRequest req,HttpServletResponse res)
		throws ServletException,IOException{
		//生成验证码及图片
		Object[] objects = ImageUtil.createImage();
		String imgcode = (String)objects[0];
		//将验证码存入session
		HttpSession session = req.getSession();
		session.setAttribute("imgcode", imgcode);
		//将图片输出给浏览器
		res.setContentType("image/png");
		OutputStream os = res.getOutputStream();
		BufferedImage image = (BufferedImage)objects[1];
		ImageIO.write(image, "png", os);
		os.close();
	}
	
	//注册
	public String regist(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		req.setCharacterEncoding("utf-8");
		String username = req.getParameter("username");
		Admin admin1 = new Admin();
		if(username != null){
			admin1 = ctsCaseQlMapper.findByName(username);
		}
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String[] intersts = req.getParameterValues("intersts");
		String phoneCode = req.getParameter("phoneCode");
		String address = req.getParameter("address");
		String code = req.getParameter("code");
		code.toUpperCase();
		
		String usernameRgx = "^[\u4e00-\u9fa5_a-zA-Z0-9]{2,10}$";
		String passwordRgx = "^[a-zA-Z0-9_]{6,10}$";
		String emailRgx =  "^[a-zA-Z0-9_.-]+@([a-zA-Z0-9-]+\\.)+[a-zA-Z0-9]{2,4}$";
		String phoneCodeRgx = "^[1]([3-9][0-9]{1}|59|58|88|89)[0-9]{8}$";
		int count = 0;
		//验证用户名格式
		if(!username.matches(usernameRgx) || admin1 != null){
			req.setAttribute("nameError", "格式不正确或用户名已被注册！");
			count++;
		}
		//验证密码格式
		if(!password.matches(passwordRgx)){
			req.setAttribute("passwordError", "密码格式不正确！");
			count++;
		}
		//验证邮箱格式
		if(!email.matches(emailRgx)){
			req.setAttribute("emailError", "邮箱格式不正确！");
			count++;
		}
		//验证电话格式
		if(!phoneCode.matches(phoneCodeRgx)){
			req.setAttribute("phoneCodeError", "电话号码格式不正确！");
			count++;
		}
		//验证兴趣爱好是否为空
		if(intersts == null){
			req.setAttribute("interstError", "兴趣不能为空！");
			count++;
		}
		//验证地址是否为空
		if(address == ""){
			req.setAttribute("addressError", "地址不能为空！");
			count++;
		}
		//验证验证码
		HttpSession session = req.getSession();
		String imgcode = (String) session.getAttribute("imgcode");
		if(code == null || !imgcode.equalsIgnoreCase(code)){
			req.setAttribute("codeError", "验证码错误");
			return "regist";
		}
		if(count != 0){
			return "regist";
		}else{
			Admin admin = new Admin();
			String interst = intersts[0];
			for(int i = 1 ;i<intersts.length;i++){
				interst += ","+intersts[i]; 
			}
			admin.setName(username);
			admin.setPassword(password);
			admin.setEmail(email);
			admin.setInterest(interst);
			admin.setPhoneCode(phoneCode);
			admin.setAddress(address);
			ctsCaseQlMapper.save(admin);
			return "management";
		}
	}
	
}






