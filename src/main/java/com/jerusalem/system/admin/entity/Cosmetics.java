package com.jerusalem.system.admin.entity;

import lombok.Data;
import lombok.ToString;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * 
 * </p>
 *
 * @author jerusalem
 * @since 2019-12-24
 */

@Entity
@Data
@ToString
@Table(name = "cosmetics")
public class Cosmetics{

    /**
     * 管理员ID
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品价格
     */
    private String price;

    /**
     * 商品库存量
     */
    private String inventory;

    /**
     * 商品生产厂家
     */
    private String manufacturer;

    /**
     * 商品品牌
     */
    private String brand;

    /**
     * 商品图片
     */
    private String picture;


}
