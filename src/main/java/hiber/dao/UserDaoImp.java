package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
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
   public List<User> getUserByCar(String model, int series) {
      TypedQuery query = sessionFactory.getCurrentSession().createQuery("FROM Car where model = :model AND series = :series");
      query.setParameter("model", model);
      query.setParameter("series", series);
      List<Car> cars= query.getResultList();
      List<User> users = new ArrayList<>();
      for (Car car : cars) {
         users.add(car.getUser());
      }
      return users;
   }

}
