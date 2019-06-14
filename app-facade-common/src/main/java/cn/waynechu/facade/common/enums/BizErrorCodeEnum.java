package cn.waynechu.facade.common.enums;

/**
 * 响应枚举
 *
 * @author zhuwei
 * @date 2018/11/6 18:49
 */
public enum BizErrorCodeEnum implements BizEnum {
    /**
     * 预定义枚举: 10000~19999  自定义业务异常: 20000+
     */
    SUCCESS(10000, "SUCCESS", "操作成功"),
    SYSTEM_ERROR(10001, "SYSTEM_ERROR", "系统异常"),
    CALL_SERVICE_ERROR(10002, "CALL_SERVICE_ERROR", "调用服务异常"),

    TOO_MANY_REQUEST(10003, "TOO_MANY_REQUEST", "访问过于频繁"),
    PERMISSION_DENY(10004, "PERMISSION_DENY", "用户服务无权限"),
    AUTHENTICATION_EXPIRED(10005, "AUTHENTICATION_EXPIRED", "身份认证过期"),
    IP_LIMIT(10006, "IP_LIMIT", "IP限制"),

    MISSING_REQUEST_PARAM(10007, "MISSING_REQUEST_PARAM", "缺少请求参数"),
    PARAM_INCORRECT(10008, "PARAM_INCORRECT", "请求参数格式不正确"),
    ARGUMENT_INVALID(10009, "ARGUMENT_INVALID", "请求参数校验不合法"),

    SUCCESS_EXIST(10010, "SUCCESS_EXIST", "记录已存在"),
    SUCCESS_ACCOUNT_NOT_EXIST(10011, "SUCCESS_ACCOUNT_NOT_EXIST", "账号不存在"),
    INSUFFICIENT_USER_PERMISSIONS(10012, "INSUFFICIENT_USER_PERMISSIONS", "用户权限不足"),
    INVALID_METHOD(10013, "INVALID_METHOD", "方法名不存在"),
    REQUEST_API_NOT_FOUND(10014, "REQUEST_API_NOT_FOUND", "请求的方法名不存在"),
    INVALID_FORMAT(10015, "INVALID_FORMAT", "无效的数据格式"),
    MISSING_APP_KEY(10016, "MISSING_APP_KEY", "缺少AppKey参数"),
    INVALID_APP_KEY(10017, "INVALID_APP_KEY ", "非法的AppKey"),
    INVALID_ACCESS_TOKEN(10018, "INVALID_ACCESS_TOKEN", "无效的access token"),
    USER_DOES_NOT_EXISTS(10019, "USER_DOES_NOT_EXISTS", "用户不存在"),
    CONTENT_IS_NULL(10020, "CONTENT_IS_NULL", "内容为空"),
    CONTENT_IS_ILLEGAL(10021, "CONTENT_IS_ILLEGAL ", "包含非法内容"),
    AUTH_FAILED(10022, "AUTH_FAILED", "验证失败"),
    ILLEGAL_OPERATION(10023, "ILLEGAL_OPERATION", "非法操作"),
    USERNAME_OR_PASSWORD_ERROR(10024, "USERNAME_OR_PASSWORD_ERROR", "用户名或者密码错误"),
    OPERATION_FAILED(10025, "OPERATION_FAILED", "操作失败"),
    VERSION_ERROR(10026, "VERSION_ERROR", "版本号错误"),
    SUCCESS_NOT_EXIST(10027, "SUCCESS_NOT_EXIST", "数据不存在"),
    REPEAT_OPERATION(10028, "REPEAT_OPERATION", "重复操作"),
    PHONE_NUMBER_HAS_BEEN_USED(10029, "PHONE_NUMBER_HAS_BEEN_USED", "该手机号已经被使用"),

    LOGOUT_BY_LIMIT_STRATEGY(10200, "LOGOUT_BY_LIMIT_STRATEGY", "您账号已被强制登出"),
    LOGIN_FAILED_FIVE_TIMES(10201, "LOGIN_FAILED_FIVE_TIMES", "密码错误5次，您的账号已经被封停30分钟"),
    LOGIN_FAILED_WITH_TIMES(10202, "LOGIN_FAILED_WITH_TIMES", "密码错误{0}次"),
    SMS_REQUEST_MORE_THAN_FIVE_TIMES(10203, "SMS_REQUEST_MORE_THAN_FIVE_TIMES", "短信验证码请求次数过多，请30分钟之后再获取"),
    CHANGE_DEVICE_MORE_THAN_FIVE_TIMES(10204, "CHANGE_DEVICE_MORE_THAN_FIVE_TIMES", "切换设备过于频繁，您的账号已经被封停30分钟"),
    DIFF_LAST_LOGIN_DEVICE(10205, "DIFF_LAST_LOGIN_DEVICE", "您的账号尝试在非常用设备登陆，请用使用短信验证码方式重新登陆"),
    ;

    private int code;
    private String name;
    private String desc;

    BizErrorCodeEnum(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

    public BizErrorCodeEnum getByCode(int code) {
        for (BizErrorCodeEnum baseResultEnum : values()) {
            if (baseResultEnum.getCode() == code) {
                return baseResultEnum;
            }
        }
        return null;
    }
}
