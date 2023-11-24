package com.docmall.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.docmall.domain.ReviewVO;
import com.docmall.dto.Criteria;

public interface ReviewMapper {
	
	void review_insert(ReviewVO vo);
	
	List<ReviewVO> list(@Param("pro_num")Integer pro_num, @Param("cri") Criteria cri); 	//criteria 에서 검색기능 사용안함. mapper에서만 param하고 서비스에서는 제거

	int listCount(Integer pro_num);
	
	//insert , update ,delete 작업은 void로 처리
	void delete(Long rew_num);
}
