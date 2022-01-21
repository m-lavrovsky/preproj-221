package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
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
   public void add(Car car) { sessionFactory.getCurrentSession().save(car); }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<Car> listCars() {
      TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car car where car.series = 6");
      return query.getResultList();
   }

   @Override
   public User getUserByCarModel(String model, int series) {
      TypedQuery<User> query2=sessionFactory.getCurrentSession().createQuery("SELECT car.user FROM Car car JOIN car.user where car.model = '"+
                                                                              model+"' and car.series = "+series);
      try {
         User result = query2.getSingleResult();
         return result;
      }
      catch (NoResultException e) {
         return null;
      }

   }
}
