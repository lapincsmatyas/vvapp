package vv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vv.model.SeniorGroup;
import vv.repository.SeniorGroupRepository;

import java.util.List;

@Service
public class SeniorGroupService {
    @Autowired
    SeniorGroupRepository seniorGroupRepository;

    public List<SeniorGroup> getAllGroups() {
        return seniorGroupRepository.findAll();
    }
}
