package com.pipiolo.home.domain;

import com.pipiolo.home.dto.UserRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User extends BaseEntity {

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Boolean subscribed;

    @ElementCollection
    @Column
    private final Set<String> regions = new HashSet<>();

    @Builder
    public User(String email, Boolean subscribed, Set<String> regions) {
        this.email = email;
        this.subscribed = subscribed;
//        this.regions.addAll(regions);
    }

    public void update(UserRequest request) {
        this.email = request.email();
        this.subscribed = request.subscribed();
//        this.regions.addAll(request.regions());
    }

    public void addRegion(String region) {
        regions.add(region);
    }
}
