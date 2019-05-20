package com.halo.admin.constant;

/**
 * @author Hailuo
 * @Date: 2019/4/3 15:22
 * @Description：
 */
public enum ReturnCode {
	SUCCESS(0, "成功"),
	FAIL(-1,"失败"),
	USER_NOT_EXIST(10000, "用户名不存在！"),
	PASSWORD_INVALID(10001,"密码错误"),
	USER_DISABLED(10002,"用户已被禁用");


	;
	private int code;
	private String desc;

	ReturnCode(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
