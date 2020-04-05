package sh.locus.accessmanagement.service.memoryImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sh.locus.accessmanagement.model.ResourcePermission;
import sh.locus.accessmanagement.model.Role;
import sh.locus.accessmanagement.model.User;
import sh.locus.accessmanagement.service.UserService;

import java.util.*;

import static java.util.Objects.requireNonNull;

@Slf4j
@Service
public class MemoryUserService implements UserService {

    private final Map<String, User> users = new HashMap<>();

    @Override
    public void add(User user) {
        requireNonNull(user, "User Cannot be Empty");
        if(users.containsKey(user.getName()))
            throw new IllegalArgumentException("User Already Exists");
        users.put(user.getName(), user);
        log.info("User Created: " + user);
    }

    @Override
    public void delete(User user) {
        requireNonNull(users, "User Cannot be Empty");
        if(users.isEmpty())
            throw new NullPointerException("No more users to delete");
        users.remove(user.getName());
    }

    @Override
    public User findByName(String name) {
        requireNonNull(name, "Name Cannot be Empty");
        return users.get(name);
    }

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

    @Override
    public boolean hasAccess(User user, ResourcePermission permission) {
        return false;
    }
}
