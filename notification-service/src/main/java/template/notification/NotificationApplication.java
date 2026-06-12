package template.notification;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication(scanBasePackages = {"template.notification", "common.events"})
public class NotificationApplication {

    public static void main(String[] args) {
        run(NotificationApplication.class, args);
    }
}
