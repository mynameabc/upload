package com.project;

/**
 * @Author: 林少君
 * @Date: 2020/5/28 12:22
 */
public enum ResponseCode {

    /**
     * ----------------------------系统异常----------------------------------
     */

    EXECUTE_SUCCESS                         (       "0000", "执行成功!"),
    EXECUTE_FAIL                            (       "0001", "执行失败!"),
    QUERY_SUCCESS                           (       "0002", "查询成功!"),
    QUERY_FAIL                              (       "0003", "查询失败!"),
    UnknownException                        (       "0004", "未知异常!"),
    RuntimeException                        (       "0005", "运行时异常!"),
    NullPointerException                    (       "0006", "空指针异常!"),
    ClassCastException                      (       "0007", "类型转换异常!"),
    IOException                             (       "0008", "IO异常!"),
    IndexOutOfBoundsException               (       "0009", "数组越界异常!"),
    MethodArgumentTypeMismatchException     (       "0010", "参数类型不匹配异常!"),
    HttpMessageNotReadableException         (       "0011", "控制器方法中@RequestBody类型参数数据类型转换异常!"),
    MethodArgumentNotValidException         (       "0012", "控制器方法参数异常!"),
    TokenIsNullException                    (       "0013", "Token为空!"),
    TokenExpireException                    (       "0014", "Token过期!"),
    TokenInvalidException                   (       "0015", "Token无效!"),

    CopySuccess                             (       "0016", "复制成功!"),
    CopyFail                                (       "0017", "复制失败!"),
    srcFileException                        (       "0018", "srcFile不是一个有效的文件!"),
    destinationException                    (       "0019", "destination不是一个有效目录"),

    DBException(        "0099", "数据库异常!");

    /**
     * ----------------------------业务异常----------------------------------
     */

    private String code;
    private String msg;

    ResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static ResponseCode getByCode(String code) {
        for (ResponseCode ec : ResponseCode.values()) {
            if (ec.getCode().equals(code)) { return ec;}
        }
        return null;
    }
}
