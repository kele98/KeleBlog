package top.aikele.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @projectName: KeleBlog
 * @package: top.aikele.entity
 * @className: BaseEntity
 * @author: Kele
 * @description: TODO
 * @date: 2023/4/14 17:42
 * @version: 1.0
 */

@Data
public class BaseEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
    @TableLogic
    private Integer isDelete;
}
