package com.docmall.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.docmall.domain.ProductVO;
import com.docmall.dto.Criteria;
import com.docmall.dto.PageDTO;
import com.docmall.service.UserProductService;
import com.docmall.util.FileUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RequiredArgsConstructor
@RequestMapping("/user/product/*")
@Controller
public class UserProductController {
	
	private final UserProductService userProductService;
	
	// 메인및썸네일 이미지 업로드 폴더경로 주입작업
	@Resource(name="uploadPath")	//servlet-context.xml의 bean이름 참조
	private String uploadPath;

	
	
	//매핑주소 1: /user/product/pro_list?cg_code=2차카테고리코드
	//매핑주소 2: /user/product/pro_list/2차카테고리 코드 (REST API 개발형태)
	/*
	@GetMapping("/pro_list")
	public void pro_list(Integer cg_code) throws Exception{
		
	}*/
	
	@GetMapping("/pro_list")
	public String pro_list(Criteria cri,@ModelAttribute("cg_code")Integer cg_code, @ModelAttribute("cg_name") String cg_name, Model model) throws Exception{
		
		   
		   //10 -> 8
		    cri.setAmount(8);
		   
			List<ProductVO> pro_list = userProductService.pro_list(cg_code, cri);
			
			//날짜폴더의 역슬래시를 슬래시로 바꾸는 작업, 역슬래시로 되어있는 정보가 스프링으로 보내는 요청데이터에 사용되면 에러발생.
			pro_list.forEach(vo -> {
				vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
			});
			
			model.addAttribute("pro_list", pro_list);
			
			int totalcount = userProductService.getTotalCount(cg_code);
			model.addAttribute("pageMaker", new PageDTO(cri, totalcount));
			
			return "/user/product/pro_list";
	   }
	   //상품리스트에서 보여줄이미지, <img src="매핑주소">
	   @ResponseBody
	   @GetMapping("/imageDisplay")	///user/product/imageDisplay?dateFolderName=값1&fileName=값2
	   public ResponseEntity<byte[]> imageDisplay(String dateFolderName, String fileName)throws Exception {
		   
		   return FileUtils.getFile(uploadPath + dateFolderName, fileName);
	   }
	}

