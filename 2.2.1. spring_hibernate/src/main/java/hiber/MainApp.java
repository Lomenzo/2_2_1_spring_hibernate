package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User userTest = userService.getUserByCar(new Car("Ferrari", 50));

      System.out.println("Id = "+userTest.getId());
      System.out.println("First Name = "+userTest.getFirstName());
      System.out.println("Last Name = "+userTest.getLastName());
      System.out.println("Email = "+userTest.getEmail());
      System.out.println("Car = "+userTest.getCar());

     /* User user1 = new User("Vasiliy", "Petrovich", "user1@mail.ru");
      User user2 = new User("Пахан", "Местный", "user2@mail.ru");
      User user3 = new User("Иван", "Мажоров", "user3@mail.ru");
      User user4 = new User("Санёк", "Настолбович", "user4@mail.ru");

      user1.setCar(new Car("VAZ", 2106));
      user2.setCar(new Car("Mersedes", 600));
      user3.setCar(new Car("Ferrari", 50));
      user4.setCar(new Car("ToyotaMark", 2));

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }*/

      context.close();
   }
}
