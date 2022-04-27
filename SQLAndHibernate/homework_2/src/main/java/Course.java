import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Courses")
@Data
@ToString(onlyExplicitlyIncluded = true)
public class Course implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy = "course")
    private List<Subscription> subscriptions;   //все подписки курса
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Subscriptions",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private List<Student> studentSubscriptionList;             //студенты подписанные на курс

    @NaturalId
    @ToString.Include
    private String name;
    @OneToMany(mappedBy = "course")
    private List<Purchase> purchases;   //все покупки курса
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PurchaseList",
            joinColumns = {@JoinColumn(name = "course_name", referencedColumnName = "name")},
            inverseJoinColumns = {@JoinColumn(name = "student_name", referencedColumnName = "name")})
    private List<Student> studentPurchaseList;             //студенты купившие курс

    @ToString.Include
    private int duration;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    @ToString.Include
    private CourseType type;

    @ToString.Include
    private String description;

    //@Column(name = "teacher_id")
    @ManyToOne(cascade = CascadeType.ALL)       //один учитель может вести много курсов
    @ToString.Include
    private Teacher teacher;

    @Column(name = "students_count")
    @ToString.Include
    private int studentsCount;

    @ToString.Include
    private int price;

    @Column(name = "price_per_hour")
    @ToString.Include
    private float pricePerHour;
}
