package com.docmall.controller;


import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.docmall.domain.MemberVO;
import com.docmall.dto.LoginDTO;
import com.docmall.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

//fianl 필드만 매개변수가 있는 생성자를 만들어주고 스프링에 의하여 생성자 주입을 받게된다.
@RequiredArgsConstructor
@Log4j
@RequestMapping("/member/*")
@Controller
public class MemberController {
	
	// https: //dev-coco.tistory.com/70
	
	private final MemberService memberService;
	
	private final PasswordEncoder passwordEncoder;
	
	@GetMapping("/join")
	public void join() {
		
		log.info("called...join");
	}
	
	// 비동기방식. ajax문법으로 호출
	// 아이디 중복체크
	// ResponseEntity클래스?httpentity를 상속받는, 결과 데이터와 HTTP상태 코드를 직접 제어할수 있는
	// 3가지 구성요소 - HttpStatus, HttpHeaders, HttpBody
	// ajax기능과 함께 사용
	
	@GetMapping("/idcheck")
	public ResponseEntity<String> idcheck(String mbsp_id) {
		
		log.info("아이디: " + mbsp_id);
		
		ResponseEntity<String> entity = null;
		
		
		//서비스 메서드 호출구문작업.
		String idUse = "";
		if(memberService.idcheck(mbsp_id) != null) {
			idUse = "no"; //아이디가 존재하여, 사용불가능
		}else {
			idUse = "yes"; //아이디가 존재하지않아, 사용가능
		}
		
		entity = new ResponseEntity<String>(idUse, HttpStatus.OK);
		
		return entity;
	}

	//회원정보저장 > 다른주소 이동(redirect)
	@PostMapping("/join")
	public String join(MemberVO vo, RedirectAttributes rttr) { //RedirectAttributes rttr은 선택사항이다.
		
		
		log.info("회원정보: " + vo);
		//60byte 암호화작업
		vo.setMbsp_password(passwordEncoder.encode(vo.getMbsp_password()));
		
		log.info("암호화 비밀번호: " + vo.getMbsp_password());
		
		//db저장
		memberService.join(vo);
		return "redirect:/member/login";
	}
	
	//로그인 폼페이지
	@GetMapping("/login")
	public void login() {
		
	}
	//1) 로그인 인증성공 -> 메인페이지(/) 주소이동  2)로그인 인증실패 -> 로그인 폼주소로 이동
	//public String login(String mbsp_id, String mbsp_password)
	//import LoginDTO -> public String login(LoginDTO dto) {
	//1,2번 둘다사용가능
	
	@PostMapping("/login")
	public String login(LoginDTO dto, HttpSession session, RedirectAttributes rttr) {
		
		log.info("로그인: " + dto);
		
		MemberVO db_vo = memberService.login(dto.getMbsp_id());
		
		String url = "";
		String msg = "";
		
		//아이디가 존재하면, true 존재하지않으면 false
		if(db_vo != null) {
			// 사용자가 입력한 비밀번호(평문 텍스트)와 db에서 가져온 암호화된 비밀번호 일치여부 검사
			if(passwordEncoder.matches(dto.getMbsp_password(), db_vo.getMbsp_password())) {
				//로그인 성공결과로 서버측의 메모리를 사용하는 세션형태작업
				session.setAttribute("loginStatus", db_vo);
				url = "/"; //메인페이지 주소
			}else {
				url = "/member/login"; //로그인 폼주소
				msg = "비밀번호가 일치하지 않습니다.";
				rttr.addFlashAttribute("msg", msg); //로그인 폼 jsp파일에서 사용목적
			}
		}else {
			//아이디가 일치하지않으면
			url = "/member/login"; //로그인 폼주소
			msg = "아이디가 일치하지 않습니다.";
			rttr.addFlashAttribute("msg", msg); //로그인 폼 jsp파일에서 사용
		}
		
		return "redirect:" + url;
	}
	//로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
}
