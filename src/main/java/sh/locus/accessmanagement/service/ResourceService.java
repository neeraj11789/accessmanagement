package sh.locus.accessmanagement.service;

import sh.locus.accessmanagement.model.Resource;

public interface ResourceService {
    void addResource(Resource resource);

    void deleteResource(Resource resource);

    Resource findByName(String name);
}