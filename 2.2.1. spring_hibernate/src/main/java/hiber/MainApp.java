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

      userService.add(new User("Василий", "Петрович", "user1@mail.ru").setCar(new Car("ВАЗ", 2106)));
      userService.add(new User("Пахан", "Местный", "user2@mail.ru").setCar(new Car("Mersedes", 600)));
      userService.add(new User("Иван", "Мажоров", "user3@mail.ru").setCar(new Car("Ferrari", 50)));
      userService.add(new User("Санёк", "Настолбович", "user4@mail.ru").setCar(new Car("ToyotaMark", 2)));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         //System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      context.close();
   }
}
