package ru.hardy.udio.view;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import ru.hardy.udio.service.UserService;

@Route("register")
@AnonymousAllowed
public class RegisterView extends Composite {
    private final UserService userService;
    public RegisterView(UserService authService){
        this.userService = authService;
    }
    @Override
    protected Component initContent(){
        TextField username = new TextField("Имя пользователя");
        PasswordField password1 = new PasswordField("Пароль");
        PasswordField password2 = new PasswordField("Повторите пароль");
        return new VerticalLayout(
                new H2("Регистрация"),
                username,
                password1,
                password2,
                new Button("Отправить", event -> register(
                        username.getValue(),
                        password1.getValue(),
                        password2.getValue()
                ))
        );
    }
    private void register(String username, String password1, String password2){
        if (username.trim().isEmpty()) {
            Notification.show("Введите имя пользователя");
        } else if (password1.isEmpty()) {
            Notification.show("Введите пароль");
        } else if (!password1.equals(password2)) {
            Notification.show("Введите пароль еще раз");
        } else {
            userService.addUser(username, password1);
            Notification.show("Проверьте свой почтовый ящик.");
        }
    }
}
