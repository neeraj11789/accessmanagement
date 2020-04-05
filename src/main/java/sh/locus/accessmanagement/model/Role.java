package sh.locus.accessmanagement.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Role {

    @Getter @Setter
    private String name;

    @Getter @Setter
    private List<ResourcePermission> resourceActionList;
}
