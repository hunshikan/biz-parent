package cn.waynechu.renting.facade.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author zhuwei
 * @date 2019/2/21 16:43
 */
@Data
public class ShopConfigLogDTO {

    private Long shopId;

    private String changeItem;

    private Integer beforeValue;

    private Integer afterValue;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;
}
