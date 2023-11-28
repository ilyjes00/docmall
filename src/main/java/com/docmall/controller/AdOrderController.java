package com.docmall.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	   
	   //상품리스트에서 보여줄이미지, <img src="매핑주소">
	   @ResponseBody
	   @GetMapping("/imageDisplay")
	   public ResponseEntity<byte[]> imageDisplay(String dateFolderName, String fileName)throws Exception {
		   
		   return FileUtils.getFile(uploadPath + dateFolderName, fileName);
	   }
	   
	   
	   
		// list?pro_num=10$page=1	-> Rest API 개발형태 주소/list/10/1
		@GetMapping("/order_detail_info/{ord_code}")
		public ResponseEntity<List<OrderDetailVO>> list(@PathVariable("ord_code") Integer pro_num) throws Exception{

			
			
			ResponseEntity<List<OrderDetailVO>> entity = null;
			//클래스명 = 주문상세 + 상품테이블 조인한 결과를 담는 클래스
/*		ResponseEntity<List<String, Object>> entity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		

		//2)db연동작업
		List<ReviewVO> list = reviewService.list(pro_num, cri);*/
		
		return entity;
		
		}

}
