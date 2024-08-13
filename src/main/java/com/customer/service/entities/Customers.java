package com.customer.service.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"mobile_number", "email", "national_id"}))
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String nationalId;
    private String address;
    private String mobileNumber;
    private Date dateOfBirth;
    private String country;
    private String email;
    private String createdAt;
    private String updatedAt;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Users user;
}
