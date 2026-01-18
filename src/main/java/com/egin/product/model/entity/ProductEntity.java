package com.egin.product.model.entity;

import com.egin.common.model.entity.BaseEntity;
import com.egin.store.model.entity.StoreEntity;
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
@Entity
@Table(name = "products")
@SuperBuilder
public class ProductEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    @Column(nullable = false, unique = true)
    private String sku;

    private String description;

    private Double mrp;
    private Double sellingPrice;
    private String brand;
    private String imageUrl;

    @ManyToOne
    private CategoryEntity categoryEntity;

    @ManyToOne
    private StoreEntity storeEntity;


}
