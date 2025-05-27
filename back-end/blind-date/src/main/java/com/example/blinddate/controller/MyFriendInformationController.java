package com.example.blinddate.controller;

import com.example.blinddate.common.GetStringObjectMap;
import com.example.blinddate.entity.MyFriendInformation;
import com.example.blinddate.service.MyFriendInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/MyFriendInformation")
public class MyFriendInformationController {
    @Autowired
    private MyFriendInformationService myFriendInformationService;

    @GetMapping("/findAll")
    public Map<String, Object> findAll(){
        List<MyFriendInformation> list = myFriendInformationService.findAll();
        return GetStringObjectMap.getStringObjectMap(list);
    }

    @PostMapping("/add")
    public boolean add(@RequestBody MyFriendInformation myFriendInformation){
        return myFriendInformationService.add(myFriendInformation);
    }

    @PostMapping("/mod")
    public boolean mod(@RequestBody MyFriendInformation myFriendInformation){
        return myFriendInformationService.mod(myFriendInformation);
    }

    @PostMapping("/addOrMod")
    public boolean addOrMod(@RequestBody MyFriendInformation myFriendInformation){
        return myFriendInformationService.addOrMod(myFriendInformation);
    }

    @GetMapping("/delete")
    public boolean delete(@RequestParam Integer id){
        return myFriendInformationService.delete(id);
    }

    @GetMapping("/findOrderData")
    public Map<String, Object> findOrderData(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        List<MyFriendInformation> list = myFriendInformationService.findOrderData(pageNum, pageSize);
        return GetStringObjectMap.getStringObjectMap(list);
    }

    @GetMapping("/likeFindAll")
    public Map<String, Object> likeFindAll(@RequestParam String friend_nick_name, @RequestParam Integer pageNum, @RequestParam Integer pageSize){
        List<MyFriendInformation> list = myFriendInformationService.likeFindAll(friend_nick_name, pageNum, pageSize);
        return GetStringObjectMap.getStringObjectMap(list);
    }
}
