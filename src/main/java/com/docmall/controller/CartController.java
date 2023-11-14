package com.docmall.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.docmall.domain.CartVO;
import com.docmall.domain.MemberVO;
import com.docmall.dto.CartDTOList;
import com.docmall.service.CartService;
import com.docmall.util.FileUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RequiredArgsConstructor
@RequestMapping("/user/Cart/*")
@Controller
public class CartController {

	private final CartService cartService;
	
	// 메인및썸네일 이미지 업로드 폴더경로 주입작업
	@Resource(name="uploadPath")	//servlet-context.xml의 bean이름 참조
	private String uploadPath;

	
	@ResponseBody
	@PostMapping("/cart_add")
	public ResponseEntity<String> cart_add(CartVO vo, HttpSession session) throws Exception{
		
		ResponseEntity<String> entity = null;
		
		// ajax방식에서 상품코드, 수량2개정보만 전송되어옴 (로그인한 사용자의 아이디 정보 추가작업)
		String mbsp_id = ((MemberVO) session.getAttribute("loginStatus")).getMbsp_id();
		vo.setMbsp_id(mbsp_id);
		
		cartService.cart_add(vo);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
	//장바구니 리스트
	@GetMapping("/cart_list")
	public void cart_list(HttpSession session, Model model) throws Exception{
		
		String mbsp_id = ((MemberVO) session.getAttribute("loginStatus")).getMbsp_id();
		
		List<CartDTOList> cart_list = cartService.cart_list(mbsp_id);
		

		
			double cart_total_price = 0;
		
	      for(int i = 0 ; i<cart_list.size(); i++) {
	          CartDTOList vo = cart_list.get(i);
	          vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
	          cart_total_price += ((double)vo.getPro_price() - (vo.getPro_price() * vo.getPro_discount() * 1/100 ))* vo.getCart_amount();
	       }
	
		
		
		model.addAttribute("cart_list", cart_list);
		model.addAttribute("cart_total_price", cart_total_price);
	}
	
	   @ResponseBody
	   @GetMapping("/cart_list/imageDisplay")	///user/Cart/cart_list/imageDisplay?dateFolderName=값1&fileName=값2
	   public ResponseEntity<byte[]> imageDisplay(String dateFolderName, String fileName)throws Exception {
		   
		   return FileUtils.getFile(uploadPath + dateFolderName, fileName);
		   
			   
		   }
	   //장바구니 수량변경
	   @PostMapping("/cart_list/cart_amount_change")
	   public ResponseEntity<String> cart_amount_change(Long cart_code, int cart_amount) throws Exception {
		   ResponseEntity<String> entity = null;
		   
		   entity = new ResponseEntity<String>("success", HttpStatus.OK);
		   return entity;
		   
	   }
}
