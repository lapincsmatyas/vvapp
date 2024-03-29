package vv.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vv.dto.SeniorGroupDTO;
import vv.dto.UserRoleDTO;
import vv.helper.mapper.SeniorGroupMapper;
import vv.helper.mapper.UserRoleMapper;
import vv.model.SeniorGroup;
import vv.model.UserRole;
import vv.service.SeniorGroupService;
import vv.service.UserRoleService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://152.66.178.92:3000", allowCredentials = "true")
@RestController
@RequestMapping("/user-role")
public class UserRoleResource {

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    UserRoleMapper userRoleMapper;

    @GetMapping
    public List<UserRoleDTO> getAllUserRoles(){
        List<UserRole> roles = userRoleService.getAllUserRoles();
        return roles.stream().map(userRoleMapper::userRoleToUserRoleDTO).collect(Collectors.toList());
    }
}
