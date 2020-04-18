package sh.locus.accessmanagement.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sh.locus.accessmanagement.model.Resource;
import sh.locus.accessmanagement.request.CreateResourceRequest;
import sh.locus.accessmanagement.response.ResponseDTO;
import sh.locus.accessmanagement.service.ResourceService;
import sh.locus.accessmanagement.service.RoleService;
import sh.locus.accessmanagement.service.UserService;

@RequiredArgsConstructor
@Slf4j
@RestController
public class DefaultController {

    @NonNull
    UserService userService;

    @NonNull
    RoleService roleService;

    @NonNull
    ResourceService resourceService;

    @RequestMapping(value = "/resource/create", method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO> createResource(@RequestBody CreateResourceRequest request){
        ResponseDTO responseDTO = new ResponseDTO();
        Resource resource = new Resource(request.getName());
        resourceService.addResource(resource);
        responseDTO.setPayload(resource);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/resources/all", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> listResources(){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setPayload(resourceService.getAll());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
