package sh.locus.accessmanagement.service.memoryImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sh.locus.accessmanagement.model.Resource;
import sh.locus.accessmanagement.service.ResourceService;

import java.util.*;

import static java.util.Objects.requireNonNull;

@Slf4j
@Service
public class MemoryResourceService implements ResourceService {

    private static final Map<String, Resource> resources = new HashMap<>();

    @Override
    public void addResource(Resource resource) {
        requireNonNull(resource, "Resource Cannot be Empty");
        if(resources.containsKey(resource.getName()))
            throw new IllegalArgumentException("Resource Already Exists");
        resources.put(resource.getName(), resource);
        log.info("Resource Created: " + resource);
    }

    @Override
    public void deleteResource(Resource resource) {
        requireNonNull(resource, "Resource Cannot be Empty");
        if(resources.isEmpty())
            throw new NullPointerException("No more resources to delete");
        resources.remove(resource.getName());
    }

    @Override
    public Resource findByName(String name) {
        requireNonNull(name, "Name Cannot be Empty");
        return resources.get(name);
    }

    @Override
    public List<Resource> getAll(){
        List<Resource> res = new ArrayList<>();
        Iterator<Map.Entry<String, Resource>> iterator = resources.entrySet().iterator();
        while (iterator.hasNext())
            res.add(iterator.next().getValue());
        return res;
    }
}
