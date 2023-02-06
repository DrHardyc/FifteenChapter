package ru.hardy.udio.config;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.shared.communication.PushMode;

@Push(PushMode.AUTOMATIC)
public class ShellConfig implements AppShellConfigurator {
}


