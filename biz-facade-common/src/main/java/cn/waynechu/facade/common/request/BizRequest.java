package cn.waynechu.facade.common.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhuwei
 * @date 2018/11/15 10:52
 */
@Data
@ApiModel(description = "请求对象")
public class BizRequest implements Serializable {
    private static final long serialVersionUID = 2688827622955322894L;
}