package org.levelup.reflection;

import org.levelup.trello.model.User;

import java.lang.reflect.Field;

public class ReflectionApp {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        User user = new User(1, "test user", "tuser", "t@user.com");

        //Через объект нашего класса
        Class <?> userClass = user.getClass();
        //Через литерал .class
        Class <?> userClassLiteral = User.class;
        //Comparator<Integer> comp = new OurComparator();
        //comp.getClass = OurComparator;

        System.out.println("Name: " +  userClass.getName());
        Field[] fields = userClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getType().getName() + " " + field.getName());
        }

        Field loginField = userClass.getDeclaredField("login");
        loginField.setAccessible(true);
        String login = (String) loginField.get(user);
        System.out.println("Login value: " + login);

        loginField.set(user, "newtestlogin");
        String newLogin = (String) loginField.get(user);
        System.out.println("Login value: " + newLogin);

    }
}
