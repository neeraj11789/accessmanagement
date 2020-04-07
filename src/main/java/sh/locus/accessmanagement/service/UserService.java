package sh.locus.accessmanagement.service;

import sh.locus.accessmanagement.model.*;

import java.util.List;

public interface UserService {

    void add(User user);

    void delete(User user);

    User findByName(String name);

    void addRole(User user, Role role);

    void removeRole(User user, Role role);

    boolean hasAccess(User user, Resource resource, ActionType action);

    List<Role> userRoles(User user);
}