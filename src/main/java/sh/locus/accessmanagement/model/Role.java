package sh.locus.accessmanagement.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

public class Role {

    @Getter @Setter
    private String id = UUID.randomUUID().toString();

    @Getter @Setter
    private String name;

    @Getter @Setter
    private List<ResourcePermission> resourcePermissionList;

    public Role(String name) {
        this.name = name;
    }

    public Role(String name, List<ResourcePermission> resourcePermissionList) {
        this.name = name;
        this.resourcePermissionList = resourcePermissionList;
    }
}