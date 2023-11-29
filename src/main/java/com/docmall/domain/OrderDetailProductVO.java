package com.docmall.domain;

import lombok.Data;

@Data
public class OrderDetailProductVO {

	//기존클래스사용
	private OrderDetailVO orderDetailVO; //collection으로 표현
	private ProductVO productVO;		//collection으로 표현
}
