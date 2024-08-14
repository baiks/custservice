package com.customer.service.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"mobile_number", "email", "identification_number"}))
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String identificationNumber;
    private String address;
    private String mobileNumber;
    private Date dateOfBirth;
    private String country;
    private String email;
    @CreationTimestamp
    private String createdAt;
    @UpdateTimestamp
    private String updatedAt;
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Users user;
}
