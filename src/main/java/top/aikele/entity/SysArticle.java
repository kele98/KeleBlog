package top.aikele.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
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
 * @since 2023-04-21
 */
@Data
@TableName("sys_article")
@ApiModel("文章实体类")
public class SysArticle extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @NotNull
    @ApiModelProperty(value ="分类id",required = true)
    private Integer categoryId;
    @NotNull
    @ApiModelProperty(value ="标题",required = true)
    private String title;
    @NotNull
    @ApiModelProperty(value = "内容",required = true)
    private String content;
    @NotNull
    @ApiModelProperty(value ="用户id",required = true)
    private Integer userId;
    @NotNull
    @ApiModelProperty("点击量")
    private Integer views;
}
