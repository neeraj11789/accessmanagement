package sh.locus.accessmanagement.service;

import sh.locus.accessmanagement.model.ResourcePermission;
import sh.locus.accessmanagement.model.Role;

public interface RoleService {
    void addRole(Role role);
    void addPermission(Role role, ResourcePermission permission);
    void revokePermission(Role role, ResourcePermission permission);

    Role findByName(String role);
    void removeRole(Role role);
}