package com.xt8.action;

import static com.xt8.util.Constants.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.xt8.form.UserVO;
import com.xt8.model.Image;
import com.xt8.model.User;
import com.xt8.service.ImageService;
import com.xt8.service.UserService;
import com.xt8.util.EnableLog;

public class UserAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {

	// private Logger logger =
	// org.apache.log4j.Logger.getLogger(UserAction.class);

	@Resource
	private UserService userService;
	@Resource
	private ImageService imageService;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session = null;

	private UserVO user;

	private JSONObject json = new JSONObject();
	private PrintWriter out = null;

	public UserAction() {
		System.out.println("initialize LoginAction......");
	}

	public String login() {
		if (isValidateForm()) {
			User u = user.getUser4Login();
			User quser = userService.checkUser(u);// 通过手机号检索用户
			if (null == quser) {
				// 没有查询到用户，说明该手机号尚未注册
				json.put(STATUS, -1);
				json.put(MESSAGE, "the phone number have not been registered");
				json.put(USERINFO, null);

			} else {
				// 查询到用户，说明该手机号已注册
				if (u.getPlainPassword().equals(quser.getPlainPassword())) {
					// 密码验证通过
					Image img = imageService.findById(quser.getAvatar());
					json.put(STATUS, 1);
					json.put(MESSAGE, "login success");
					json.put(USERINFO, UserVO.genResponseJson4Login(quser, img));
				} else {
					// 密码错误
					json.put(STATUS, -2);
					json.put(MESSAGE, "wrong password!");
					json.put(USERINFO, null);
				}
			}
		} else {
			// 提交参数无效
			json.put(STATUS, -2);
			json.put(MESSAGE, "invalid parameters!");
			json.put(USERINFO, null);
		}

		String jsonStr = json.toString();
		out.print(jsonStr);
		out.flush();
		return null;

	}

	public String logout() {
		if (session.getAttribute(LOGINED_USER) != null) {
			session.removeAttribute(LOGINED_USER);
		}
		json.put(STATUS, 1);
		json.put(MESSAGE, "logout success");
		String jsonStr = json.toString();
		out.print(jsonStr);
		out.flush();
		return null;
	}

	public String register() {
		if (isValidateForm()) {
			User u = user.getUser4Register();
			User quser = userService.checkUser(u);// 通过手机号检索用户
			if (null == quser) {
				// 没有查询到用户，说明该手机号尚未注册
				String apiKey = "";// apiKey有待生成
				String heartbeatNumber = "";// 心跳号有待生成
				u.setRegisterDate(new java.util.Date());
				u.setApiKey(apiKey);
				u.setHeartbeatNumber(heartbeatNumber);
				Serializable userId = userService.save(u);// 注册该用户
				u = userService.findById(userId);
				Image img = new Image();
				img.setImagePath(user.getAvatar());// 将用户头像图片添加到数据库
				img.setUser(u);
				Integer imgId = (Integer) imageService.save(img);
				//u.setAvatar(imgId);
				userService.update(u);// 更新用户头像
				json.put(STATUS, 1);
				json.put(MESSAGE, "register success");
				json.put(USERINFO, UserVO.genResponseJson4Register(u, img));

			} else {
				// 查询到用户，说明该手机号已被注册
				json.put(STATUS, -1);
				json.put(MESSAGE, "the phone number have been occupid!");
				json.put(USERINFO, null);
			}
		} else {
			// 提交参数无效
			json.put(STATUS, -2);
			json.put(MESSAGE, "invalid parameters!");
			json.put(USERINFO, null);
		}

		String jsonStr = json.toString();
		out.print(jsonStr);
		out.flush();
		return null;
	}

	private boolean isValidateForm() {
		if (null == user) {
			return false;
		}
		if (null == user.getPhone() || 0 == user.getPhone().trim().length()) {
			return false;
		}
		if (null == user.getPassword()
				|| 0 == user.getPassword().trim().length()) {
			return false;
		}
		return true;
	}

	@Override
	public void validate() {
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		this.response.setCharacterEncoding("utf-8");
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		try {
			this.request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		this.setSession(this.request.getSession());
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public HttpSession getSession() {
		return session;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}
}
