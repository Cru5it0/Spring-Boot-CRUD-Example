package org.bbva.example.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "sales")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String description;

    @ManyToMany
    @JoinColumn(name = "employee_id")
    private Employee employee_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employee getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Employee employee_id) {
        this.employee_id = employee_id;
    }
}
