package com.wzh.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Auther: wzh
 * @Date: 公共的响应类
 * @Description: com.wzh.vo
 * @version: 1.0
 */
//@JsonInclude(JsonInclude.Include.NON_NULL):
// 序列化 Java 对象为 JSON 字符串时，忽略值为 null 的字段，即不会将这些字段包含在最终的 JSON 输出中。
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseVO<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    public ResponseVO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功响应，包含数据
     *
     * @param data 响应数据
     * @param <T>  数据类型
     * @return ResponseVO
     */
    /*
     * 如果不加 <T>，编译器会把 ResponseVO<T> 中的 T 当成一个具体的类名（比如 String、Integer），
     * 但你代码中并没有一个叫 T 的类，因此会直接编译报错
     * 方法名前的 <T> 会绑定方法入参 T data 和返回值 ResponseVO<T> 的类型
     * */
    public static <T> ResponseVO<T> success(T data) {
        return new ResponseVO<>(200, "Success", data);
    }

    /**
     * 成功响应，无数据
     *
     * @param <T> 数据类型
     * @return ResponseVO
     */
    public static <T> ResponseVO<T> success() {
        return new ResponseVO<>(200, "Success", null);
    }

    /**
     * 错误响应，指定状态码和消息
     *
     * @param code    状态码
     * @param message 消息
     * @param <T>     数据类型
     * @return ResponseVO
     */
    public static <T> ResponseVO<T> error(Integer code, String message) {
        return new ResponseVO<>(code, message, null);
    }

    /**
     * 错误响应，默认状态码500
     *
     * @param message 错误消息
     * @param <T>     数据类型
     * @return ResponseVO
     */
    public static <T> ResponseVO<T> error(String message) {
        return new ResponseVO<>(500, message, null);
    }

    /**
     * 设置响应码并返回自身，支持链式调用
     *
     * @param code 响应码
     * @return ResponseVO
     */
    public ResponseVO<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    /**
     * 设置响应消息并返回自身，支持链式调用
     *
     * @param message 响应消息
     * @return ResponseVO
     */
    public ResponseVO<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 设置响应数据并返回自身，支持链式调用
     *
     * @param data 响应数据
     * @return ResponseVO
     */
    public ResponseVO<T> setData(T data) {
        this.data = data;
        return this;
    }
}
