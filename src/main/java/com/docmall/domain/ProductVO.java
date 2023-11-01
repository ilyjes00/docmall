package com.docmall.domain;

import java.util.Date;

/*
 CREATE TABLE PRODUCT_TBL(
        PRO_NUM             NUMBER  CONSTRAINT  PK_PRO_NUM         PRIMARY KEY,
        CG_CODE            NUMBER            NULL,
        PRO_NAME            VARCHAR2(50)            NOT NULL,
        PRO_PRICE           NUMBER                  NOT NULL,
        PRO_DISCOUNT        NUMBER                  NOT NULL,
        PRO_PUBLISHER       VARCHAR2(50)            NOT NULL,
        PRO_CONTENT         VARCHAR2(4000)                    NOT NULL,       -- 내용이 4000BYTE 초과여부판단?
        PRO_UP_FOLDER       VARCHAR(50)             NOT NULL,
        PRO_IMG             VARCHAR(50)             NOT NULL,  -- 날짜폴더경로가 포함하여 파일이름저장
        PRO_AMOUNT          NUMBER                  NOT NULL,
        PRO_BUY             CHAR(1)                 NOT NULL,
        PRO_DATE            DATE DEFAULT SYSDATE    NOT NULL,
        PRO_UPDATEDATE      DATE DEFAULT SYSDATE    NOT NULL,
        FOREIGN KEY(CG_CODE) REFERENCES CATEGORY_TBL(CG_CODE)
);
 */
//pro_img, cg_code, pro_publisher, pro_price, pro_name, pro_num, pro_up_folder, pro_discount, pro_content, pro_buy, pro_amount, pro_date, pro_updatedate


public class ProductVO {
	private Integer pro_num;
	private Integer cg_code; //2차카테고리 코드
	private String 	pro_name;
	private int 	pro_price;
	private int 	pro_discount;
	private String 	pro_publisher;
	private String 	pro_content;
	private String	pro_up_folder;
	private String 	pro_img;
	private int 	pro_amount;
	private String 	pro_buy;
	private Date 	pro_date;
	private Date 	pro_updatedate;
	
	
}
