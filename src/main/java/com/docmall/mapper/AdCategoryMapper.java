package com.docmall.mapper;

import java.util.List;

import com.docmall.domain.CategoryVO;

//인터페이스가 bean으로 생성되는것이 아니라, 아래 Proxy Class가 객체 (bean)이 생성이되어
//AdcategoryServiceImpl 클래스 안에 주입
//매퍼클래스를 삭속받는(Proxy class)가 새성이되고 객체생성이 이루어진다
public interface AdCategoryMapper {

	
	List<CategoryVO> getFirstCategoryList();
	
	List<CategoryVO> getSecondCategoryList(Integer cg_parent_code);
	
	CategoryVO get(Integer cg_code);
}
