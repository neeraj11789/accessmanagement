# Access Management

Role Based Access Control:

* Entities are USER, ACTION TYPE, RESOURCE, ROLE
* ACTION TYPE defines the access level(Ex: READ, WRITE, DELETE)
* Access to resources for users are controlled strictly by the role. One user can have multiple roles. Given a user, action type and resource system should be able to tell whether user has access or not.

## Notes regarding the application
* Using SpringBoot Application here just for creating Services. We are currently not using any dependency injection. Have used Lombok extensively.
* Using JUNIT rather than command line application to test the flow
* Added major test cases - for time constraint. More test cases can be added
* Services has been created for User, Role and Resource. 
* **@NOTE: Since we were not having DAO layer, have coupled user and role functionlity in the service. As Enhancement, it could be moved to UserRoleMapping when using DAO**
* All the entities reside under model package.
* Currently, we have provided only InMemory Implementation of the service interface. This could be extended later to DAO Layer.
* Testcases has been created for all services and method name is explainatory of the test case.
* Important methods of interest for our service mentioned below. The test cases for these reside in `MemoryUserServiceTest` 


```
public interface UserService {
    void addRole(User user, Role role);
    void removeRole(User user, Role role);
    boolean hasAccess(User user, ResourcePermission permission);
}
```

## Bug Fix Updates
* Modified the hasAccess method signature to accept the resource and actionType along with the user
* **There was a bug that even if the resouce is deleted, the method might return true** The bug has been fixed by adding a private method which checks that the resouce must exist.
* **Code Modifications**
* * Injected the ResourceService in the MemoryUserService class
  * method signature modified as mentioned
  * OverRide **equals() & hashCode() of ResourcePermission class** since now are creating new instance and we no longer need reference check.
  * method requireExists() added in MemoryUserService class
  * test case added **should_fail_when_accessing_non_existing_resource()**
