package com.hanchao.web.controller;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestIndexController {
	
	private static Logger logger = LoggerFactory.getLogger(TestIndexController.class); //slf4j
//	private static Log logger = LogFactory.getLog(TestIndexController.class); //commong-logging
	
	//@RequestMapping(value="/",method=RequestMethod.GET)
//	@RequestMapping("/")
	public String index() {
		System.out.println("Welcome to springMVC!!");
		//return "index";//这样就请求转发去了index.jsp中了
		/**注意事项：重定向到reidrect:/index时。必须有对应的RequestMapping("/index")*/
		return "redirect:/index"; //重定向到index.jsp
		//仅仅是跳转的话也可以这样：<mvc:view-controller path="/" view-name="index"/>
	}



	@RequestMapping("/index")
	public String toIndex() {
		System.out.println("去首页。。。。。。");
		logger.info(" ====== use logback ...");
		return "index";
		//说明：如果一个方法仅仅是为了跳转，那么我们可以直接在springmvc-servlet.xml中加上如下的配置即可：
		//<mvc:view-controller path="/index" view-name="index"/>
	}

	

	/**
	 * 放行 /url1/url2的请求
	 * hanchao
	 * 2013-03-04
	 */
	public String commonURL(
			@PathVariable("URL1") String URL1,
			@PathVariable("URL2") String URL2) {
		return "/" + URL1 + "/" + URL2;

	}

	/**
	 * 放行 /url1/url2/url3的请求
	 * hanchao
	 * 2013-03-04
	 */
	public String commonURL(
			@PathVariable("URL1") String URL1,
			@PathVariable("URL2") String URL2,
			@PathVariable("URL3") String URL3) {
		return "/" + URL1 + "/" + URL2 + "/" + URL3;
	}
	
	public static void main(String[] args) {
		logger.info("---");
	}
}
