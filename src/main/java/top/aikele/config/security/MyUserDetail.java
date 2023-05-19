package top.aikele.config.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import top.aikele.entity.SysUser;

import java.util.Collection;
import java.util.Objects;

/**
 * @projectName: KeleBlog
 * @package: top.aikele.config.security
 * @className: MyUserDetail
 * @author: Kele
 * @description: TODO
 * @date: 2023/4/14 19:37
 * @version: 1.0
 */
@Data
public class MyUserDetail implements UserDetails {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyUserDetail that = (MyUserDetail) o;
        return Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }

    public MyUserDetail(SysUser user) {
        this.user = user;
    }

    private SysUser user;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
