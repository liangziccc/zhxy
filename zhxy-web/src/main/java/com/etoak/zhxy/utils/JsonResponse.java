package com.etoak.zhxy.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor@AllArgsConstructor
public class JsonResponse {
    private int code;
    private String msg;
    private Object data;
}
