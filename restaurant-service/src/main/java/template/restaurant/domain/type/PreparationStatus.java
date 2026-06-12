package template.restaurant.domain.type;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PreparationStatus {

    PENDING("Pending"),
    ACCEPTED("Accepted"),
    PREPARING("Preparing"),
    READY("Ready"),
    REJECTED("Rejected");

    private final String description;
}
