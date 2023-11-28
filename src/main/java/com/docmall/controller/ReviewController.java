package com.docmall.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.docmall.domain.MemberVO;
import com.docmall.domain.ReviewVO;
import com.docmall.dto.Criteria;
import com.docmall.dto.PageDTO;
import com.docmall.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor
@Log4j
@RequestMapping("/user/review/*") 
@RestController //ajax만 사용하므로 @controller 가아닌 restcontroller 이용  jsp 파일 사용안함.
public class ReviewController {

	private final ReviewService reviewService;
	
	@PostMapping(value = "/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> review_insert(@RequestBody ReviewVO vo, HttpSession session) throws Exception{
		
		log.info("리뷰: " + vo);
		
	String mbsp_id = ((MemberVO) session.getAttribute("loginStatus")).getMbsp_id();
	vo.setMbsp_id(mbsp_id);
	
	ResponseEntity<String> entity = null;
	
	//db저장
	reviewService.review_insert(vo);
	
	entity = new ResponseEntity<String>("success", HttpStatus.OK);
	
	return entity;
	}
	
	// list?pro_num=10$page=1	-> Rest API 개발형태 주소/list/10/1
	@GetMapping("/list/{pro_num}/{page}")
	public ResponseEntity<Map<String, Object>> list(@PathVariable("pro_num") Integer pro_num, @PathVariable("page") int page) throws Exception{

	ResponseEntity<Map<String, Object>> entity = null;
	Map<String, Object> map = new HashMap<String, Object>();
	
	//1)상품후기목록 페이지
	Criteria cri = new Criteria();
	cri.setAmount(2); //페이지수
	cri.setPageNum(page);
	
	
	
	//2)db연동작업
	List<ReviewVO> list = reviewService.list(pro_num, cri);
	
	//3)페이징페이지
	int listCount = reviewService.listCount(pro_num);
	PageDTO pageMaker = new PageDTO(cri, listCount);
	
	
	map.put("list", list); // 상품후기목록데이터 - List<ReviewVO>
	map.put("pageMaker", pageMaker); //페이징데이터 - PageDTO
	
	//리턴타입에 따른 구문
	// -select문
	//ResponseEntity<Map<String, Object>> : 1)상품후기목록데이터 - List<ReviewVO>  2)페이징 데이터	-PageDTO 둘다써야하므로 이문법을사용
	//ResponseEntity<List<ReviewVO>> : 1)상품후기목록데이터 - List<ReviewVO>
	//ResponseEntity<PageDTO> : 페이징데이터
	
	// jackson-databind 라이브러리에 의하여 map -> json 으로 변환되어 ajax 호출한 쪽으로 리턴값이 보내진다.
	entity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	
	
	return entity;
	
	}
	
	//상품후기 삭제
	@DeleteMapping("/delete/{rew_num}") //user/review/delete/1
	public ResponseEntity<String> delete(@PathVariable("rew_num") Long rew_num)throws Exception {
		ResponseEntity<String> entity = null;
		
		//db연동작업.
		reviewService.delete(rew_num);
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		
		return entity;
	}
	
	@PutMapping(value = "/modify", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> review_modify(@RequestBody ReviewVO vo, HttpSession session) throws Exception{
		
		log.info("리뷰: " + vo);
		
	String mbsp_id = ((MemberVO) session.getAttribute("loginStatus")).getMbsp_id();
	vo.setMbsp_id(mbsp_id);
	
	ResponseEntity<String> entity = null;
	
	//db저장
	reviewService.review_modify(vo);
	
	entity = new ResponseEntity<String>("success", HttpStatus.OK);
	
	return entity;
	}
}
