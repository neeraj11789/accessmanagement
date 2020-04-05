package sh.locus.accessmanagement.service.memoryImpl;

import sh.locus.accessmanagement.model.ResourcePermission;
import sh.locus.accessmanagement.model.Role;
import sh.locus.accessmanagement.service.AdminService;

import java.util.HashMap;
import java.util.Map;

public class MemoryAdminService implements AdminService {

    private final Map<String, ResourcePermission> resourcePermissionMap = new HashMap<>();


    @Override
    public void addRole(String role) {

    }

    @Override
    public void addPermission(Role role, ResourcePermission permission) {

    }

    @Override
    public void revokePermission(Role role, ResourcePermission permission) {

    }

    @Override
    public void findRoleByName(String role) {

    }

    @Override
    public void removeRole(Role role) {

    }
}
