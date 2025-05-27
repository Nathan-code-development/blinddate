package com.example.blinddate.service;

import com.example.blinddate.entity.ChatContent;
import com.example.blinddate.mapper.ChatContentMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatContentService {
    @Resource
    ChatContentMapper chatContentMapper;

    public List<ChatContent> findAll(){
        return chatContentMapper.findAll();
    }

    public boolean add(ChatContent chatContent){
        return chatContentMapper.add(chatContent);
    }

    public boolean mod(ChatContent chatContent){
        return chatContentMapper.mod(chatContent);
    }

    public boolean addOrMod(ChatContent chatContent){
        return chatContentMapper.addOrMod(chatContent);
    }

    public boolean delete(Integer id){
        return chatContentMapper.delete(id);
    }

    public List<ChatContent> findOrderData(Integer pageNum, Integer pageSize){
        int currentPageNum = (pageNum - 1) * pageSize;
        return chatContentMapper.findOrderData(currentPageNum, pageSize);
    }

    public List<ChatContent> likeFindAll(String my_nick_name, String friend_nick_name, Integer pageNum, Integer pageSize){
        int currentPageNum = (pageNum - 1) * pageSize;
        return chatContentMapper.likeFindAll(my_nick_name, friend_nick_name, currentPageNum, pageSize);
    }
}
