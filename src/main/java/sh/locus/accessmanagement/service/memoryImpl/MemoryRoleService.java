package sh.locus.accessmanagement.service.memoryImpl;

import org.springframework.util.StringUtils;
import sh.locus.accessmanagement.model.ResourcePermission;
import sh.locus.accessmanagement.model.Role;
import sh.locus.accessmanagement.service.RoleService;
import java.util.*;

import static java.util.Objects.requireNonNull;

public class MemoryRoleService implements RoleService {

    private final Map<String, Role> roles = new HashMap<>();

    @Override
    public void addRole(Role role) {
        if(StringUtils.isEmpty(role))
            throw new IllegalArgumentException("RoleName cannot be empty");

        if(roles.containsKey(role.getName()))
            throw new IllegalArgumentException("Role Already Exists");

        roles.put(role.getName(), role);
        System.out.println("Role Added to the System: " + role);
    }

    @Override
    public void addPermission(Role role, ResourcePermission permission) {
        requireNonNull(role, "Role cannot be empty");
        requireNonNull(permission, "Permission cannot be empty");

        if(roles.get(role.getName())==null)
            throw new IllegalArgumentException("Role Doesn't Exist");

        List<ResourcePermission> existingPermissions = role.getResourcePermissionList();
        if(existingPermissions == null){
            List<ResourcePermission> resourcePermissionList = new ArrayList<>();
            resourcePermissionList.add(permission);
            role.setResourcePermissionList(resourcePermissionList);
        }else {
            Iterator<ResourcePermission> iterator = existingPermissions.iterator();
            while (iterator.hasNext()){
                ResourcePermission current = iterator.next();
                if(current == permission){
                   throw new IllegalArgumentException("Permission Already Exists");
                }
            }
            role.getResourcePermissionList().add(permission);
        }
        System.out.println("Permission added to the Role: " + permission);
    }

    @Override
    public void revokePermission(Role role, ResourcePermission permission) {
        requireNonNull(role, "Role cannot be empty");
        requireNonNull(permission, "Permission cannot be empty");

        if(roles.get(role.getName())==null)
            throw new IllegalArgumentException("Role Doesn't Exist");

        List<ResourcePermission> existingPermissions = role.getResourcePermissionList();
        System.out.println("List of Permissions Before Removing: " + existingPermissions);
        Iterator<ResourcePermission> iterator = existingPermissions.iterator();
        boolean permissionExists = false;
        while (iterator.hasNext()){
            ResourcePermission current = iterator.next();
            if(current == permission){
                System.out.println("Removing the Permission " + permission);
                permissionExists = true;
                iterator.remove();
            }
        }

        if(!permissionExists)
            throw new IllegalArgumentException("Permission Doesn't Exist");
        System.out.println("List of Permissions Before Removing: " + existingPermissions);
    }

    @Override
    public Role findByName(String name) {
        requireNonNull(name, "Name Cannot be Empty");
        return roles.get(name);
    }

    @Override
    public void removeRole(Role role) {
        requireNonNull(role, "Role cannot be empty");
        if(roles.get(role.getName()) == null){
            throw new IllegalArgumentException("Role Doesn't Exist");
        }
        roles.remove(role.getName());
        System.out.println("Role Removed from the system: " + role);
    }
}
