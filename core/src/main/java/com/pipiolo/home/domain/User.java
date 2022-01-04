package com.pipiolo.home.domain;

import com.pipiolo.home.dto.UserRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User extends BaseEntity {

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Boolean isSubscribed;

    @Builder
    public User(String email, Boolean isSubscribed) {
        this.email = email;
        this.isSubscribed = isSubscribed;
    }

    public void update(UserRequest request) {
        this.email = request.email();
        this.isSubscribed = request.isSubscribed();
    }
}
