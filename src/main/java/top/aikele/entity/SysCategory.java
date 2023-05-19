package top.aikele.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import top.aikele.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * <p>
 *
 * </p>
 *
 * @author kele
 * @since 2023-04-27
 */
@Getter
@Setter
@TableName("sys_category")
public class SysCategory extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 文章分类名
     */
    @NotNull
    @ApiModelProperty("分类名称")
    private String class_name;
}
