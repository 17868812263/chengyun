package com.chengyun.chengyun.vo;

public class ResultVo<T> {

    private Integer code;
 
    private String msg;
 
    private T data;

    public ResultVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
 
    public ResultVo(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
 
    /**
     * 请求成功  状态码 200
     *
     * @param msg 返回信息
     * @param <T> 类型
     * @return ResultVO
     */
    public static <T> ResultVo getSuccess(String msg) {
        return new ResultVo(200, msg);
    }
 
    /**
     * 请求成功  状态码 200
     *
     * @param msg  返回信息
     * @param data 返回对象
     * @param <T>  类型
     * @return ResultVO
     */
    public static <T> ResultVo getSuccess(String msg, T data) {
        return new ResultVo(200, msg, data);
    }
 
    /**
     * 请求失败   状态码 500
     *
     * @param msg 返回信息
     * @param <T> 类型
     * @return ResultVO
     */
    public static <T> ResultVo getFailed(String msg) {
        return new ResultVo(500, msg);
    }
 
    /**
     * 请求失败  状态 500
     *
     * @param msg  返回信息
     * @param data 返回数据
     * @param <T>  类型
     * @return ResultVO
     */
    public static <T> ResultVo getFailed(String msg, T data) {
        return new ResultVo(500, msg, data);
    }
 
 
    /**
     * 用户名不存在
     *
     * @param <T> 类型
     * @return ResultVO
     */
    public static <T> ResultVo getNoExisted() {
        return new ResultVo(0, "用户名不存在，请重试");
    }
 
 
    /**
     * 密码错误
     *
     * @param <T> 类型
     * @return ResultVO
     */
    public static <T> ResultVo getPasswordError() {
        return new ResultVo(1, "密码错误，请重试");
    }
 
 
    public Integer getCode() {
        return code;
    }
 
    public void setCode(Integer code) {
        this.code = code;
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
