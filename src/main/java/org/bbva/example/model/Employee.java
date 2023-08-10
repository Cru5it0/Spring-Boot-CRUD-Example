package org.bbva.example.model;

import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Builder
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job")
    @NotBlank
    private String Job;
    @ManyToOne
    @JoinColumn(name = "id_email")
    private Email id_email;

    @ManyToOne
    @JoinColumn(name = "id_person")
    private Person id_person;

    @ManyToOne
    @JoinColumn(name = "id_sales")
    private Sales id_sales;

    @Column(name = "status")
    private Integer status;

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

    public Employee() {}
    public Employee(Long id, String job, Email id_email, Person id_person, Sales id_sales, int status) {
        this.id = id;
        Job = job;
        this.id_email = id_email;
        this.id_person = id_person;
        this.id_sales = id_sales;
        this.status = status;
    }
}
