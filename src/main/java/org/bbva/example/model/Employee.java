package org.bbva.example.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @NotBlank
    private String Job;
    @ManyToOne
    @JoinColumn(name = "id_email")
    private Email id_email;

    @ManyToOne
    @JoinColumn(name = "id_persona")
    private Person id_person;

    @ManyToOne
    @JoinColumn(name = "id_sales")
    private Sales id_sales;

    @NotBlank
    private int status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJob() {
        return Job;
    }

    public void setJob(String job) {
        Job = job;
    }

    public Email getId_email() {
        return id_email;
    }

    public void setId_email(Email id_email) {
        this.id_email = id_email;
    }

    public Person getId_person() {
        return id_person;
    }

    public void setId_person(Person id_person) {
        this.id_person = id_person;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Sales getId_sales() {
        return id_sales;
    }

    public void setId_sales(Sales id_sales) {
        this.id_sales = id_sales;
    }
}
