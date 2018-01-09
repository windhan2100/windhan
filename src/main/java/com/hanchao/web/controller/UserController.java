package com.hanchao.web.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * springmvc测试用
 * @author liweihan
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	/**
	 * 1.说明：如果我们在类名上配置一个@RequestMapping("/user")的话
	 * 我们在每个方法上就不需要配置了@RequestMapping("/login")
	 * 否则，方法上需要这么配置：@RequestMapping("/user/login")
	 */
	
	/******************
	 * 用户登录			该例子涉及到表单提交传值，一种是表单中的值name="username"和User对象中的username属性名相同；
	 * 					另一种是和对象的属性没有关系的，比如：sex;;
	 * 					该例子还涉及到session的使用，当然，如果你想使用request,response，
	 * 					直接在参数中加HttpServletRequest request ,HttpServletResponse即可
	 * 
	 * 					如果我们要限制提交的方式：我们把@RequestMapping("/login")改成
	 * 					@RequestMapping(value="/login",method=RequestMethod.POST)
	 * hanchao
	 * 2013-03-04
	 * ***************
	 * 
	 */
	@RequestMapping("/login")
	public String login(User user,String sex,Model model,HttpSession session,HttpServletRequest request) {
		logger.info("页面传来的用户名：" + user.getUsername());
		logger.info("页面传来的性别：" + sex);
		
		//向页面传值的方式
		model.addAttribute("username", user.getUsername());
		model.addAttribute("sex", sex);
		
		//用session传值
		session.setAttribute("demo", "session传值");
		//request传值
		request.setAttribute("demoRequest", "request传值");
		//传一个对象到页面(传一个集合或者map是相同的方式)
		model.addAttribute("user", user);
		
		return "main";
		
//		return "redirect:/main"; 
		/**
		 * 这种重定向的跳转方式，不能传值model；
		 * 还必须要配置<mvc:view-controller path="/main" view-name="main"/>；
		 * 或者自己写一个跳转的方法；
		 */
	}
	
	/******************
	 * 用户登录			此例子用的ModelAndView向页面进行传值了
	 *                  并限制了，提交的方法为post;如果你把form表单的提交方式修改了就会报错了。
	 * 					和上面一个例子有异曲同工之处。
	 * hanchao
	 * 2013-03-04
	 * ***************
	 * 
	 * 
	 */
/*	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(User user,String sex) {
		ModelAndView mav = new ModelAndView();
		
		//要跳转的页面
		mav.setViewName("main");
		//向页面传值
		mav.addObject("username", user.getUsername());
		mav.addObject("sex", sex);
		//向要跳转的传个对象
		mav.addObject("user", user);
		
		return mav;
	}
*/

	/*****************
	 * 根据用户ID删除用户		此处的例子是url传值的方式的例子，
	 * 					/del/{id}中的id尽量和 @PathVariable String id中的id的值保持一致。
	 * hanchao
	 * 2013-03-04
	 * ***************
	 * 
	 * 
	 */
	@RequestMapping("/del/{id}")
	public String del(@PathVariable String id,Model model) {
		logger.info("要删除的用户的ID为：" + id);
		model.addAttribute("flag", "ok");
		
		//map向页面的传值的例子
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mapDemo", "demoMap");
		model.addAttribute("map", map);
		
		return "main";
	}
	
	/********************
	 * Ajax删除用户
	 * hanchao
	 * 2013-03-04
	 * ******************
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delajax/{id}")
	public @ResponseBody String delAjax(@PathVariable String id) {
		logger.info("要删除掉的用户的ID为：" + id);
		return "ok";
	}
	
	/**********************
	 * 修改用户
	 * hanchao
	 * 2013-03-04
	 * *******************
	 * @param id
	 * @return
	 */
	@RequestMapping("/update/{id}")
	public String update(@PathVariable String id) {
		logger.info("要修改用户的ID" + id);
		return "redirect:/success";
	}
	
	/******************
	 * 根据用户Id查找用户对象
	 * hanchao
	 * 2013-03-04
	 * ***************
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/show/{id}")
	public String show(@PathVariable String id,Model model) {
		logger.info("您要查找的用户的ID为：" + id);
		User user = new User();
		user.setId(Integer.parseInt(id));
		user.setUsername("jerry");
		user.setPassword("123456");
		model.addAttribute("user", user);
		
		return "success";
	}
	
	/******************
	 * ajax查找一个用户
	 * hanchao 
	 * 2013-03-04
	 * ***************
	 * @param id
	 * @return
	 */
	@RequestMapping("/showajax/{id}")
	@ResponseBody
	public User showajax(@PathVariable String id) {
		logger.info("要查找用户的Id为：" + id);
		User user = new User();
		user.setId(Integer.parseInt(id));
		user.setUsername("tom");
		user.setPassword("123456");
		user.setRegDate(new Date());
		
		//return null;
		return user;
	}
	
	/*****************
	 * 查询所有的用户 ===========分页
	 * hanchao
	 * 2013-03-04
	 * **************
	 * 
	 * @return
	 */
	@RequestMapping("/showall/{pageNo}")
	public String showAll(@PathVariable String pageNo,Model model) {
		System.out.println("当前页为：" + pageNo);
		//如果url不存在当前页数
		if(pageNo == null || "".equals(pageNo)) {
			pageNo = "1";
		}
		
		//判断当前页数是否为数字
		long currPage = 1;
		if(pageNo != null && !("".equals(pageNo))) {
			try {
				currPage = Long.parseLong(pageNo);
			} catch (Exception e) {
				e.printStackTrace();
				currPage = 1;
			}
		}
		
		/**
		 * 以下数据应该来自于service层了，此处只是为了测试
		 * 
		 * 100:代表总的条数;currPage代表当前页;3:每页显示的条数
		 * 
		 */
		Page<User> userPage = new Page<User>(100,currPage,3);
		List<User> userList = new ArrayList<User>();
		
		for (int i = 0; i < 3; i++) {
			User user = new User();
			user.setId(1);
			user.setUsername("tom");
			user.setPassword("123");
			user.setRegDate(new Date());
			userList.add(user);
		}
		
		userPage.setPageList(userList);
		model.addAttribute("page", userPage);
		model.addAttribute("listSize", userList.size());
		logger.info("总的记录数目" + userList.size());
		logger.info("每页显示条数：" + userPage.getPageSize());
		
		return "alluser";
	}
	
	/****************************************
	 * 一个简单的例子，当Map集合 key为一个对象时，我们应该如何取值
	 * hanchao
	 * 20130-03-04
	 * **************************************
	 * 
	 * 
	 * @return
	 */
	@RequestMapping("/getmap")
	public String getMap(Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key", "hanchao");
		
		User user = new User();
		user.setUsername("key");
		model.addAttribute("user", user);
		model.addAttribute("map", map);
		
		return "success";
	}
	
	/**
	 * 文件的上传
	 * 采用file.Transto 来保存上传的文件
	 * 其他方法可以参考：
	 * https://www.cnblogs.com/fjsnail/p/3491033.html
	 * http://blog.csdn.net/qian_ch/article/details/69258465
	 * @return
	 */
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public String upload(String decription ,
			@RequestParam MultipartFile myfile,
			HttpServletRequest request) {
		logger.info(" myfile isEmpty:{}",myfile.isEmpty());
		
		String path = request.getServletContext().getRealPath("/static/images/");
		logger.info("上传文件的路径名:{}",path);
		logger.info("=====:{}",request.getServletContext().getRealPath(""));
		
		
//		File file1 = new File("C:\\demo.jpg");
		File file1 = new File(path,"test.jpg");
		
		logger.info("fileName:" + myfile.getOriginalFilename());
		logger.info("fileSize:" + myfile.getSize());
		
		try {
			myfile.transferTo(file1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	/**
	 * 使用Ajax上传
	 * @param name
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/upload2",method=RequestMethod.POST)
	public Map<String, Object> upload2(
			String decription ,
			@RequestParam MultipartFile file,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (file.isEmpty()) {
			map.put("code", "101");
			map.put("msg", "上传文件不能为空");
			return map;
		}
		
		String path = request.getSession().getServletContext().getRealPath("/static/images/");
		logger.info("2上传文件的路径名:{}",path);
		logger.info("2=====:{}",request.getSession().getServletContext().getRealPath(""));
		
//		File file1 = new File("C:\\demo.jpg");
		String fileName = file.getOriginalFilename();
		File targetFile  = new File(path,fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		
		logger.info("fileName:" + fileName);
		logger.info("fileSize:" + file.getSize());
		
		//保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		map.put("code", "100");
		map.put("msg", "成功!");
		
		return map;
	}
	
	/***********************************************************/
	/**
	 * @RequestParam用于将请求参数区数据映射到功能处理方法的参数上 
	 * 请求中包含username参数（如/requestparam1?username=zhang），则自动传入。
	 * 此时,我们的访问链接中必须传入username参数
	 * eg:http://localhost:8080/user/request?username=韩超
	 * 否则,则会报404
	 * 
	 * @param username
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/request")
	public String requestParmam(@RequestParam("username") String username) {
		logger.info(" ====== username:{}",username);
		return "好的!";
	}
	
	/**
	 * value：参数名字，即入参的请求参数名字，如username表示请求的参数区中的名字为username的参数的值将传入； 
	 * required：是否必须，默认是true，表示请求中一定要有相应的参数，否则将报404错误码；
	 *          表示请求中可以没有名字为username的参数，如果没有默认为null，
	 * eg:http://localhost:8080/user/request2
	 * @param username
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/request2")
	public String requestParmam2(@RequestParam(value = "username",required = false) 
								 	String username) {
		logger.info(" ====== username:{}",username);
		return "好的!";
	}
	
	/**
	 *     原子类型：必须有值，否则抛出异常，如果允许空值请使用包装类代替。 
    	   Boolean包装类型类型：默认Boolean.FALSE，其他引用类型默认为null。 
    	   eg:http://localhost:8080/user/request3?username=3
	 * @param username
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/request3")
	public String requestParmam3(@RequestParam(value = "username",required = false) 
									int username) {
		logger.info(" ====== username:{}",username);
		return "好的!";
	}
	
	/**
	 * 表示如果请求中没有名字为username的参数，默认值为“defaultValue的值”。 
	 * eg:http://localhost:8080/user/request4
	 * @param username
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/request4")
	public String requestParmam4(@RequestParam(value = "username",
									required = false,defaultValue = "我好笨") 
								 	String username) {
		logger.info(" ====== username:{}",username);
		return "好的!";
	}
	
	/**
	 * 如果请求中有多个同名的应该如何接收呢？如给用户授权时，可能授予多个权限
	 * eg:http://localhost:8080/user/request5?role=admin&role=user&role=roleA
	 * 打印结果：role:admin,user,roleA
	 * 即：多个数据之间使用“，”分割。
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/request5")
	public String requestParam5(@RequestParam(value="role") String roleList) {
		logger.info("role:{}",roleList);
		return "测试多个同名的参数";
	}
	
	/**
	 * 如果请求中有多个同名的应该如何接收呢？如给用户授权时，可能授予多个权限
	 * eg:http://localhost:8080/user/request51?role=admin&role=user&role=roleA
	 * 打印结果：role:[admin, user, roleA]
	 * 即：结果是一个数组对象
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/request51")
	public String requestParam51(@RequestParam(value="role") String[] roleList) {
		logger.info("role:"+Arrays.toString(roleList));
		logger.info("role:"+Arrays.asList(roleList));
		return "测试多个同名的参数String[]";
	}
	
	/**
	 * 如果请求中有多个同名的应该如何接收呢？如给用户授权时，可能授予多个权限
	 * eg:http://localhost:8080/user/request52?role=admin&role=user&role=roleA
	 * 打印结果：role:[admin, user, roleA]
	 * 即：结果是一个List<String>的集合
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/request52")
	public String requestParam52(@RequestParam(value="role") List<String> roleList) {
		logger.info("role:"+roleList);
		return "测试多个同名的参数role:[admin, user, roleA]";
	}
	
	
}
