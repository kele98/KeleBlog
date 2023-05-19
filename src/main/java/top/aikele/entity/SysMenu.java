package top.aikele.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import top.aikele.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author kele
 * @since 2023-04-14
 */
@Getter
@Setter
@TableName("sys_menu")
public class SysMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private String perms;

    private String menuName;

    private LocalDateTime creatTime;
}
