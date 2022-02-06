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
      return sessionFactory.getCurrentSession().createQuery("from User").getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<Car> listCars() {
      return sessionFactory.getCurrentSession().createQuery("from Car").getResultList();
   }

   @Override
   public User getUserByCarModelAndSeries(String model, int series) {
      TypedQuery<User> query2=sessionFactory.getCurrentSession().createQuery("SELECT car.user FROM Car car JOIN car.user" +
                                                                              " where car.model = :model and car.series = :series");
      query2.setParameter("model",model);
      query2.setParameter("series",series);

      try {
         return query2.getSingleResult();
      }
      catch (NoResultException e) {
         return null;
      }
   }
}
