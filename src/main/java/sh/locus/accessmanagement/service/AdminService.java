package sh.locus.accessmanagement.service;

import sh.locus.accessmanagement.model.ActionType;
import sh.locus.accessmanagement.model.Resource;
import sh.locus.accessmanagement.model.ResourcePermission;

import java.util.List;

public interface AdminService {
    void addResourceAction(Resource resource, ActionType actionType);
    void removeResourceAction(Resource resource, ActionType actionType);

    void addRole(String name, List<ResourcePermission> permissions);
    void remokePermissionFromRole(String role, ResourcePermission permission);
    void deleteRole(String role);
}