package com.example.blinddate.mapper;

import com.example.blinddate.entity.Login;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LoginMapper {
    List<Login> findAll();
    boolean add(Login login);
    boolean mod(Login login);
    boolean addOrMod(Login login);
    boolean delete(Integer id);
    List<Login> findOrderData(Integer pageNum, Integer pageSize);
    List<Login> likeFindAll(String account, Integer pageNum, Integer pageSize);
}
