package com.example.blinddate.mapper;

import com.example.blinddate.entity.ExpectOtherInformation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ExpectOtherInformationMapper {
    List<ExpectOtherInformation> findAll();
    boolean add(ExpectOtherInformation expectOtherInformation);
    boolean mod(ExpectOtherInformation expectOtherInformation);
    boolean addOrMod(ExpectOtherInformation expectOtherInformation);
    boolean delete(Integer id);
    List<ExpectOtherInformation> findOrderData(Integer pageNum, Integer pageSize);
    List<ExpectOtherInformation> likeFindAll(String age, String household_registration, Integer pageNum, Integer pageSize);
}
