package sh.locus.accessmanagement.service.memoryImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.locus.accessmanagement.model.Resource;
import sh.locus.accessmanagement.service.ResourceService;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.fail;

class MemoryResourceServiceTest {

    private ResourceService service;

    @BeforeEach
    void setUp() {
        service = new MemoryResourceService();
    }

    @Test
    void should_pass_when_new_resource_is_created(){
        String resourceName = "platform-team-repo";
        assertNull( resourceName + " should not exist", service.findByName(resourceName));
        Resource resource = new Resource(resourceName);
        service.addResource(resource);
        assertEquals("Resource should be created now", resource, service.findByName(resourceName));
    }

    @Test
    void should_throw_exception_when_creating_empty_object(){
        try{
            service.addResource(null);
            fail("Failed as could not catch Exception");
        }catch (Exception e){
            assertTrue(e instanceof NullPointerException);
            assertEquals(e.getMessage(), "Resource Cannot be Empty");
        }
    }

    @Test
    void should_fail_when_creating_duplicate_resource(){
        String resourceName = "platform-team-repo";
        Resource resource = new Resource(resourceName);
        service.addResource(resource);
        try {
            service.addResource(resource);
            fail("Failed as could not catch Exception");
        }catch (Exception e){
            assertTrue(e instanceof IllegalArgumentException);
            assertEquals(e.getMessage(), "Resource Already Exists");
        }
    }

    @Test
    void should_fail_when_trying_to_delete_non_existing_resource(){
        String resourceName = "platform-team-repo";
        assertNull( resourceName + " should not exist", service.findByName(resourceName));

        Resource resource = new Resource(resourceName);
        try {
            service.deleteResource(resource);
            fail("Failed as could not catch Exception");
        }catch (Exception e){
            assertTrue(e instanceof NullPointerException);
            assertEquals(e.getMessage(), "No more resources to delete");
        }

    }

    @Test
    void should_pass_when_deleting_exising_group(){
        String resourceName = "platform-team-repo";
        Resource resource = new Resource(resourceName);
        service.addResource(resource);

        service.deleteResource(resource);
        assertNull( resourceName + " should not exist", service.findByName(resourceName));
    }

    @Test
    void should_throw_excepting_when_passing_null_object(){
        try{
            service.deleteResource(null);
            fail("Failed as could not catch Exception");
        }catch (Exception e){
            assertTrue(e instanceof NullPointerException);
            assertEquals(e.getMessage(), "Resource Cannot be Empty");
        }
    }
}