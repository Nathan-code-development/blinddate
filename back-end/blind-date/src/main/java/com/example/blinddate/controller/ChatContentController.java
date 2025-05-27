package com.example.blinddate.controller;

import com.example.blinddate.common.GetStringObjectMap;
import com.example.blinddate.entity.ChatContent;
import com.example.blinddate.service.ChatContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ChatContent")
public class ChatContentController {
    @Autowired
    private ChatContentService chatContentService;

    @GetMapping("/findAll")
    public Map<String, Object> findAll(){
        List<ChatContent> list = chatContentService.findAll();
        return GetStringObjectMap.getStringObjectMap(list);
    }

    @PostMapping("/add")
    public boolean add(@RequestBody ChatContent myFriendInformation){
        return chatContentService.add(myFriendInformation);
    }

    @PostMapping("/mod")
    public boolean mod(@RequestBody ChatContent myFriendInformation){
        return chatContentService.mod(myFriendInformation);
    }

    @PostMapping("/addOrMod")
    public boolean addOrMod(@RequestBody ChatContent myFriendInformation){
        return chatContentService.addOrMod(myFriendInformation);
    }

    @GetMapping("/delete")
    public boolean delete(@RequestParam Integer id){
        return chatContentService.delete(id);
    }

    @GetMapping("/findOrderData")
    public Map<String, Object> findOrderData(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        List<ChatContent> list = chatContentService.findOrderData(pageNum, pageSize);
        return GetStringObjectMap.getStringObjectMap(list);
    }

    @GetMapping("/likeFindAll")
    public Map<String, Object> likeFindAll(@RequestParam String my_nick_name, @RequestParam String friend_nick_name, @RequestParam Integer pageNum, @RequestParam Integer pageSize){
        List<ChatContent> list = chatContentService.likeFindAll(my_nick_name, friend_nick_name, pageNum, pageSize);
        return GetStringObjectMap.getStringObjectMap(list);
    }
}
