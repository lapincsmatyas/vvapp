package vv.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vv.dto.SeniorDTO;
import vv.helper.mapper.SeniorMapper;
import vv.model.Senior;
import vv.repository.SeniorRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/senior")
public class SeniorResource {

    @Autowired
    SeniorRepository seniorRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<SeniorDTO> getAllSeniors(){
        List<Senior> seniors = seniorRepository.findAll();
        return seniors.stream().map(SeniorMapper.INSTANCE::seniorToSeniorDto).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SeniorDTO getSeniorById(@PathVariable("id") long id){
        Senior senior = seniorRepository.findById(id).orElse(null);
        if(senior != null){
            return SeniorMapper.INSTANCE.seniorToSeniorDto(senior);
        }
        else return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public SeniorDTO addSenior(@RequestBody SeniorDTO seniorDTO){
        Senior senior = SeniorMapper.INSTANCE.seniorDtoToSenior(seniorDTO);
        seniorRepository.save(senior);
        return SeniorMapper.INSTANCE.seniorToSeniorDto(senior);
    }
}
