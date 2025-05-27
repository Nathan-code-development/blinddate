package com.example.blinddate.service;

import com.example.blinddate.entity.Login;
import com.example.blinddate.mapper.LoginMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    @Resource
    LoginMapper loginMapper;

    public List<Login> findAll(){
        return loginMapper.findAll();
    }

    public boolean add(Login login){
        return loginMapper.add(login);
    }

    public boolean mod(Login login){
        return loginMapper.mod(login);
    }

    public boolean addOrMod(Login login){
        return loginMapper.addOrMod(login);
    }

    public boolean delete(Integer id){
        return loginMapper.delete(id);
    }

    public List<Login> findOrderData(Integer pageNum, Integer pageSize){
        int currentPageNum = (pageNum - 1) * pageSize;
        return loginMapper.findOrderData(currentPageNum, pageSize);
    }

    public List<Login> likeFindAll(String account, Integer pageNum, Integer pageSize){
        int currentPageNum = (pageNum - 1) * pageSize;
        return loginMapper.likeFindAll(account, currentPageNum, pageSize);
    }
}
