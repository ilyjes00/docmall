package com.docmall.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.docmall.domain.OrderVO;
import com.docmall.domain.PaymentVO;
import com.docmall.mapper.OrderMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
	
	private final OrderMapper orderMapper;

	@Override
	public int getOrderSeq() {
		// TODO Auto-generated method stub
		return orderMapper.getOrderSeq();
	}

	@Transactional //트랜젝션 적용 (중요*) 하나라도 작동안되면 전부 취소되게된다 rollback
	@Override
	public void order_insert(OrderVO o_vo, PaymentVO p_vo) {
		
		orderMapper.order_insert(o_vo);
		orderMapper.order_detail_insert(o_vo.getOrd_code(), o_vo.getMbsp_id());
		orderMapper.cart_del(o_vo.getMbsp_id());
		orderMapper.payment_insert(p_vo);
		
		
	}

}
