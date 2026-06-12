package template.courier.domain.type;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CourierStatus {

    AVAILABLE("Available"),
    BUSY("Busy");

    private final String description;
}
