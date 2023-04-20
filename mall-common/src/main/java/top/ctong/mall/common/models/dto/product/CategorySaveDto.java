package top.ctong.mall.common.models.dto.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import javax.validation.constraints.Min;
import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 分类参数
 * </p>
 *
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-04-19 21:01
 */
@ApiModel("分类保存请求参数")
@Data
public class CategorySaveDto implements Serializable {

    private static final long serialVersionUID = 8793078653965022744L;

    /**
     * 分类名称
     */
    @ApiModelProperty("分类名称")
    @NotBlank(message = "分类名称不能为空")
    private String name;

    /**
     * 父分类id
     */
    @ApiModelProperty("父分类ID")
    @Min(value = 0, message = "父分类id异常")
    private Long parentCid;

    /**
     * 层级
     */
    @ApiModelProperty("层级")
    @Min(value = 1, message = "分类层级异常")
    private Integer catLevel;

    /**
     * 是否显示，0---不显示，1---显示
     */
    @ApiModelProperty("是否显示，0---不显示，1---显示")
    private Boolean showStatus;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    @Min(value = 0, message = "分类排序标识异常")
    private Integer sort;

    public Long getParentCid() {
        return parentCid == null ? (parentCid = 0L) : parentCid;
    }

    public Integer getCatLevel() {
        return catLevel == null ? (catLevel = 1) : catLevel;
    }
}
