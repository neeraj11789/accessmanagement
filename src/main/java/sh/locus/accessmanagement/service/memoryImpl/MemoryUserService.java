package sh.locus.accessmanagement.service.memoryImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sh.locus.accessmanagement.model.Role;
import sh.locus.accessmanagement.model.User;
import sh.locus.accessmanagement.service.UserService;

import java.util.HashMap;
import java.util.Map;

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

    }

    @Override
    public void removeRole(User user, Role role) {

    }
}
