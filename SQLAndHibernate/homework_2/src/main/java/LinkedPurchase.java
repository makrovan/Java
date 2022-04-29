import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "LinkedPurchaseList")
@AllArgsConstructor
public class LinkedPurchase {
    @EmbeddedId
    private LinkedPurchase.LinkedPurchaseKey id;

    @Column(name = "student_id", insertable = false, updatable = false)
    private int student_id;

    @Column(name = "course_id", insertable = false, updatable = false)
    private int course_id;

    @Embeddable
    @Data
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class LinkedPurchaseKey implements Serializable {

        @Column(name = "student_id")
        private int studentId;

        @Column(name = "course_id")
        private int courseId;
    }
}
