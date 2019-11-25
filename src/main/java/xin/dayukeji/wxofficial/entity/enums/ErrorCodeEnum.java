package xin.dayukeji.wxofficial.entity.enums;

import xin.dayukeji.common.entity.ErrorResult;

/**
 * @Author: yfy
 * @Date: 2019-09-25 20:35
 * @Version 1.0
 * @description 描述
 */
public enum ErrorCodeEnum implements ErrorResult {
    /**
     * Gl 99990101 error code enum.
     */
    GL9999001(1206, "暂不支持该服务");

    int code;

    String message;


    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String msg() {
        return message;
    }
}
