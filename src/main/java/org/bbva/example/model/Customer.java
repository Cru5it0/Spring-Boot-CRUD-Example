package org.bbva.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Address is mandatory")
    private String address;

    @ManyToOne
    @JoinColumn(name = "id_email")
    private Email id_email;

    @ManyToOne
    @JoinColumn(name = "id_person")
    private Person id_person;

    @Column(name = "status")
    private Integer status;

}
