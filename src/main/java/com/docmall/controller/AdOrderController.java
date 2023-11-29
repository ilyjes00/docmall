package com.docmall.controller;

import java.io.Console;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.docmall.domain.OrderDetailInfoVO;
import com.docmall.domain.OrderDetailProductVO;
import com.docmall.domain.OrderDetailVO;
import com.docmall.domain.OrderVO;
import com.docmall.dto.Criteria;
import com.docmall.dto.PageDTO;
import com.docmall.service.AdOrderService;
import com.docmall.util.FileUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor
@Log4j
@RequestMapping("/admin/order/*")
@Controller
public class AdOrderController {
	public final AdOrderService adOrderService;
	
	   // 메인 및 썸네일 이미지 업로드 폴더경로 주입작업
	   @Resource(name="uploadPath")	//servlet-context.xml의 bean이름 참조
	   private String uploadPath;

	   //상품리스트	(목록과페이징)
	   @GetMapping("/order_list")
	   public void order_list(Criteria cri, Model model) throws Exception {
		   
		   
		   //10 -> 2
		    cri.setAmount(2);
		   
			List<OrderVO> order_list = adOrderService.order_list(cri);
			
			
			

			model.addAttribute("order_list", order_list);
			
			int totalcount = adOrderService.getTotalCount(cri);
			model.addAttribute("pageMaker", new PageDTO(cri, totalcount));
			
			
	   }
	   
	   @ResponseBody
	   @GetMapping("/imageDisplay")	///user/product/imageDisplay?dateFolderName=값1&fileName=값2
	   public ResponseEntity<byte[]> imageDisplay(String dateFolderName, String fileName)throws Exception {
		   
		   return FileUtils.getFile(uploadPath + dateFolderName, fileName);
	   }
	   
	   
	   
		// 주문상세방법1. 주문상세보기 클라이언트 JSON형태로 변환해서 보내진다. (pom.xml에 jackson-databind 라이브러리 백그라운드로 작동)
		@GetMapping("/order_detail_info1/{ord_code}")
		public ResponseEntity<List<OrderDetailInfoVO>> order_detail_list1(@PathVariable("ord_code") Long ord_code) throws Exception{

			
			
			ResponseEntity<List<OrderDetailInfoVO>> entity = null;
			//클래스명 = 주문상세 + 상품테이블 조인한 결과를 담는 클래스
/*		ResponseEntity<List<String, Object>> entity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		*/
			List<OrderDetailInfoVO> OrderDetailList = adOrderService.orderDetailInfo1(ord_code);
			
			//날짜폴더의 역슬래시를 슬래시로 바꾸는 작업, 역슬래시로 되어있는 정보가 스프링으로 보내는 요청데이터에 사용되면 에러발생.
			OrderDetailList.forEach(vo -> {
				vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
			});
			
			
			entity = new ResponseEntity<List<OrderDetailInfoVO>> (OrderDetailList, HttpStatus.OK);
		
		return entity;
		
		}
		//주문상세내역 개별상품삭제.
			@GetMapping("/order_product_delete")
		   public String order_product_delete(Criteria cri, Long ord_code, Integer pro_num)throws Exception  {
				
				//주문상세 개별삭제
				adOrderService.order_product_delete(ord_code, pro_num);
				
				return "redirect:/admin/order/order_list" + cri.getListLink();
			   
		    }
			//주문상세작업2
			// 주문상세방법1. 주문상세보기 클라이언트 JSON형태로 변환해서 보내진다. (pom.xml에 jackson-databind 라이브러리 백그라운드로 작동)
			@GetMapping("/order_detail_info2/{ord_code}")
			public String order_detail_list2(@PathVariable("ord_code") Long ord_code, Model model) throws Exception{

				List<OrderDetailProductVO> orderProductList = adOrderService.orderDetailInfo2(ord_code);
				
/*				orderProductList.forEach(vo -> {
					vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
				});
				*/
				orderProductList.forEach(vo -> {
					vo.getProductVO().setPro_up_folder(vo.getProductVO().getPro_up_folder().replace("\\", "/"));
				});
				
				model.addAttribute("orderProductList", orderProductList);
				
				return "/admin/order/order_detail_pro";
				
				
			}
		

}
