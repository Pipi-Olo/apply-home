package com.pipiolo.home.domain;

import com.pipiolo.home.constant.Role;
import com.pipiolo.home.dto.UserRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseEntity implements UserDetails {

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private Boolean subscribed;

    @ElementCollection
    @Column
    private final Set<String> regions = new HashSet<>();

    @Builder
    public User(
            String email,
            String password,
            Role role,
            Boolean subscribed,
            Set<String> regions
    ) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.subscribed = subscribed;
        if (!regions.isEmpty()) {
            this.regions.addAll(regions);
        }
    }

    public void update(UserRequest request) {
        this.email = request.email();
        this.subscribed = request.subscribed();
        if (!regions.isEmpty()) {
            this.regions.addAll(regions);
        }
    }

    public void addRegion(String region) {
        regions.add(region);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton((GrantedAuthority) () -> role.getRole());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
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
