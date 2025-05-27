package com.example.blinddate.controller;

import com.example.blinddate.common.GetStringObjectMap;
import com.example.blinddate.entity.ExpectOtherInformation;
import com.example.blinddate.service.ExpectOtherInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ExpectOtherInformation")
public class ExpectOtherInformationController {
    @Autowired
    private ExpectOtherInformationService expectOtherInformationService;

    @GetMapping("/findAll")
    public Map<String, Object> findAll(){
        List<ExpectOtherInformation> list = expectOtherInformationService.findAll();
        return GetStringObjectMap.getStringObjectMap(list);
    }

    @PostMapping("/add")
    public boolean add(@RequestBody ExpectOtherInformation expectOtherInformation){
        return expectOtherInformationService.add(expectOtherInformation);
    }

    @PostMapping("/mod")
    public boolean mod(@RequestBody ExpectOtherInformation expectOtherInformation){
        return expectOtherInformationService.mod(expectOtherInformation);
    }

    @PostMapping("/addOrMod")
    public boolean addOrMod(@RequestBody ExpectOtherInformation expectOtherInformation){
        return expectOtherInformationService.addOrMod(expectOtherInformation);
    }

    @GetMapping("/delete")
    public boolean delete(@RequestParam Integer id){
        return expectOtherInformationService.delete(id);
    }

    @GetMapping("/findOrderData")
    public Map<String, Object> findOrderData(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        List<ExpectOtherInformation> list = expectOtherInformationService.findOrderData(pageNum, pageSize);
        return GetStringObjectMap.getStringObjectMap(list);
    }

    @GetMapping("/likeFindAll")
    public Map<String, Object> likeFindAll(@RequestParam String age, @RequestParam String household_registration,
                                           @RequestParam Integer pageNum, @RequestParam Integer pageSize){
        List<ExpectOtherInformation> list = expectOtherInformationService.likeFindAll(age, household_registration, pageNum, pageSize);
        return GetStringObjectMap.getStringObjectMap(list);
    }
}
