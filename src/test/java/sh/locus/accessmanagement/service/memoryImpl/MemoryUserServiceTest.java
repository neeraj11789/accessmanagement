package sh.locus.accessmanagement.service.memoryImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.locus.accessmanagement.model.Resource;
import sh.locus.accessmanagement.model.User;
import sh.locus.accessmanagement.service.UserService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

class MemoryUserServiceTest {

    /**
     * @NOTE: Adding only few test cases here for time constraint
     */
    private UserService service;

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
}