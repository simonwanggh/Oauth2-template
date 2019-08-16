package com.zihong.auth.controller.to;

public class PHPTemp {
	

	public String getUid() {
		return "3";
	}
	public String getName() {
		return "老司机";
	}
	public int getRoleid() {
		return 0;
	}
	public String getUsername() {
		return "test";
	}
	public Object getLever() {
		return null;
	}
	public System getSystem() {
		return new System();
	}
	public String getRights() {
		return "{\"PU_QUERY\":true,\"PU_ADD\":true,\"PU_UPDATE\":true,\"PU_DELETE\":true,\"PU_EXPORT\":true,\"PU_PRINT\":true,\"PU_CHECK\":true,\"PU_UNCHECK\":true,\"PO_QUERY\":true,\"PO_ADD\":true,\"PO_UPDATE\":true,\"PO_DELETE\":true,\"PO_EXPORT\":true,\"PO_PRINT\":true,\"PO_CHECK\":true,\"PO_UNCHECK\":true}";
	}
	
	

}


class System{
	

	public String getCompanyName() {
		return "ERP";
	}
	public String getCompanyAddr() {
		return "ERP";
	}
	public String getStartDate() {
		return "";
	}
	public String getPhone() {
		return "17611112222";
	}
	
	
}
