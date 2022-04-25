import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Teachers")
@Data
@ToString(onlyExplicitlyIncluded = true)
public class Teacher
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    @NaturalId
    @ToString.Include
    private String name;

    @ToString.Include
    private int salary;

    @ToString.Include
    private int age;
}
