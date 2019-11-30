package vv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vv.model.Senior;
import vv.repository.SeniorRepository;

import java.util.List;

@Service
public class SeniorService {
    @Autowired
    SeniorRepository seniorRepository;

    public List<Senior> getAllSeniors(){
        return seniorRepository.findAll();
    }

    public Senior getSeniorById(long id){
        return seniorRepository.findById(id).orElse(null);
    }

    public Senior saveSenior(Senior senior) {
        return seniorRepository.save(senior);
    }

    public Senior getSeniorByAuthSchId(String authSchId) {
        return seniorRepository.findByAuthSchId(authSchId);
    }

    public Senior getSeniorByEmail(String email){
        return seniorRepository.findByEmail(email);
    }
}
