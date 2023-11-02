package com.docmall.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.docmall.domain.ProductVO;
import com.docmall.service.AdProductService;
import com.docmall.service.AdProductServiceImpl;
import com.docmall.util.FileUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RequiredArgsConstructor
@RequestMapping("/admin/product/*")
@Controller
public class AdProductController {

	private final AdProductService adProductService;
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	
	//상품등록 폼
	@GetMapping("/pro_insert")
	public void pro_insert() {
		
		log.info("상품등록 폼");
	}
	//상품등록 저장
	//파일 업로드기능 : 1)스프링에서 내장된 기본라이브러리 
	// 2)외부라이브러리(commons-fileupload)를이용(pom.xml) , servlet-context.xml 에서 multipartfile에 대한 bean 등록작업
	//MultipartFile uploadFile : <input type="file" class="form-control" name="uploadFile" id="uploadFile">
	@PostMapping("/pro_insert")
	public String pro_insert(ProductVO vo , MultipartFile uploadFile, RedirectAttributes rttr) {
		
		log.info("상품정보 " + vo);
		//1)파일업로드 작업. 미리선수작업 : FileUtils 클래스작업
		String dateFolder = FileUtils.getDateFolder();
		String savedFileName = FileUtils.uploadFile(uploadPath, dateFolder, uploadFile);
		
		log.info("상품정보 " + vo);
		
		vo.setPro_img(savedFileName);
		vo.setPro_up_folder(dateFolder);
		//2)상품정보 저장.
		//adProductService.pro_insert(vo);
		
		return "redirect:/리스트";
	}
	
}
