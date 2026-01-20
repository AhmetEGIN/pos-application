package com.egin.branch.model.entity;

import com.egin.common.model.entity.BaseEntity;
import com.egin.store.model.entity.StoreEntity;
import com.egin.user.model.entity.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "branchs")
public class BranchEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String address;
    private String phone;

    @Email(message = "Email should be valid")
    private String email;

    @ElementCollection
    private List<String> workingDays;

    private LocalDateTime openTime;
    private LocalDateTime closeTime;

    @ManyToOne
    private StoreEntity storeEntity;

    @OneToOne(cascade = CascadeType.REMOVE)
    private UserEntity manager;


}

