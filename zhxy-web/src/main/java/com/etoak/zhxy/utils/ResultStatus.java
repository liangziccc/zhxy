package com.etoak.zhxy.utils;

public enum ResultStatus {
    SUCCESS(200,"success"),
    ERROR(2000,"error"), INVALID_REQUEST(300,"invalid"),CHECKCODE_ERROR(203,"checkerror");

    private int code;
    private  String msg;

    private ResultStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static JsonResponse INVALID_REQUEST(String str) {
        return new JsonResponse(INVALID_REQUEST.code, INVALID_REQUEST.msg,str);
    }

    public static JsonResponse checkCodeError(String s) {
        return new JsonResponse(CHECKCODE_ERROR.code, CHECKCODE_ERROR.msg,s ) ;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
    public static JsonResponse suc(Object str){
        return  new  JsonResponse(SUCCESS.code,SUCCESS.msg,str);
    }
    public static JsonResponse errer(Object str){
        return  new  JsonResponse(ERROR.code,ERROR.msg,str);
    }



}
