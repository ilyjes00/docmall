package com.docmall.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class OrderVO {

	
	//ord_code, mbsp_id, ord_name, ord_zipcode, ord_addr_basic, ord_addr_detail, ord_tel, ord_price, ord_regdate ,ORD_STATUS, PAYMENT_STATUS
	
	private long ord_code;
	private String mbsp_id;
	private String ord_name;
	private String ord_zipcode;
	private String ord_addr_basic;
	private String ord_addr_detail;
	private String ord_tel;
	private int ord_price;
	private Date ord_regdate;
	
	private String ord_status;
	private String payment_status;
}
