import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.get(Student.class, 2);
        System.out.println(student);
        System.out.println("---------------------Subscriptions courses of student ----------------------------------");
        List<Course> courseList = student.getCourseSubscriptionList(); //many to many
        courseList.forEach(c -> {
            System.out.println(c);
            Subscription subscription = session.get(Subscription.class,
                    new Subscription.SubscriptionKey(student.getId(), c.getId()));
            System.out.println(subscription);
        });
        System.out.println("---------------------Purchases courses of student ----------------------------------");
        courseList = student.getCoursePurchaseList(); //many to many
        courseList.forEach(c -> {
            System.out.println(c);
            Purchase purchase = session.get(Purchase.class,
                    new Purchase.PurchaseKey(student.getName(), c.getName()));
            System.out.println(purchase);
        });

        Course course = session.get(Course.class, 11);
        System.out.println(course);
        System.out.println(course.getTeacher());  //one to many
        System.out.println("---------------------Subscriptions students of course----------------------------------");
        List<Student> studentList = course.getStudentSubscriptionList();   //many to many
        studentList.forEach(s -> {
            System.out.println(s);
            Subscription subscription = session.get(Subscription.class,
                    new Subscription.SubscriptionKey(s.getId(), course.getId()));
            System.out.println(subscription);
            //System.out.println(subscription);
        });
        System.out.println("---------------------Purchases students of course----------------------------------");
        studentList = course.getStudentPurchaseList();   //many to many
        studentList.forEach(s -> {
            System.out.println(s);
            Purchase purchase = session.get(Purchase.class,
                    new Purchase.PurchaseKey(s.getName(), course.getName()));
            System.out.println(purchase);
            //System.out.println(subscription);
        });


        transaction.commit();
        sessionFactory.close();
    }
}