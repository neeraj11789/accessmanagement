package sh.locus.accessmanagement.service;

import sh.locus.accessmanagement.model.ResourcePermission;
import sh.locus.accessmanagement.model.Role;
import sh.locus.accessmanagement.model.User;

public interface UserService {

    void add(User user);

    void delete(User user);

    User findByName(String name);

    void addRole(User user, Role role);

    void removeRole(User user, Role role);

    boolean hasAccess(User user, ResourcePermission permission);
}