package service;

import entity.User;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

public class UserService {

    private static String fusers = "users.fl";
    private static HashSet<User> users = new HashSet<>();

    public static void load () throws IOException {
        users.clear();
        File file = new File(fusers);
        if (file.exists()) {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            while(true)
            {
                try {
                    users.add((User) objectInputStream.readObject());
                }
                catch(EOFException e) {
                    break;
                }
                catch(Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
            objectInputStream.close();
        }
    }

    public static void save () throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fusers));
        for (User user : users) {
            objectOutputStream.writeObject(user);
        }
        objectOutputStream.close();
    }

    public static void add (String login, String pswd)  {
        users.add(new User(login, pswd));
    }

    public static User get (String login)  {
        for (User user : users) {
            if (user.getLogin().equals(login))
                return user;
        }
        return null;
    }

    public static void print () {
        for (User user : users) {
            System.out.println(user.getLogin());
            System.out.println(user.getPswd());
        }
    }
}
