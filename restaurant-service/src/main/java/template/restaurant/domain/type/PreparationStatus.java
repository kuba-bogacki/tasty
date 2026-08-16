package template.restaurant.domain.type;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PreparationStatus {

    ACCEPTED("Accepted"),
    REJECTED("Rejected"),
    WITHDREW("Withdrew"),
    STARTED("Started"),
    COMPLETED("Completed");

    private final String description;
}
