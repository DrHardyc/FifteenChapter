package ru.hardy.udio.view;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Tag("sa-login-view")
@Route(value = LoginView.ROUTE)
@PageTitle("Login")
public class LoginView extends VerticalLayout implements BeforeEnterObserver{
    public static final String ROUTE = "login";
    private final LoginForm login = new LoginForm();
    //LoginOverlay login = new LoginOverlay();
    public LoginView(){
        //Button register = new Button("Регистрация");
        //H1 title = new H1("Hailoo");
        //title.add(register);
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.add(login);
        //layout.add(register);
        layout.setHorizontalComponentAlignment(Alignment.CENTER, login);
        //layout.setHorizontalComponentAlignment(Alignment.CENTER, register);
        add(layout);
        //login.getElement().getThemeList().add("dark");
        login.setForgotPasswordButtonVisible(false);
        getStyle()
                .set("display", "flex")
                .set("justify-content", "center");
        login.setI18n(CreateLoginI18n());
        addClassName("login-view");
        login.setAction("login");
        //register.addClickListener(e -> UI.getCurrent().navigate(RegisterView.class));
        login.setForgotPasswordButtonVisible(true);
    }
    @Override
    public void beforeEnter(BeforeEnterEvent event){
        login.setError(event.getLocation().getQueryParameters().getParameters().containsKey("error"));
    }
    private LoginI18n CreateLoginI18n(){
        LoginI18n i18n = LoginI18n.createDefault();
        LoginI18n.Form i18nForm = i18n.getForm();
        i18nForm.setTitle("Д - наблюдение");
        i18nForm.setUsername("Имя пользователя");
        i18nForm.setPassword("Пароль");
        i18nForm.setSubmit("Войти");
        i18nForm.setForgotPassword("Забыли пароль?");
        i18n.setForm(i18nForm);
        LoginI18n.ErrorMessage i18nErrorMessage = i18n.getErrorMessage();
        i18nErrorMessage.setTitle("Ошибка ввода данных");
        i18nErrorMessage.setMessage("Неверные имя пользователя или пароль.");
        i18n.setErrorMessage(i18nErrorMessage);
        return i18n;
    }
}