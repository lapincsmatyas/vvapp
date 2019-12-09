package vv.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vv.dto.SeniorGroupDTO;
import vv.helper.mapper.SeniorGroupMapper;
import vv.model.SeniorGroup;
import vv.service.SeniorGroupService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://152.66.178.92:3000", allowCredentials = "true")
@RestController
@RequestMapping("/group")
public class SeniorGroupResource {

    @Autowired
    SeniorGroupService groupService;

    @GetMapping
    public List<SeniorGroupDTO> getAllSeniors(){
        List<SeniorGroup> groups = groupService.getAllGroups();
        return groups.stream().map(SeniorGroupMapper.INSTANCE::groupToGroupDto).collect(Collectors.toList());
    }
}
