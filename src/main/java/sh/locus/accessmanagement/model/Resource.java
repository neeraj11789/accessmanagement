package sh.locus.accessmanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.UUID;

public class Resource {

    /**
     * @Notes: Keeping ID as String to create Unique values which
     * would be taken care by Data Layer if DB is used
     */
    @Getter @Setter
    private String id = UUID.randomUUID().toString();

    @Getter @Setter
    private String name;

    public Resource(String name) {
        this.name = name;
    }

    // Other fields defined here based on the requirement
}
