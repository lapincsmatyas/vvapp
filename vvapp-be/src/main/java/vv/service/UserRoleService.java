package vv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vv.model.SeniorGroup;
import vv.model.UserRole;
import vv.repository.SeniorGroupRepository;
import vv.repository.UserRoleRepository;

import java.util.List;

@Service
public class UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;

    public List<UserRole> getAllUserRoles() {
        return userRoleRepository.findAll();
    }
}
