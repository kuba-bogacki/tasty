package template.restaurant;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication(scanBasePackages = {"template.restaurant", "common.events"})
public class RestaurantApplication {

    public static void main(String[] args) {
        run(RestaurantApplication.class, args);
    }
}
