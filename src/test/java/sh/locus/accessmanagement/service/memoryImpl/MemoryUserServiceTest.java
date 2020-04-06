package sh.locus.accessmanagement.service.memoryImpl;

import com.sun.tools.javac.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.locus.accessmanagement.model.*;
import sh.locus.accessmanagement.service.UserService;

import static org.junit.Assert.*;

class MemoryUserServiceTest {

    /**
     * @NOTE: Adding only few test cases here for time constraint
     */
    private UserService service;

    // Creating resources
    private final Resource PLATFORM_TEAM_REPO = new Resource("platform-team-repo");
    private final Resource CORE_TEAM_REPO = new Resource("content-team-repo");
    private final Resource UC_TEAM_REPO = new Resource("uc-team-repo");

    // Adding Resource Permissions
    private final ResourcePermission PLATFORM_TEAM_REPO_READ = new ResourcePermission(PLATFORM_TEAM_REPO, ActionType.READ);
    private final ResourcePermission PLATFORM_TEAM_REPO_WRITE = new ResourcePermission(PLATFORM_TEAM_REPO, ActionType.WRITE);
    private final ResourcePermission PLATFORM_TEAM_REPO_DELETE = new ResourcePermission(PLATFORM_TEAM_REPO, ActionType.DELETE);
    private final ResourcePermission CORE_TEAM_REPO_READ = new ResourcePermission(CORE_TEAM_REPO, ActionType.READ);
    private final ResourcePermission CORE_TEAM_REPO_WRITE = new ResourcePermission(CORE_TEAM_REPO, ActionType.WRITE);
    private final ResourcePermission CORE_TEAM_REPO_DELETE = new ResourcePermission(CORE_TEAM_REPO, ActionType.DELETE);
    private final ResourcePermission UC_TEAM_REPO_READ = new ResourcePermission(UC_TEAM_REPO, ActionType.READ);
    private final ResourcePermission UC_TEAM_REPO_WRITE = new ResourcePermission(UC_TEAM_REPO, ActionType.WRITE);
    private final ResourcePermission UC_TEAM_REPO_DELETE = new ResourcePermission(UC_TEAM_REPO, ActionType.DELETE);

    // Role with permissions
    private final Role ADMIN = new Role("Admin", List.of(PLATFORM_TEAM_REPO_DELETE, PLATFORM_TEAM_REPO_READ,
            PLATFORM_TEAM_REPO_WRITE, CORE_TEAM_REPO_DELETE, CORE_TEAM_REPO_READ, CORE_TEAM_REPO_WRITE, UC_TEAM_REPO_DELETE,
            UC_TEAM_REPO_READ, UC_TEAM_REPO_WRITE));

    // Role with Only read permission
    private final Role MANAGER = new Role("Manager", List.of(PLATFORM_TEAM_REPO_READ, UC_TEAM_REPO_READ, CORE_TEAM_REPO_READ));

    // Role with Only Write permission
    private final Role DEVELOPER = new Role("Developer", List.of(PLATFORM_TEAM_REPO_WRITE, CORE_TEAM_REPO_WRITE,
            UC_TEAM_REPO_WRITE));

    // Adding users
    private final User alex = new User("alex");
    private final User bob = new User("bob");
    private final User charlie = new User("charlie");

    @BeforeEach
    void setUp() {
        service = new MemoryUserService();
    }

    @Test
    void should_pass_when_new_resource_is_created(){
        String userName = "neeraj";
        assertNull( userName + " should not exist", service.findByName(userName));
        User user = new User(userName);
        service.add(user);
        assertEquals("Repo should be created now", user, service.findByName(userName));
    }

    @Test
    void should_pass_when_deleting_exising_group(){
        String userName = "neeraj";
        User user = new User(userName);
        service.add(user);

        service.delete(user);
        assertNull( userName + " should not exist", service.findByName(userName));
    }

    @Test
    void should_pass_add_role_if_user_exists_and_new_role(){
        service.add(alex);
        service.addRole(alex, ADMIN);
        java.util.List<Role> userRoles = service.findByName(alex.getName()).getRoles();
        assertEquals(userRoles.get(0), ADMIN);
    }

    @Test
    void should_pass_add_multiple_roles_if_user_exists_and_new_role(){
        service.add(alex);
        service.addRole(alex, ADMIN);
        service.addRole(alex, DEVELOPER);
        java.util.List<Role> userRoles = service.findByName(alex.getName()).getRoles();
        assertEquals(userRoles.size(), 2);
    }

    @Test
    void should_fail_add_role_if_user_exists_and_role_exists_as_well(){
        service.add(alex);
        service.addRole(alex, ADMIN);
        try {
            service.addRole(alex, ADMIN);
            fail();
        }catch (Exception e){
            assertTrue(e instanceof IllegalArgumentException);
            assertEquals(e.getMessage(), "Role Already Exists");
        }
    }

    @Test
    void should_pass_remove_role_if_user_exists_and_role_exists(){
        service.add(alex);
        service.addRole(alex, ADMIN);
        service.removeRole(alex, ADMIN);
        assertEquals(service.findByName(alex.getName()).getRoles().size(), 0);
    }

    @Test
    void should_fail_remove_role_if_user_exists_and_has_none_role(){
        service.add(alex);
        try {
            service.removeRole(alex, ADMIN);
            fail();
        }catch (Exception e){
            assertTrue(e instanceof NullPointerException);
        }
    }

    @Test
    void should_fail_remove_role_if_user_exists_and_role_doesnot_exists(){
        service.add(alex);
        service.addRole(alex, DEVELOPER);
        try {
            service.removeRole(alex, ADMIN);
            fail();
        }catch (Exception e){
            assertTrue(e instanceof IllegalArgumentException);
            assertEquals(e.getMessage(), "Role Doesn't Exist");
        }
    }

    @Test
    void should_pass_when_accessing_permitted_resource_from_user(){
        service.add(alex);
        service.addRole(alex, ADMIN);

        assertTrue(service.hasAccess(alex, PLATFORM_TEAM_REPO_READ));
    }

    @Test
    void should_fail_when_accessing_unauthorized_resource_from_user(){
        service.add(bob);
        service.addRole(bob, DEVELOPER);

        assertFalse(service.hasAccess(bob, PLATFORM_TEAM_REPO_DELETE));
    }

    @Test
    void should_fail_when_user_has_multiple_roles_still_not_the_permission_for_resource(){
        service.add(charlie);
        service.addRole(charlie, DEVELOPER);
        service.addRole(charlie, MANAGER);

        assertFalse(service.hasAccess(charlie, UC_TEAM_REPO_DELETE));
    }
}