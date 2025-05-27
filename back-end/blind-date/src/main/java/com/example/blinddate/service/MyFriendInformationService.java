package com.example.blinddate.service;

import com.example.blinddate.entity.MyFriendInformation;
import com.example.blinddate.mapper.MyFriendInformationMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyFriendInformationService {
    @Resource
    MyFriendInformationMapper myFriendInformationMapper;

    public List<MyFriendInformation> findAll(){
        return myFriendInformationMapper.findAll();
    }

    public boolean add(MyFriendInformation myFriendInformation){
        return myFriendInformationMapper.add(myFriendInformation);
    }

    public boolean mod(MyFriendInformation myFriendInformation){
        return myFriendInformationMapper.mod(myFriendInformation);
    }

    public boolean addOrMod(MyFriendInformation myFriendInformation){
        return myFriendInformationMapper.addOrMod(myFriendInformation);
    }

    public boolean delete(Integer id){
        return myFriendInformationMapper.delete(id);
    }

    public List<MyFriendInformation> findOrderData(Integer pageNum, Integer pageSize){
        int currentPageNum = (pageNum - 1) * pageSize;
        return myFriendInformationMapper.findOrderData(currentPageNum, pageSize);
    }

    public List<MyFriendInformation> likeFindAll(String friend_nick_name, Integer pageNum, Integer pageSize){
        int currentPageNum = (pageNum - 1) * pageSize;
        return myFriendInformationMapper.likeFindAll(friend_nick_name, currentPageNum, pageSize);
    }
}
