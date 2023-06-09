package com.hwang.enums;

/**
 * ClassName: AppHttpCodeEnum
 * Package: com.grow.Enum
 * Description:
 *
 * @Author 王辉
 * @Create 2023/3/22 10:59
 * @Version 1.0
 */
public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200, "操作成功"),
    SYSTEM_ERROR(500, "出现错误"),
    USERNAME_EXIST(501, "用户名已存在"),
    PHONE_NUMBER_EXIST(502, "手机号已存在"),
    REQUIRE_USERNAME(504, "必需填写用户名"),
    REQUIRE_PASSWORD(504, "必须填写密码"),
    NOT_LOGIN(401, "NOTLOGIN"),
    SEND_FAIL(500, "短信发送失败"),
    SEND_SUCCESS(500, "手机验证码短信发送成功"),

    NEED_LOGIN(401, "需要登录后操作"),
    NO_OPERATOR_AUTH(403, "无权限操作"),
    EMAIL_EXIST(503, "邮箱已存在"),
    FILE_TYPE_ERROR(506, "文件类型有误,请上传png或jpg文件!"),
    FILE_UPLOAD_FAILURE(507, "文件上传失败，请检查!"),
    USERNAME_NOT_NULL(508, "用户名不能为空"),
    NICKNAME_NOT_NULL(509, "昵称不能为空"),
    PASSWORD_NOT_NULL(510, "密码不能为空"),
    EMAIL_NOT_NULL(511, "邮箱不能为空"),
    NICKNAME_EXIST(512, "昵称已存在"),
    LOGIN_ERROR(505,"用户名或密码错误");

    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage) {
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
