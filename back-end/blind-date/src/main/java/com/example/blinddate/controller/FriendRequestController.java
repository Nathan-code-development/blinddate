package com.example.blinddate.controller;

import com.example.blinddate.common.GetStringObjectMap;
import com.example.blinddate.entity.FriendRequest;
import com.example.blinddate.service.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/FriendRequest")
public class FriendRequestController {
    @Autowired
    private FriendRequestService friendRequestService;

    @GetMapping("/findAll")
    public Map<String, Object> findAll(){
        List<FriendRequest> list = friendRequestService.findAll();
        return GetStringObjectMap.getStringObjectMap(list);
    }

    @PostMapping("/add")
    public boolean add(@RequestBody FriendRequest friendRequest){
        return friendRequestService.add(friendRequest);
    }

    @GetMapping("/addStatus")
    public boolean addStatus(@RequestParam Integer id, @RequestParam String status){
        return friendRequestService.addStatus(id, status);
    }

    @PostMapping("/mod")
    public boolean mod(@RequestBody FriendRequest friendRequest){
        return friendRequestService.mod(friendRequest);
    }

    @GetMapping("/modStatus")
    public boolean modStatus(@RequestParam Integer id, @RequestParam String status){
        return friendRequestService.modStatus(id, status);
    }

    @PostMapping("/addOrMod")
    public boolean addOrMod(@RequestBody FriendRequest friendRequest){
        return friendRequestService.addOrMod(friendRequest);
    }

    @GetMapping("/addOrModStatus")
    public boolean addOrModStatus(@RequestParam Integer id, @RequestParam String status){
        return friendRequestService.addOrModStatus(id, status);
    }

    @GetMapping("/delete")
    public boolean delete(@RequestParam Integer id){
        return friendRequestService.delete(id);
    }

    @GetMapping("/getMyFriendInformationTableID")
    public Integer getMyFriendInformationTableID(){
        return friendRequestService.getMyFriendInformationTableID();
    }

    @GetMapping("/findOrderData")
    public Map<String, Object> findOrderData(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        List<FriendRequest> list = friendRequestService.findOrderData(pageNum, pageSize);
        return GetStringObjectMap.getStringObjectMap(list);
    }

    @GetMapping("/likeFindAll")
    public Map<String, Object> likeFindAll(@RequestParam String request_people_id, @RequestParam String receive_people_id, @RequestParam Integer pageNum, @RequestParam Integer pageSize){
        List<FriendRequest> list = friendRequestService.likeFindAll(request_people_id, receive_people_id, pageNum, pageSize);
        return GetStringObjectMap.getStringObjectMap(list);
    }
}
