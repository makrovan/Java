import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Students")
@Data
@ToString(onlyExplicitlyIncluded = true)
public class Student implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy = "student")
    private List<Subscription> subscriptions;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Subscriptions",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courseSubscriptionList;

    //@NaturalId
    @ToString.Include
    private String name;
    @OneToMany(mappedBy = "student")
    private List<Purchase> purchases;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PurchaseList",
            joinColumns = @JoinColumn(name = "student_name", referencedColumnName = "name"),
            inverseJoinColumns = @JoinColumn(name = "course_name", referencedColumnName = "name"))
    private List<Course> coursePurchaseList;

    @ToString.Include
    private int age;

    @Column(name = "registration_date")
    //@Temporal(TemporalType.TIMESTAMP)
    @ToString.Include
    private Date registrationDate;
}
