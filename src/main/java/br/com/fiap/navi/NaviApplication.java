package br.com.fiap.navi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.component.page.AppShellConfigurator;

@SpringBootApplication
@Theme(value = "my-theme", variant = "dark")
public class NaviApplication implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(NaviApplication.class, args);
    }
}
