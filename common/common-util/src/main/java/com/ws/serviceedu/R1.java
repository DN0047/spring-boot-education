package com.ws.serviceedu;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R1 {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    private R1(){}

    public static R1 ok(){
        R1 r = new R1();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    public static R1 error(){
        R1 r = new R1();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    public R1 success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R1 message(String message){
        this.setMessage(message);
        return this;
    }

    public R1 code(Integer code){
        this.setCode(code);
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public R1 setMessage(String message) {
        this.message = message;


        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public R1 data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R1 data(Map<String, Object> map){
        this.setData(map);
        return this;
    }


}
