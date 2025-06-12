package org.example.userservice;

import org.example.userservice.dao.UserDao;
import org.example.userservice.dao.UserDaoImpl;
import org.example.userservice.entity.User;

import java.util.Scanner;

public class App {
    private static final UserDao userDao = new UserDaoImpl();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== User service ===");
            System.out.println("1. Создать пользователя");
            System.out.println("2. Найти пользователя");
            System.out.println("3. Обновить пользователя");
            System.out.println("4. Удалить пользователя");
            System.out.println("5. Список всех пользователей");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1 -> {
                    System.out.print("Имя: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Возраст: ");
                    int age = Integer.parseInt(scanner.nextLine());

                    User user = new User();
                    user.setName(name);
                    user.setEmail(email);
                    user.setAge(age);
                    userDao.create(user);
                    System.out.println("Пользователь успешно создан!");
                }
                case 2 -> {
                    System.out.print("ID пользователя: ");
                    Long id = Long.parseLong(scanner.nextLine());
                    User user = userDao.read(id);
                    System.out.println(user != null ?
                            String.format("Пользователь [ID=%d, Имя='%s', Email='%s', Возраст=%d]",
                                    user.getId(), user.getName(), user.getEmail(), user.getAge())
                            : "Пользователь не найден.");
                }
                case 3 -> {
                    System.out.print("ID пользователя: ");
                    Long id = Long.parseLong(scanner.nextLine());
                    User user = userDao.read(id);
                    if (user != null) {
                        System.out.print("Имя: ");
                        user.setName(scanner.nextLine());
                        System.out.print("Email: ");
                        user.setEmail(scanner.nextLine());
                        System.out.print("Возраст: ");
                        user.setAge(Integer.parseInt(scanner.nextLine()));
                        userDao.update(user);
                        System.out.println("Данные пользователя обновлены!");
                    } else {
                        System.out.println("Пользователь не найден.");
                    }
                }
                case 4 -> {
                    System.out.print("ID пользователя: ");
                    Long id = Long.parseLong(scanner.nextLine());
                    userDao.delete(id);
                    System.out.println("Пользователь удален!");
                }
                case 5 -> {
                    System.out.println("\nСписок пользователей:");
                    userDao.findAll().forEach(u ->
                            System.out.printf("Пользователь [ID=%d, Имя='%s', Email='%s', Возраст=%d]%n",
                                    u.getId(), u.getName(), u.getEmail(), u.getAge())
                    );
                }
                case 0 -> {
                    System.out.println("Завершение работы...");
                    System.exit(0);
                }
                default -> System.out.println("Неверный вариант.");
            }
        }
    }
}