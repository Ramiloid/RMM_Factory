package kz.hibernatetask;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;

public class HibernateTask {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<Phone> criteriaPhone = cb.createQuery(Phone.class);
        Root<Phone> rootPhone = criteriaPhone.from(Phone.class);
        //criteriaUserRole.select(rootPhone);
        criteriaPhone.select(rootPhone).where(cb.equal(rootPhone.get("type"), "mobile"));

        CriteriaQuery<User> criteriaUser = cb.createQuery(User.class);
        Root<User> rootUser = criteriaUser.from(User.class);
        Predicate[] predicates = new Predicate[2];
        predicates[0] = cb.like(rootUser.get("position"), "%friend%");
        predicates[1] = cb.like(rootUser.get("birthday"), "%2000%");
        criteriaUser.select(rootUser).where(predicates);

        User user1 = new User();
        user1.setName("Alex");
        user1.setBirthday("2000-05-07");
        user1.setPosition("Friend");
        session.save(user1);
        
        User user2 = new User();
        user2.setName("Dina");
        user2.setBirthday("2000-03-20");
        user2.setPosition("Best friend");
        session.save(user2);
        
        User user3 = new User();
        user3.setName("Vika");
        user3.setBirthday("2001-05-07");
        user3.setPosition("Sister");
        session.save(user3);

        org.hibernate.Transaction tr = session.beginTransaction();
        session.delete(user3);
//        session.delete(userRole2);
        tr.commit();

        Phone phone = new Phone();
        phone.setPhone("111-222-333");
        phone.setType("mobile");
        phone.setUser(new User("Ramil", "2000-05-22", "me"));
        session.save(phone);

        phone = (Phone) session.get(Phone.class, phone.getId());
        phone.setPhone("777-666-555");
        session.save(phone);

        Phone phone2 = new Phone();
        phone2.setPhone("444-222-333");
        phone2.setType("mobile");
        phone2.setUser(user2);
        session.save(phone2);

        Phone phone3 = new Phone();
        phone3.setPhone("444-555-555");
        phone3.setType("work");
        phone3.setUser(user2);
        session.save(phone3);

        List<User> resultsUser = session.createQuery(criteriaUser).getResultList();
        resultsUser.forEach((item) -> {
            System.out.println(item);
        });

        List<Phone> resultsUserRole = session.createQuery(criteriaPhone).getResultList();
        resultsUserRole.forEach((item) -> {
            System.out.println(item);
        });

        session.close();

        System.exit(0);
    }
}
