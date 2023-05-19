package top.aikele.entity;

public enum ResultCodeEnum {
     success(200,"成功"),
     fail(201,"失败");
     private int code;
    private String massage;

    ResultCodeEnum(int code, String massage) {
        this.code = code;
        this.massage = massage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
