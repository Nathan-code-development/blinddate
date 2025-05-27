package com.example.blinddate.mapper;

import com.example.blinddate.entity.MyFriendInformation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MyFriendInformationMapper {
    List<MyFriendInformation> findAll();
    boolean add(MyFriendInformation myFriendInformation);
    boolean mod(MyFriendInformation myFriendInformation);
    boolean addOrMod(MyFriendInformation myFriendInformation);
    boolean delete(Integer id);
    List<MyFriendInformation> findOrderData(Integer pageNum, Integer pageSize);
    List<MyFriendInformation> likeFindAll(String friend_nick_name, Integer pageNum, Integer pageSize);
}
