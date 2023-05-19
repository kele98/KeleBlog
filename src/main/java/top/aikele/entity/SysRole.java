package top.aikele.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("sys_role")
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String roleName;
}
