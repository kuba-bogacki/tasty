package template.order;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication(scanBasePackages = {"template.order", "common.events"})
public class OrderApplication {

    public static void main(String[] args) {
        run(OrderApplication.class, args);
    }
}
