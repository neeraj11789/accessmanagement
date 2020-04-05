package sh.locus.accessmanagement.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Rules {

    @Getter @Setter
    private Role role;

    @Getter @Setter
    private List<ResourceAccess> resourceAccessList;

}
