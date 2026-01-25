package com.egin.inventory.model.entity;

import com.egin.branch.model.entity.BranchEntity;
import com.egin.common.model.entity.BaseEntity;
import com.egin.product.model.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "inventories")
public class InventoryEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private BranchEntity branchEntity;

    @ManyToOne
    private ProductEntity productEntity;

    @Column(nullable = false)
    private Integer quantity;



}
