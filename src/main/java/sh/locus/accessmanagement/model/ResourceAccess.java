package sh.locus.accessmanagement.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

public class ResourceAccess {

    @Getter @Setter
    private Resource resource;

    @Getter @Setter
    private List<ActionType> actionTypes;
}