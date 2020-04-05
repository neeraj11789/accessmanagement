package sh.locus.accessmanagement.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class User {

    @Getter @Setter
    private String id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private List<Role> roles;

}
