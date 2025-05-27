package com.example.blinddate.mapper;

import com.example.blinddate.entity.ChatContent;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ChatContentMapper {
    List<ChatContent> findAll();
    boolean add(ChatContent chatContent);
    boolean mod(ChatContent chatContent);
    boolean addOrMod(ChatContent chatContent);
    boolean delete(Integer id);
    List<ChatContent> findOrderData(Integer pageNum, Integer pageSize);
    List<ChatContent> likeFindAll(String my_nick_name, String friend_nick_name, Integer pageNum, Integer pageSize);
}
