package sh.locus.accessmanagement.service.memoryImpl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sh.locus.accessmanagement.model.*;
import sh.locus.accessmanagement.service.ResourceService;
import sh.locus.accessmanagement.service.UserService;

import java.util.*;

import static java.util.Objects.requireNonNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemoryUserService implements UserService {

    private static final Map<String, User> users = new HashMap<>();

    @NonNull
    private ResourceService resourceService;

    /**
     * Add User
     * @param user
     */
    @Override
    public void add(User user) {
        requireNonNull(user, "User Cannot be Empty");
        if(users.containsKey(user.getName()))
            throw new IllegalArgumentException("User Already Exists");
        users.put(user.getName(), user);
        log.info("User Created: " + user);
    }

    /**
     * Delete User
     * @param user
     */
    @Override
    public void delete(User user) {
        requireNonNull(users, "User Cannot be Empty");
        if(users.isEmpty())
            throw new NullPointerException("No more users to delete");
        users.remove(user.getName());
    }

    /**
     * Find user by name
     * @param name
     * @return
     */
    @Override
    public User findByName(String name) {
        requireNonNull(name, "Name Cannot be Empty");
        return users.get(name);
    }

    /**
     * Add User Role
     * @param user
     * @param role
     */
    @Override
    public void addRole(User user, Role role) {
        requireNonNull(role, "Role cannot be empty");
        requireNonNull(user, "User cannot be empty");

        if(users.get(user.getName())==null)
            throw new IllegalArgumentException("User Doesn't Exist");

        List<Role> existingRoles = user.getRoles();
        if(existingRoles == null){
            List<Role> roles = new ArrayList<>();
            roles.add(role);
            user.setRoles(roles);
        }else {
            Iterator<Role> iterator = existingRoles.iterator();
            while (iterator.hasNext()){
                Role current = iterator.next();
                if(current == role){
                    throw new IllegalArgumentException("Role Already Exists");
                }
            }
            user.getRoles().add(role);
        }
        System.out.println("Role added to the User: " + role);
    }

    /**
     * Remove Role of the user
     * @param user
     * @param role
     */
    @Override
    public void removeRole(User user, Role role) {
        requireNonNull(role, "Role cannot be empty");
        requireNonNull(user, "User cannot be empty");

        if(users.get(user.getName())==null)
            throw new IllegalArgumentException("User Doesn't Exist");

        List<Role> existingRoles = user.getRoles();
        System.out.println("List of Roles Before Removing: " + existingRoles);
        Iterator<Role> iterator = existingRoles.iterator();
        boolean roleExists = false;
        while (iterator.hasNext()){
            Role current = iterator.next();
            if(current == role){
                System.out.println("Removing the Role " + role);
                roleExists = true;
                iterator.remove();
            }
        }

        if(!roleExists)
            throw new IllegalArgumentException("Role Doesn't Exist");
        System.out.println("List of Permissions After Removing: " + existingRoles);
    }

    /**
     * Check if the user has access to a resource and action
     * @param user
     * @param resource
     * @param action
     * @return
     */
    @Override
    public boolean hasAccess(User user, Resource resource, ActionType action) {
        requireExists(user);
        requireExists(resource);

        ResourcePermission permission = new ResourcePermission(resource, action);
        List<Role> userRoles = user.getRoles();

        Iterator<Role> iterator = userRoles.iterator();
        while (iterator.hasNext()){
            Role current = iterator.next();
            if(current.getResourcePermissionList().size() > 0){
                Iterator<ResourcePermission> resourcePermissionIterator = current.getResourcePermissionList().iterator();
                while (resourcePermissionIterator.hasNext()){
                    ResourcePermission resourcePermission = resourcePermissionIterator.next();
                    if(permission.equals(resourcePermission)){
                        System.out.println("User " + user.getName() + " has access to the resourcePermission " + permission);
                        return true;
                    }
                }
            }
        }

        System.out.println("User " + user.getName() + " DOES NOT have to the resourcePermission " + permission);
        return false;
    }

    /**
     * Fetch all the user roles
     * @param user
     * @return
     */
    @Override
    public List<Role> userRoles(User user) {
        requireNonNull(user, "User cannot be empty");
        return user.getRoles();
    }

    /**
     * Check if User exists in the system
     * @param user
     */
    private void requireExists(User user){
        requireNonNull(user, "User cannot be empty");
        if(users.get(user.getName())==null)
            throw new IllegalArgumentException("User Doesn't Exist");
    }

    /**
     * Check if the Resource exists in the system
     * @param resource
     */
    private void requireExists(Resource resource){
        requireNonNull(resource, "Resource cannot be null");
        if(resourceService.findByName(resource.getName())==null)
            throw new IllegalArgumentException("Resource Doesn't Exist");
    }
}