package com.docmall.service;

import org.springframework.stereotype.Service;

import com.docmall.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

	//자동주입
	//@RequiredArgsConstructor : memberMapper 필드를 매개변수로 설정
	private final MemberMapper memberMapper;

	@Override
	public String idcheck(String mbsp_id) {
		// TODO Auto-generated method stub
		return memberMapper.idcheck(mbsp_id);
	}
}
