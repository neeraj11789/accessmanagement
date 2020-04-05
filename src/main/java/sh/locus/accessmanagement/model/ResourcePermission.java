package sh.locus.accessmanagement.model;

import lombok.Getter;
import lombok.Setter;

public class ResourcePermission {

    @Getter @Setter
    private Resource resource;

    @Getter @Setter
    private ActionType actionType;
}