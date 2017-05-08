package com.tanzl.paycenter.common;

public class BaseResponse<T> {
    
    public static final Integer RC_SUCCESS = 1;
    public static final Integer RC_FAIL = -1;
    public static final Integer RC_RECORD_NOT_FOUND = 404;
    
	private Integer rc = -1;
	private String msg;
	private T data;

	public Integer getRc() {
		return rc;
	}

	public void setRc(Integer rc) {
		this.rc = rc;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
