package com.halo.admin.vo;

import com.halo.admin.constant.ReturnCode;
import lombok.Data;

/**
 * @author Hailuo
 * @Date: 2019/4/3 15:20
 * @Description：
 */
@Data
public class Response {
	private int code;
	private String desc;
	private Object data;

	private Response() {
	}

	private Response(int code, String desc, Object data) {
		this.code = code;
		this.desc = desc;
		this.data = data;
	}

	public static Response buildResponse(ReturnCode returnCode, Object data) {
		return new Response(returnCode.getCode(), returnCode.getDesc(), data);
	}

	public static Response buildResponse(ReturnCode returnCode) {
		return buildResponse(returnCode, null);
	}

	public static Response buildResponse(Object data) {
		return buildResponse(ReturnCode.SUCCESS, data);
	}

	public static Response buildResponse() {
		return buildResponse(ReturnCode.SUCCESS, null);
	}

    public static Response buildFailResponse(String msg) {
        return new Response(ReturnCode.FAIL.getCode(), msg, null);
    }






}
