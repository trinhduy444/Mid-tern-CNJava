package com.example.SpringCommerceShop.Controller;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.example.SpringCommerceShop.Mapper.AccountMapper;
import com.example.SpringCommerceShop.Modal.*;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.SpringCommerceShop.Config.AES;
import com.example.SpringCommerceShop.Mapper.OrdersMapper;
import com.example.SpringCommerceShop.Mapper.ProductMapper;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

import jakarta.websocket.server.PathParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class HomePageController {
	
	@Autowired
	ProductMapper productMapper;
	
	@Autowired
	OrdersMapper ordersMapper;

	@Autowired
	AccountMapper accountMapper;

	private String secrectKey = "DuyDepTrai@123";

	private boolean isValidUser(String username, String password){

		String passDecode = AES.encrypt(password,secrectKey);

		if(accountMapper.isValidLogin(username).equals(passDecode)) {
			return true;
		}
		return false;
		
	}
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("login");
		return modelAndView;
	}

	@PostMapping("/loginSuccess")
	public ModelAndView login(@RequestParam("fullname") String fullname,@RequestParam("user_id") String user_id,
							  @RequestParam("email") String email,@RequestParam("password") String password) {
		ModelAndView modelAndView = new ModelAndView("login");
		String passwordEncode = AES.encrypt(password,secrectKey);
		Account account = new Account(user_id,fullname,email,passwordEncode,2);
		accountMapper.insert(account);
		return modelAndView;
	}



@PostMapping("/login")
public ModelAndView loginPost(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletResponse response, RedirectAttributes redirectAttributes){

	// process login
	if(isValidUser(email,password)){
		Cookie cookie = new Cookie("loginInfo", email);
		Cookie cookiepass = new Cookie("loginInfoPass", password);

		cookie.setMaxAge(60*60*24); // cookie will expire in 1 day
		cookiepass.setMaxAge(60*60*24); // cookie will expire in 1 day

		response.addCookie(cookie);
		response.addCookie(cookiepass);
		return new ModelAndView("redirect:/");
	}
	else {
		redirectAttributes.addFlashAttribute("error", "Invalid username or password");
		return new ModelAndView("redirect:/login");
	}
}


	@GetMapping("/register")
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView("register");

		return modelAndView;
	}
	@GetMapping("/")
	public ModelAndView index(@RequestParam(name = "page", defaultValue = "1")int pageNumber) {   
	    int pageSize = 8; // số lượng mục trên mỗi trang
	    int totalItems = productMapper.getCountProduct(); // tổng số lượng mục trong CSDL
	    int totalPages = (int) Math.ceil((double) totalItems / pageSize); // tính tổng số trang
	    
	    ModelAndView modelAndView = new ModelAndView("index");    
	    MyPageInfo myPageInfo = new MyPageInfo(pageNumber, pageSize, totalItems, totalPages); // tạo đối tượng MyPageInfo
	    modelAndView.addObject("myPageInfo",myPageInfo);
	    List<Map<String, Object>> listMapProduct = productMapper.getProductPage(myPageInfo);
	    modelAndView.addObject("products",listMapProduct);


	    return modelAndView;
	}
	@GetMapping("/detail/{productId}")
	public ModelAndView detailPage(@PathVariable String productId) {
		ModelAndView modelAndView = new ModelAndView("detail");

		
		ProductWithBLOBs listProductById = productMapper.selectByPrimaryKey(productId);
		
	    modelAndView.addObject("product",listProductById);

		return modelAndView;
	}
	
	@PostMapping("/purchaseSuccess")
	public ModelAndView purchaseSuccess(@RequestParam("order_id") String order_id,
			@RequestParam("fullname") String fullname,@RequestParam("address") String address,
			@RequestParam("email") String email,@RequestParam("phone") String phone,@RequestParam("totalPrice") String totalPrice) {
	    int total;
		try {
			total = Integer.parseInt(totalPrice);
		} catch (NumberFormatException e) {
			total = 0; // Nếu không chuyển đổi được, gán giá trị mặc định là 0
		}
		
		ModelAndView modelAndView = new ModelAndView("purchasePage");    
	    Orders orders = new Orders(order_id, fullname, address, phone, email, total);
	    ordersMapper.insertOrder(orders);
	    return modelAndView;
	}
	
	@GetMapping("/dashboard")
	public ModelAndView dashBoard(){

		return new ModelAndView("dashboard");
	}
	@GetMapping("/profile")
	public ModelAndView proFile(){

		return new ModelAndView("profile");
	}
	@GetMapping("/products/{category}")
	public ModelAndView getProductByCateGory(@PathVariable String category) {
		ModelAndView modelAndView = new ModelAndView("index");

		
		int cate=0;
		if(category.equals("food")) {
			cate =1;

		}
		else if(category.equals("drink")){
			cate = 2;
		}
		
		List<Map<String, Object>> listProductsCateList = productMapper.findByCategory(cate);
	    modelAndView.addObject("products",listProductsCateList);
		int pageNumber =1;
		int pageSize = 8; // số lượng mục trên mỗi trang
		int totalItems = productMapper.getCountProduct(); // tổng số lượng mục trong CSDL
		int totalPages = (int) Math.ceil((double) totalItems / pageSize); // tính tổng số trang
		MyPageInfo myPageInfo = new MyPageInfo(pageNumber, pageSize, totalItems, totalPages); // tạo đối tượng MyPageInfo
		modelAndView.addObject("myPageInfo",myPageInfo);
		
		return modelAndView;
	}
	
	@GetMapping("/cart")
	public ModelAndView cartPage() {
		ModelAndView modelAndView = new ModelAndView("cart");
		return modelAndView;
	}

	
	@GetMapping("/haha")
	public ModelAndView loginPage() {
		ModelAndView modelAndView = new ModelAndView("login");
		return modelAndView;
	}
	
	@GetMapping("/search")
	public ModelAndView searchByName(@RequestParam("name") String name) {
		ModelAndView modelAndView = new ModelAndView("index");
		List<Map<String, Object>> listProductList = productMapper.findByName(name);//Product duoc tim kiem boi name
		modelAndView.addObject("products",listProductList);
		
		int pageNumber =1;
	    int pageSize = 8; // số lượng mục trên mỗi trang
	    int totalItems = productMapper.getCountProduct(); // tổng số lượng mục trong CSDL
	    int totalPages = (int) Math.ceil((double) totalItems / pageSize); // tính tổng số trang
	    
	    MyPageInfo myPageInfo = new MyPageInfo(pageNumber, pageSize, totalItems, totalPages); // tạo đối tượng MyPageInfo
	    modelAndView.addObject("myPageInfo",myPageInfo);
		return modelAndView;
	}

}
