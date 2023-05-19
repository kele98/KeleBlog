package top.aikele.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Blob;
import java.util.List;
import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;
import top.aikele.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 *
 * </p>
 *
 * @author kele
 * @since 2023-04-14
 */
@Data
@NoArgsConstructor
@TableName("sys_user")
public class SysUser extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    private String password;
    @NotNull
    private String icon = "beijing.aliyuncs.com/icon/default.png";
    @TableField(exist = false)
    private List<String> perms;

    public SysUser(String username, String password, String icon) {
        this.username = username;
        this.password = password;
        this.icon = icon;
    }
}
