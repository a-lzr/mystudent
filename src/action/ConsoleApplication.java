package action;

import entity.User;
import service.*;

import java.io.IOException;

import static service.UserService.*;

public class ConsoleApplication {
    private Reader in = new ConsoleReader();
    private Writer out = new ConsoleWriter();
    private User current = null;

    public void run() {
        try {
            load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            out.WriteString("Нажмите 1 - для авторизации пользователя, 2 - для регистрации нового пользователя, иначе произойдет выход из программы");
            switch (in.ReadString()) {
                case "1":
                    if (run_authorize()) {
                        run_task();
                        current = null;
                    }
                    continue;
                case "2":
                    run_registrate("");
                    continue;
                default:
                    return;
            }
        }
    }

    private Boolean run_authorize() {
        while (true) {
            out.WriteString("Введите логин для входа");
            String login = in.ReadString();
            User user = get(login);

            if (user == null) {
                out.WriteString("Пользователя с таким логином не сущестует.");
                out.WriteString("Нажмите 1 - для регистрации пользователя, 2 - для повторения операции, иначе операция будет отменена");
                switch (in.ReadString()) {
                    case "1":
                        if (run_registrate(login))
                            continue;
                        else
                            return false;
                    case "2":
                        continue;
                    default:
                        return false;
                }
            }
            else {
                for (int i = 0; i < 3; i++) {
                    out.WriteString("Введите пароль для входа");
                    if (user.equals(in.ReadString()))
                        return true;
                    else {
                        out.WriteString("Вход невозможен");
                    }
                }
                out.WriteString("Вход невозможен: вы исчерпали попытки для входа");
                out.WriteString("Нажмите 1 - для повторения операции, иначе операция будет отменена");
                if ("1".equals(in.ReadString())) {
                    continue;
                }
                return false;
            }
        }
    }

    private Boolean run_registrate(String login) {
        while (true) {
            if (login.isEmpty()) {
                out.WriteString("Введите логин для нового пользователя:");
                login = in.ReadString();
            }
            else {
                User user = get(login);
                if (user == null)
                    break;
                else {
                    out.WriteString("Введенный логин нельзя использовать для регистрации.");
                    out.WriteString("Нажмите 1 - для повторения операции, иначе операция будет отменена");
                    if ("1".equals(in.ReadString())) {
                        continue;
                    }
                    return false;
                }
            }
        }

        while (true) {
            out.WriteString("Введите пароль для входа");
            String pswd = in.ReadString();
            out.WriteString("Повторите пароль для входа");
            if (pswd.equals(in.ReadString())) {
                add(login, pswd);
                try {
                    save();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }
            else {
                out.WriteString("Регистрация невозможна: пароли не совпадают");
                out.WriteString("Нажмите 1 - для повторения операции, иначе операция будет отменена");
                if ("1".equals(in.ReadString())) {
                    continue;
                }
                return false;
            }
        }
    }

    private void run_task() {
        while (true) {
            out.WriteString("Нажмите 1 - для выполнения задачи, иначе произойдет деавторизация");
            if ("1".equals(in.ReadString())) {
                out.WriteString("Нажмите 1 - для добавления нового студента, Нажмите 2 - вывода списка студентов по группе");
                out.WriteString("Введите номер задачи");
                run_task(in.ReadInteger());
                continue;
            }
            return;
        }
    }

    private void run_task(int task) {
        switch (task) {
            case 1:
                StudentAction.new_student(out, in);
                break;
            case 2:
                out.WriteString("Введите номер группы");
                StudentAction.print_group(out, in.ReadString());
                break;
            default:
                System.out.println("Задачи с таким номером не существует");
        }
    }
}