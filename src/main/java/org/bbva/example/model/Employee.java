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
    private Long id_email;

    @ManyToOne
    @JoinColumn(name = "id_persona")
    private Person id_person;
    @NotBlank
    private int status;

    @ManyToMany
    @JoinColumn(name = "sales_id")
    private Sales sales_id;

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

    public Long getId_email() {
        return id_email;
    }

    public void setId_email(Long id_email) {
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

    public Sales getSales_id() {
        return sales_id;
    }

    public void setSales_id(Sales sales_id) {
        this.sales_id = sales_id;
    }
}
