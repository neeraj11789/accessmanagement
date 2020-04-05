package sh.locus.accessmanagement.service;

import sh.locus.accessmanagement.model.ResourcePermission;
import sh.locus.accessmanagement.model.Role;

public interface AdminService {
    void addRole(String role);
    void addPermission(Role role, ResourcePermission permission);
    void revokePermission(Role role, ResourcePermission permission);

    void findRoleByName(String role);
    void removeRole(Role role);
}