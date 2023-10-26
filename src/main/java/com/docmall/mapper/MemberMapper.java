package com.docmall.mapper;

import com.docmall.domain.MemberVO;

public interface MemberMapper {
	//jsp에서도 idcheck부분 소문자로 처리해서 이쪽도 소문자처리함
	String idcheck(String mbsp_id);
	
	void join(MemberVO vo);
	
	MemberVO login(String mbsp_id);
}
