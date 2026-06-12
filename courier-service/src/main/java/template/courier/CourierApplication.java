package template.courier;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication(scanBasePackages = {"template.courier", "common.events"})
public class CourierApplication {

    public static void main(String[] args) {
        run(CourierApplication.class, args);
    }
}
