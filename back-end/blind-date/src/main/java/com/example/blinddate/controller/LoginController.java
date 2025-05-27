package com.example.blinddate.controller;

import com.example.blinddate.common.GetStringObjectMap;
import com.example.blinddate.entity.Login;
import com.example.blinddate.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping("/findAll")
    public Map<String, Object> findAll(){
        List<Login> list = loginService.findAll();
        return GetStringObjectMap.getStringObjectMap(list);
    }

    @PostMapping("/add")
    public boolean add(@RequestBody Login login){
        return loginService.add(login);
    }

    @PostMapping("/mod")
    public boolean mod(@RequestBody Login login){
        return loginService.mod(login);
    }

    @PostMapping("/addOrMod")
    public boolean addOrMod(@RequestBody Login login){
        return loginService.addOrMod(login);
    }

    @GetMapping("/delete")
    public boolean delete(@RequestParam Integer id){
        return loginService.delete(id);
    }

    @GetMapping("/findOrderData")
    public Map<String, Object> findOrderData(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        List<Login> list = loginService.findOrderData(pageNum, pageSize);
        return GetStringObjectMap.getStringObjectMap(list);
    }

    @GetMapping("/likeFindAll")
    public Map<String, Object> likeFindAll(@RequestParam String account, @RequestParam Integer pageNum, @RequestParam Integer pageSize){
        List<Login> list = loginService.likeFindAll(account, pageNum, pageSize);
        return GetStringObjectMap.getStringObjectMap(list);
    }
}
