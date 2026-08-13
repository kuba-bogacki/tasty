package template.restaurant.domain.type;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PreparationStatus {

    ACCEPTED("Accepted"),
    REJECTED("Rejected"),
    WITHDRAW("Withdraw"),
    IN_PROGRESS("In progress"),
    READY("Ready");

    private final String description;
}
