package com.egin.store.model.entity;

import com.egin.common.model.entity.BaseEntity;
import com.egin.store.model.StoreContact;
import com.egin.store.model.enums.StoreStatus;
import com.egin.user.model.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "stores")
public class StoreEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String brand;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity storeAdmin;

    private String description;
    private String storeType;
    private StoreStatus status;

    @Embedded
    private StoreContact storeContact = new StoreContact();

    @PrePersist
    protected void onCreate() {
//        super.onCreate();
        this.status = StoreStatus.PENDING;
    }


}
