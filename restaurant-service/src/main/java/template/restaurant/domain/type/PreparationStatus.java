package template.restaurant.domain.type;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PreparationStatus {

    ACCEPTED("Accepted"),
    REJECTED("Rejected"),
    CANCELLED("Cancelled"),
    IN_PROGRESS("In progress"),
    READY("Ready");

    private final String description;
}
