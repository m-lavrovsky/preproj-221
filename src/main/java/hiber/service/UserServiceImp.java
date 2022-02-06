package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Override
   //public void add(User user, String model, int series) {      userDao.add(user,model,series);   }
   public void add(Car car) {
      userDao.add(car);
   }

   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   //@Transactional(readOnly = true)
   @Override
   public List<Car> listCars() {
      return userDao.listCars();
   }

   @Override
   public User getUserByCarModelAndSeries(String model, int series) {
      return userDao.getUserByCarModelAndSeries(model, series);
   }
}