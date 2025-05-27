package com.example.blinddate.service;

import com.example.blinddate.entity.FriendRequest;
import com.example.blinddate.mapper.FriendRequestMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FriendRequestService {
    @Resource
    FriendRequestMapper friendRequestMapper;

    public List<FriendRequest> findAll(){
        return friendRequestMapper.findAll();
    }

    public boolean add(FriendRequest friendRequest){
        return friendRequestMapper.add(friendRequest);
    }

    public boolean addStatus(Integer id, String status){
        return friendRequestMapper.addStatus(id, status);
    }

    public boolean mod(FriendRequest friendRequest){
        return friendRequestMapper.mod(friendRequest);
    }

    public boolean modStatus(Integer id, String status){
        return friendRequestMapper.modStatus(id, status);
    }

    public boolean addOrMod(FriendRequest friendRequest){
        return friendRequestMapper.addOrMod(friendRequest);
    }

    public boolean addOrModStatus(Integer id, String status){
        return friendRequestMapper.addOrModStatus(id, status);
    }

    public boolean delete(Integer id){
        return friendRequestMapper.delete(id);
    }

    public Integer getMyFriendInformationTableID(){
        return friendRequestMapper.getMyFriendInformationTableID();
    }

    public List<FriendRequest> findOrderData(Integer pageNum, Integer pageSize){
        int currentPageNum = (pageNum - 1) * pageSize;
        return friendRequestMapper.findOrderData(currentPageNum, pageSize);
    }

    public List<FriendRequest> likeFindAll(String request_people_id, String receive_people_id, Integer pageNum, Integer pageSize){
        int currentPageNum = (pageNum - 1) * pageSize;
        return friendRequestMapper.likeFindAll(request_people_id, receive_people_id, currentPageNum, pageSize);
    }
}
