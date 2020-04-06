package sh.locus.accessmanagement.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

public class User {

    @Getter @Setter
    private String id = UUID.randomUUID().toString();

    @Getter @Setter
    private String name;

    @Getter @Setter
    private List<Role> roles;

    public User(String name) {
        this.name = name;
    }

    public User(String name, List<Role> roles) {
        this.name = name;
        this.roles = roles;
    }
}