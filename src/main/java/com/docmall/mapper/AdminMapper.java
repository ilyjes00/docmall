package com.docmall.mapper;

import com.docmall.domain.AdminVO;

public interface AdminMapper {

	AdminVO admin_ok(String admin_id);
	
	void login_Date(String admin_id);
}
