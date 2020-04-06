package sh.locus.accessmanagement.service.memoryImpl;

import com.sun.tools.javac.util.List;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.locus.accessmanagement.model.*;
import sh.locus.accessmanagement.service.UserService;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

class MemoryUserServiceTest {

    /**
     * @NOTE: Adding only few test cases here for time constraint
     */
    private UserService service;

    private final Resource PLATFORM_TEAM_REPO = new Resource("platform-team-repo");
    private final Resource CORE_TEAM_REPO = new Resource("content-team-repo");
    private final Resource UC_TEAM_REPO = new Resource("uc-team-repo");

    private final ResourcePermission PLATFORM_TEAM_REPO_READ = new ResourcePermission(PLATFORM_TEAM_REPO, ActionType.READ);
    private final ResourcePermission PLATFORM_TEAM_REPO_WRITE = new ResourcePermission(PLATFORM_TEAM_REPO, ActionType.WRITE);
    private final ResourcePermission PLATFORM_TEAM_REPO_DELETE = new ResourcePermission(PLATFORM_TEAM_REPO, ActionType.DELETE);

    private final ResourcePermission CORE_TEAM_REPO_READ = new ResourcePermission(CORE_TEAM_REPO, ActionType.READ);
    private final ResourcePermission CORE_TEAM_REPO_WRITE = new ResourcePermission(CORE_TEAM_REPO, ActionType.WRITE);
    private final ResourcePermission CORE_TEAM_REPO_DELETE = new ResourcePermission(CORE_TEAM_REPO, ActionType.DELETE);

    private final ResourcePermission UC_TEAM_REPO_READ = new ResourcePermission(UC_TEAM_REPO, ActionType.READ);
    private final ResourcePermission UC_TEAM_REPO_WRITE = new ResourcePermission(UC_TEAM_REPO, ActionType.WRITE);
    private final ResourcePermission UC_TEAM_REPO_DELETE = new ResourcePermission(UC_TEAM_REPO, ActionType.DELETE);

    private final Role ADMIN = new Role("Admin", List.of(PLATFORM_TEAM_REPO_DELETE, PLATFORM_TEAM_REPO_READ,
            PLATFORM_TEAM_REPO_WRITE, CORE_TEAM_REPO_DELETE, CORE_TEAM_REPO_READ, CORE_TEAM_REPO_WRITE, UC_TEAM_REPO_DELETE,
            UC_TEAM_REPO_READ, UC_TEAM_REPO_WRITE));

    private final Role MANAGER = new Role("Manager", List.of(PLATFORM_TEAM_REPO_DELETE, PLATFORM_TEAM_REPO_READ,
            UC_TEAM_REPO_DELETE, UC_TEAM_REPO_READ, CORE_TEAM_REPO_DELETE, CORE_TEAM_REPO_READ));

    private final Role DEVELOPER = new Role("Developer", List.of(PLATFORM_TEAM_REPO_WRITE, CORE_TEAM_REPO_WRITE,
            UC_TEAM_REPO_WRITE));

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

    }
}