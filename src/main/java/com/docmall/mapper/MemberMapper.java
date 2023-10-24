package com.docmall.mapper;

public interface MemberMapper {
	//jsp에서도 idcheck부분 소문자로 처리해서 이쪽도 소문자처리함
	String idcheck(String mbsp_id);
}
