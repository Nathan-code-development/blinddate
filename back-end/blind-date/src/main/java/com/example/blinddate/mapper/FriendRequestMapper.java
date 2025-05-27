package com.example.blinddate.mapper;

import com.example.blinddate.entity.FriendRequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FriendRequestMapper {
    List<FriendRequest> findAll();
    boolean add(FriendRequest friendRequest);
    boolean addStatus(Integer id, String status);
    boolean mod(FriendRequest friendRequest);
    boolean modStatus(Integer id, String status);
    boolean addOrMod(FriendRequest friendRequest);
    boolean addOrModStatus(Integer id, String status);
    boolean delete(Integer id);
    Integer getMyFriendInformationTableID();
    List<FriendRequest> findOrderData(Integer pageNum, Integer pageSize);
    List<FriendRequest> likeFindAll(String request_people_id,String receive_people_id, Integer pageNum, Integer pageSize);
}
