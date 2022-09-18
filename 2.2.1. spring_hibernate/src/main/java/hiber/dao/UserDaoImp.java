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
   }
}
