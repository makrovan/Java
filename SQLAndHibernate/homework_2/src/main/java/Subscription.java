import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Subscriptions")
@ToString(onlyExplicitlyIncluded = true)
@Data
public class Subscription {

    @EmbeddedId
    private SubscriptionKey id;

    //@Column(name = "student_id", insertable = false, updatable = false)
    //private int studentId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="student_id",insertable=false, updatable=false)
    private Student student;

    //@Column(name = "course_id", insertable = false, updatable = false)
    //private int courseId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="course_id",insertable=false, updatable=false)
    private Course course;

    @Column(name = "subscription_date")
    @ToString.Include
    private Date subscriptionDate;

    @Embeddable
    @Data
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class SubscriptionKey implements Serializable {

        @Column(name = "student_id")
        private int studentId;

        @Column(name = "course_id")
        private int courseId;
    }
}
