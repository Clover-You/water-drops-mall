package top.ctong.mall.common.models.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 分类实体
 * </p>
 *
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-04-08 18:51
 */
@Data
@ApiModel("分类实体")
public class CategoryEntity implements Serializable {

    /**
     * 分类id
     */
    @ApiModelProperty("分类ID")
    private Long catId;

    /**
     * 分类名称
     */
    @ApiModelProperty("分类名称")
    private String name;

    /**
     * 父分类id
     */
    @ApiModelProperty("父分类ID")
    private Long parentCid;

    /**
     * 层级
     */
    @ApiModelProperty("层级")
    private Integer catLevel;

    /**
     * 是否显示，0---不显示，1---显示
     */
    @ApiModelProperty("是否显示，0---不显示，1---显示")
    private Byte showStatus;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer sort;

    /**
     * 图标地址
     */
    @ApiModelProperty("图标地址")
    private String icon;

    /**
     * 计量单位
     */
    @ApiModelProperty("计量单位")
    private String productUnit;

    /**
     * 商品计数
     */
    @ApiModelProperty("商品计数")
    private String productCount;
}
