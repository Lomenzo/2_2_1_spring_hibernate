package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(Car car) {
      String hqlT = "FROM User U WHERE U.car = (FROM Car C WHERE C.model = " + (char) 39 + car.getModel() + (char) 39 + "AND C.series = " + car.getSeries() + ")";
      // моя идея упорно отказывалась работать с экранированием \' и \" поэтому взял char UTF-8 для кавычки
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hqlT);
      return query.getSingleResult();
      //List<User> idList = query.getResultList();
      //idList.forEach(System.out::println);
      //return (User) queryCarID;
      //System.out.println(user);
      //return sessionFactory.getCurrentSession().get(User.class, (long)22);
      //return sessionFactory.getCurrentSession().createQuery("FROM cars c WHERE c.model = VAZ");


      //return sessionFactory.getCurrentSession().get(User.class, (long)22);

      /*String hql = "FROM cars c WHERE c.model = VAZ";
      Query query = session.createQuery(hql);
      List results = query.list();

      String hql = "SELECT E.firstName FROM Employee E";
      Query query = session.createQuery(hql);*/

      //return sessionFactory.getCurrentSession().load(User.class, car.getModel());

      //методы .get и .load ищут по (класс + ключ-Id)
      //не удалось заставить их искать по (класс + другое поле)
      //нужно найти способ искать по полям (не только ключу)

   }
}
