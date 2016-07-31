package com.atguigu.crm.handler;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atguigu.crm.entity.User;
import com.atguigu.crm.service.UserService;

@Controller
public class UserHandler {
	@Autowired
	private UserService userService;
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	@RequestMapping(value="/user/shiro-login",method=RequestMethod.POST)
	public String login2(@RequestParam(value="name",required=false) String name,
			@RequestParam(value="password",required=false) String password,
			HttpSession session,
			RedirectAttributes attributes,
			Locale locale){
		// 获取当前 User: 调用了 SecurityUtils.getSubject() 方法. 
		Subject currentUser = SecurityUtils.getSubject();
		
		// 检测用户是否已经被认证. 即用户是否登录. 调用 Subject 的 isAuthenticated()
		if(!currentUser.isAuthenticated()){
			// 把用户名和密码封装为一个 UsernamePasswordToken 对象. 
			UsernamePasswordToken token = new UsernamePasswordToken(name, password);
			token.setRememberMe(true);
			try {
				// 执行登录. 调用 Subject 的 login(UsernamePasswordToken) 方法.
				currentUser.login(token);
			} catch (AuthenticationException e) {
				String code="error.user.login";
				String message=messageSource.getMessage(code, null, locale);
				attributes.addFlashAttribute("message", message);
				return "redirect:/index";
			}
		
		}
		//可以通过调用 Subject 的 .getPrincipals().getPrimaryPrincipal() 获取到
		//在 realm 中创建 SimpleAuthenticationInfo 对象时的 principal 实例.
		session.setAttribute("user",currentUser.getPrincipals().getPrimaryPrincipal());
		return "home/success";
		
	}
	
	/**
	 * 1.使用SpringMVC提供的RedirectAttributes API可以吧key-value对在重定向的情况下，在页面上给予显示
	 * ①在目标方法的参数中添加RedirectAttributes参数
	 * ②具体调用RedirectAttrubutes的addFlaseAttribute（key，value）来添加键值对。
	 * ③重定向到目标资源，但不能直接重定向到其物理页面，而需经过SpringMVC处理一下
	 * <mvc:view-controller path="/index" view-name="index"/>
	 * ④页面上使用javascript和JSTL标签结合来显示错误消息
	 * 
	 * 2.错误消息如何放在国际化资源文件中
	 * ①在 SpringMVC 中配置国际化资源文件
	 * 配置 org.springframework.context.support.ResourceBundleMessageSource bean. 
	 * 且 id 必须为 messageSource
	 * ②在类路径下新建国际化资源文件，加入key-value对
	 * ③在Handler中自动装配ResourceBundleMessageSource属性
	 * ④调用  getMessage(String code, Object[] args, Locale locale) 方法来获取国际化资源文件中的 value 值.
	 * ⑤ Locale 可以直接在目标方法中传入
	 * @param name
	 * @param password
	 * @param session
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value="/user/login",method=RequestMethod.POST)
	public String login(@RequestParam(value="name",required=false) String name,
			@RequestParam(value="password",required=false) String password,
			HttpSession session,
			RedirectAttributes attributes,
			Locale locale){
		User user=userService.login(name, password);
		if(user==null){
			String code="error.user.login";
			String message=messageSource.getMessage(code, null, locale);
			attributes.addFlashAttribute("message", message);
			return "redirect:/index";
		}
		session.setAttribute("user", user);
		return "home/success";
		
	}
	
	@RequestMapping("user/logout")
	public String logout(){
		return "home/index";
		
	}
}
