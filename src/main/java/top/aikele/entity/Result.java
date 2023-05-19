package top.aikele.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * @projectName: KeleBlog
 * @package: top.aikele.entity
 * @className: Result
 * @author: Kele
 * @description: TODO
 * @data: 2023/4/15 0:45
 * @version: 1.0
 */
public class Result<T> {
    @ApiModelProperty("数据")
    private T data;
    @ApiModelProperty("状态")
    private Integer code;
    @ApiModelProperty("信息")
    private String Massage;

    public Result(Integer code, String massage) {
        this.code = code;
        Massage = massage;
    }

    public Result(T data, Integer code, String massage) {
        this.data = data;
        this.code = code;
        Massage = massage;
    }

    public static Result fail(){
        return new Result(ResultCodeEnum.fail.getCode(),ResultCodeEnum.fail.getMassage());
    }
    public static Result fail(String massage){
        return new Result(ResultCodeEnum.fail.getCode(),massage);
    }
    public static <T> Result fail(String massage,T t){
        return new Result(t,ResultCodeEnum.fail.getCode(),massage);
    }
    public static Result success(){
        return new Result(ResultCodeEnum.success.getCode(),ResultCodeEnum.success.getMassage());
    }
    public static Result success(String massage){
        return new Result(ResultCodeEnum.success.getCode(),massage);
    }
    public static <T> Result success(T t){
        return new Result(t,ResultCodeEnum.success.getCode(),ResultCodeEnum.success.getMassage());
    }
    public static <T> Result success(String massage,T t){
        return new Result(t,ResultCodeEnum.success.getCode(),massage);
    }
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMassage() {
        return Massage;
    }

    public void setMassage(String massage) {
        Massage = massage;
    }
}
