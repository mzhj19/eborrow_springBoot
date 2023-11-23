package com.mzhj19.eborrow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    // TO DO 5 pictures
    @Lob
    @Column(name = "image1", length = 1000, nullable = false)
    private byte[] image1;

/*    @Lob
    @Column(name = "image2")
    private byte[] image2;

    @Lob
    @Column(name = "image3")
    private byte[] image3;

    @Lob
    @Column(name = "image4")
    private byte[] image4;*/

    @Column(name = "borrow_type", nullable = false)
    private String borrowType;

    @Column(name = "per_unit_price", nullable = false)
    private String perUnitPrice;

    @Column(name = "mobile_no", nullable = false)
    private String mobileNo;

    @ManyToOne(
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "owner_id")
    private User ownerId;

    @Column(name = "division", nullable = false)
    private String division;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "sub_district", nullable = false)
    private String subDistrict;

    //@Enumerated(EnumType.STRING)
    //@Column(name = "status", columnDefinition = "ENUM('BORROWED', 'RETURNED') DEFAULT 'RETURNED'")
    @Column(name = "status")
    private String status;

    @JsonIgnore
    @Column(name = "created_at", insertable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @JsonIgnore
    @Column(name = "updated_at", insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;
}
