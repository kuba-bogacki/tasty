package template.courier.domain.type;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CourierStatus {

    AVAILABLE("Available"),
    PICKING_DELIVERY("Picking delivery"),
    BUSY("Busy");

    private final String description;
}
