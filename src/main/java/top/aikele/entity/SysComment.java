package top.aikele.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

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
 * @since 2023-04-24
 */
@Data
@TableName("sys_comment")
@ApiModel("评论实体类")
public class SysComment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 文章id
     */
    @ApiModelProperty(value = "文章ID",required = true)
    @NotNull
    private Integer articleId;

    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容",required = true)
    @NotNull
    private String comment;

    /**
     * 发表评论用户的id
     */
    @ApiModelProperty(value = "评论用户id",required = true)
    @NotNull
    private Integer userId;

    /**
     * 父级评论id
     */
    @ApiModelProperty(value = "父评论id(无可不传)")
    private Integer pId;
    @ApiModelProperty(value = "root评论id(无可不传)")
    private Integer topId;
    /**
    子评论
    */
    @TableField(exist = false)
    @ApiModelProperty(hidden = true)
    private List<SysComment> children;
}
