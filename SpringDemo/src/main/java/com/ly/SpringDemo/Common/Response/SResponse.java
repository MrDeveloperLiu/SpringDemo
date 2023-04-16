package com.ly.SpringDemo.Common.Response;


import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.HashMap;
import java.util.Map;


@Data
@AllArgsConstructor
public class SResponse {
    private Integer code;
    private String msg;
    private Object data;

    public Map<String, Object> toMap() {
        HashMap retVal = new HashMap<>();
        retVal.put("data", data);
        retVal.put("code", code);
        retVal.put("msg", msg);
        return retVal;
    }
}
