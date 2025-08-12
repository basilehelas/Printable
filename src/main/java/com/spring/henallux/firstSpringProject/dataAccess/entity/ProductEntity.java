package com.spring.henallux.firstSpringProject.dataAccess.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Lob
    @Basic(fetch = FetchType.LAZY) // pas reload a chaque fois
    @Column(name = "image")
    private byte[] image;

    @Lob
    private String description;

    @Column(name = "category_id")
    private Integer categoryId;

    protected ProductEntity() {}

    public Integer getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public String getDescription() { return description; }
    public Integer getCategoryId() { return categoryId; }
    public byte[] getImage() { return image; }
}
