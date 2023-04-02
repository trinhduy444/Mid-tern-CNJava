package com.example.SpringCommerceShop.Controller;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.SpringCommerceShop.Mapper.ProductMapper;
import com.example.SpringCommerceShop.Modal.Product;
import com.example.SpringCommerceShop.Modal.ProductExample;
import com.fasterxml.jackson.annotation.JacksonInject.Value;


@Controller
public class HomePageController {
	
	@Autowired
	ProductMapper productMapper;
	
	
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		//Danh sach san pham
		ProductExample productExample = new ProductExample();
//		productExample.createCriteria().andIdEqualTo("P01");
		
		List<Product> listProduct = productMapper.selectByExample(productExample);
		
		
//		for(Product product : listProduct) {
//			System.out.println("Du lieu:" + product.getName() );
//		}
		
		


		List<Map<String, Object>> listMapProduct = productMapper.getAllProduct();
		
		
//		for (Map<String, Object> value : listMapProduct) {
//			System.out.println(value.get("username"));
//		}
//		

		
		return modelAndView;
	}
}
