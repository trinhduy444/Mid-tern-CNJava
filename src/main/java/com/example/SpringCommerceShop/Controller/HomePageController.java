package com.example.SpringCommerceShop.Controller;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.SpringCommerceShop.Config.AES;
import com.example.SpringCommerceShop.Mapper.OrdersMapper;
import com.example.SpringCommerceShop.Mapper.ProductMapper;
import com.example.SpringCommerceShop.Modal.MyPageInfo;
import com.example.SpringCommerceShop.Modal.Orders;
import com.example.SpringCommerceShop.Modal.Product;
import com.example.SpringCommerceShop.Modal.ProductExample;
import com.example.SpringCommerceShop.Modal.ProductWithBLOBs;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

import jakarta.websocket.server.PathParam;


@Controller
public class HomePageController {
	
	@Autowired
	ProductMapper productMapper;
	
	@Autowired
	OrdersMapper ordersMapper;
	
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("login");
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
	
	
	@GetMapping("/products/{category}")
	public ModelAndView getProductByCateGory(@PathVariable String category) {
		ModelAndView modelAndView = new ModelAndView("index");
		System.out.println("Cate:"+category);
		
		int cate=0;
		if(category.equals("food")) {
			cate =1;

		}
		else if(category.equals("drink")){
			cate = 2;
		}
		
		System.out.println(cate);
		List<Map<String, Object>> listProductsCateList = productMapper.findByCategory(cate);
	    modelAndView.addObject("products",listProductsCateList);
//		int pageNumber =1;
//		int pageSize = 8; // số lượng mục trên mỗi trang
//		int totalItems = productMapper.getCountProduct(); // tổng số lượng mục trong CSDL
//		int totalPages = (int) Math.ceil((double) totalItems / pageSize); // tính tổng số trang
//		MyPageInfo myPageInfo = new MyPageInfo(pageNumber, pageSize, totalItems, totalPages); // tạo đối tượng MyPageInfo
//		modelAndView.addObject("myPageInfo",myPageInfo);
		
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
