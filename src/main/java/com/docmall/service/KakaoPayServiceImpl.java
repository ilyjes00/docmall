package com.docmall.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.docmall.kakaopay.ApproveResponse;
import com.docmall.kakaopay.ReadyResponse;

/*
  	카카오페이 Api에 요청하기위한 작업
  	HttpURLConnection을 이용한 HTTP통신방법
  	Rest Template을 이용한 통신 (스프링에서 권장) : https://minkwon4.tistory.com/178
  	설명 : RestTemplate은 Spring 3.0 부터 지원하는 템플릿으로 Spring에서 HTTP 통신을 RESTful 형식에 맞게 손쉬운 사용을 제공해주는 템플릿이다
 */
/*
 	결제준비하기 - 첫번쨰 요청
 	요청주소  https://kapi.kakao.com/v1/payment/ready
 	Post: /v1/payment/ready
 	Host: kapi.kakao.com
 	Authorization: KakaoAK ${SERVICE_APP_ADMIN_KEY}
 	Content-type: application/x-www-form-urlencoded;charset=utf-8
 
 
 */

@Service
public class KakaoPayServiceImpl {
	
	
	//httpHeader 클래스 : 헤더정보를 구성할때 사용
	//	https://jung-story.tistory.com/132
	/*
		Authorization: KakaoAK ${SERVICE_APP_ADMIN_KEY}
 		Content-type: application/x-www-form-urlencoded;charset=utf-8
	 */
	//요청 파라미터의 헤더정보
	private HttpHeaders getHeaderInfo() {
		HttpHeaders headers = new HttpHeaders();
		//Admin키
		headers.set("Authorization", "KakaoAK ${53118e479f6d3ac99035e20073dd88ee}"); //카카오톡 개발자 https://developers.kakao.com/console/app 홈페이지에서 자기개인작업으로 변경할것
		headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		return headers;
	}
	

	public ReadyResponse payReady(Long odr_code,String itemName, int quantity, String mbsp_id, int totalAmount) {
			
		//카카오페이 요청할 request 정보 구성작업
		//컬렉션 클래스를 이용하여, 구성한다
		//map : 하나의 키에 값만 저장하는 특징
		//	https://jung-story.tistory.com/132
		
		
		//hashMap기능은 중복이안되므로 Spring 에서 지원하는 MultiValueMap을 이용하여 값이 중복되게끔한다
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		
		parameters.add("cid", "TC0ONETIME");					//	가맹점 코드, 10자
		parameters.add("partner_order_id", String.valueOf(odr_code));		// 	가맹점 주문번호, 최대 100자
		parameters.add("partner_user_id", mbsp_id);		//	가맹점 회원 id, 최대 100자
		parameters.add("item_name", itemName);			// 	상품명, 최대 100자
		parameters.add("quantity", String.valueOf(quantity));				//	상품 수량
		parameters.add("total_amount", String.valueOf(totalAmount));			//	상품 총액
		parameters.add("tax_free_amount", "0");		// 	상품 비과세 금액
		parameters.add("approval_url", "http://localhost:9090/user/order/orderApproval");			//	결제 성공 시 redirect url, 최대 255자
		parameters.add("cancel_url", "http://localhost:9090/user/order/orderCancle");			//	결제 취소 시 redirect url, 최대 255자
		parameters.add("fail_url", "http://localhost:9090/user/order/orderFail");				//	결제 실패 시 redirect url, 최대 255자
		
		//헤더 파라미터를 구성하는작업
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String,String>>(parameters, this.getHeaderInfo());
		
		//KakaoApi 서버와 통신
		RestTemplate template = new RestTemplate();
		//1차 요청주소
		String kakaoReadUrl = "https://kapi.kakao.com/v1/payment/ready";
		
		ReadyResponse readyResponse = template.postForObject(kakaoReadUrl, requestEntity, ReadyResponse.class);
		
		return readyResponse;
	}
	
	/*
 	결제준비하기 - 두번쨰 요청
 	요청주소  https://kapi.kakao.com/v1/payment/approve
 	Post: /v1/payment/approve
 	Host: kapi.kakao.com
 	Authorization: KakaoAK ${SERVICE_APP_ADMIN_KEY}
 	Content-type: application/x-www-form-urlencoded;charset=utf-8
 
 
 */
	public ApproveResponse payApprove(Long odr_code, String tid ,String pgToken, String mbsp_id) {
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
			
		parameters.add("cid", "TC0ONETIME");	//가맹점 코드 10글자
		parameters.add("tid", tid);				//결제 고유번호, 결제준비 API응답에 포함
		parameters.add("partner_order_id", String.valueOf(odr_code));		// 	가맹점 주문번호, 최대 100자
		parameters.add("partner_user_id", mbsp_id);		//	가맹점 회원 id, 최대 100자
		parameters.add("pgToken", pgToken);			
		
		//헤더 파라미터를 구성하는작업
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String,String>>(parameters, this.getHeaderInfo());
		
		//KakaoApi 서버와 통신
		RestTemplate template = new RestTemplate();
		//1차 요청주소
		String kakaoApproveUrl = "https://kapi.kakao.com/v1/payment/approve";
		
		ApproveResponse approveResponse = template.postForObject(kakaoApproveUrl, requestEntity,  ApproveResponse.class);
		

		
		return approveResponse;
	}
	
	
}
