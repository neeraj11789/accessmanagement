package sh.locus.accessmanagement.service.memoryImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import sh.locus.accessmanagement.model.ActionType;
import sh.locus.accessmanagement.model.Resource;
import sh.locus.accessmanagement.model.ResourcePermission;
import sh.locus.accessmanagement.model.Role;
import sh.locus.accessmanagement.service.RoleService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;


class MemoryRoleServiceTest {
    private RoleService service;

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

    private final Role ADMIN = new Role("Admin");
    private final Role MANAGER = new Role("Manager");
    private final Role DEVELOPER = new Role("Developer");

    @BeforeEach
    void setUp() {
        service = new MemoryRoleService();
    }

    @Order(1)
    @Test
    void should_pass_add_role_if_new_role_is_passed(){
        assertNull( DEVELOPER.getName() + " should not exist", service.findByName(DEVELOPER.getName()));
        service.addRole(DEVELOPER);
        assertEquals("Role should be created now", DEVELOPER, service.findByName(DEVELOPER.getName()));
    }

    @Order(2)
    @Test
    void should_fail_add_role_if_new_role_already_exists(){
        service.addRole(DEVELOPER);
        try {
            service.addRole(DEVELOPER);
            fail("Failed as could not catch Exception");
        }catch (Exception e){
            assertTrue(e instanceof IllegalArgumentException);
            assertEquals(e.getMessage(), "Role Already Exists");
        }
    }

    @Order(3)
    @Test
    void should_pass_add_permission_to_new_role(){
        service.addRole(ADMIN);
        service.addPermission(ADMIN, PLATFORM_TEAM_REPO_READ);
        service.addPermission(ADMIN, PLATFORM_TEAM_REPO_WRITE);
        service.addPermission(ADMIN, UC_TEAM_REPO_READ);
        service.addPermission(ADMIN, UC_TEAM_REPO_WRITE);

        assertEquals(service.findByName(ADMIN.getName()).getResourcePermissionList().size(), 4);
    }

    @Order(4)
    @Test
    void should_fail_add_permission_if_permission_already_exists(){
        service.addRole(DEVELOPER);
        service.addPermission(DEVELOPER, PLATFORM_TEAM_REPO_READ);
        try {
            service.addPermission(DEVELOPER, PLATFORM_TEAM_REPO_READ);
            fail("Failed as could not catch Exception");
        }catch (Exception e){
            assertTrue(e instanceof IllegalArgumentException);
            assertEquals(e.getMessage(), "Permission Already Exists");
        }
    }

    @Order(5)
    @Test
    void should_fail_add_permission_if_role_doesnot_exist(){
        try {
            service.addPermission(DEVELOPER, PLATFORM_TEAM_REPO_READ);
            fail("Failed as could not catch Exception");
        }catch (Exception e){
            assertTrue(e instanceof IllegalArgumentException);
            assertEquals(e.getMessage(), "Role Doesn't Exist");
        }
    }

    @Order(6)
    @Test
    void should_pass_remove_permission_if_role_has_that_permission(){
        service.addRole(MANAGER);
        service.addPermission(MANAGER, PLATFORM_TEAM_REPO_READ);
        service.addPermission(MANAGER, PLATFORM_TEAM_REPO_WRITE);
        service.addPermission(MANAGER, UC_TEAM_REPO_READ);
        service.addPermission(MANAGER, UC_TEAM_REPO_WRITE);

        assertEquals(service.findByName(MANAGER.getName()).getResourcePermissionList().size(), 4);
        service.revokePermission(MANAGER, UC_TEAM_REPO_WRITE);
        assertEquals(service.findByName(MANAGER.getName()).getResourcePermissionList().size(), 3);
    }

    @Order(7)
    @Test
    void should_fail_remove_permission_if_role_doesnot_have_that_permission(){
        service.addRole(DEVELOPER);
        service.addPermission(DEVELOPER, PLATFORM_TEAM_REPO_READ);
        service.addPermission(DEVELOPER, PLATFORM_TEAM_REPO_WRITE);
        service.addPermission(DEVELOPER, UC_TEAM_REPO_READ);
        service.addPermission(DEVELOPER, UC_TEAM_REPO_WRITE);
        try {
            service.revokePermission(DEVELOPER, CORE_TEAM_REPO_WRITE);
            fail("Failed as could not catch Exception");
        }catch (Exception e){
            assertTrue(e instanceof IllegalArgumentException);
            assertEquals(e.getMessage(), "Permission Doesn't Exist");
        }
    }
}