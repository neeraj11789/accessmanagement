package sh.locus.accessmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ResourcePermission {

    @Getter @Setter
    private Resource resource;

    @Getter @Setter
    private ActionType actionType;
}