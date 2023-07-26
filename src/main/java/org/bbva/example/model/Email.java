package org.bbva.example.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "emails")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @javax.validation.constraints.Email
    @Column(nullable = false, length = 50)
    private String email;

    @Column(length = 20, nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Employee employee_id) {
        this.employee_id = employee_id;
    }
}
