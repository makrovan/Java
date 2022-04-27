import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "PurchaseList")
@Data
@ToString(onlyExplicitlyIncluded = true)
public class Purchase {
    @EmbeddedId
    private Purchase.PurchaseKey id;


    //private String studentName;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="student_name", referencedColumnName = "name", insertable=false, updatable=false)
    private Student student;

    //private String courseName;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="course_name", referencedColumnName = "name", insertable=false, updatable=false)
    private Course course;

    @ToString.Include
    private int price;

    @Column(name = "subscription_date")
    @ToString.Include
    private Date subscriptionDate;

    @Embeddable
    @Data
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class PurchaseKey implements Serializable {

        @Column(name = "student_name")
        private String studentName;

        @Column(name = "course_name")
        private String courseName;
    }
}
