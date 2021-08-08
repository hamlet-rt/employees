package hamlet.employees.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    private String firstName;

    private String lastName;

    private String middleName;

    private String phoneNumber;

    private String address;
}
