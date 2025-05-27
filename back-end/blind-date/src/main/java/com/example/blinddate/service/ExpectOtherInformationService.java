package com.example.blinddate.service;

import com.example.blinddate.entity.ExpectOtherInformation;
import com.example.blinddate.mapper.ExpectOtherInformationMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpectOtherInformationService {
    @Resource
    ExpectOtherInformationMapper expectOtherInformationMapper;

    public List<ExpectOtherInformation> findAll(){
        return expectOtherInformationMapper.findAll();
    }

    public boolean add(ExpectOtherInformation expectOtherInformation){
        return expectOtherInformationMapper.add(expectOtherInformation);
    }

    public boolean mod(ExpectOtherInformation expectOtherInformation){
        return expectOtherInformationMapper.mod(expectOtherInformation);
    }

    public boolean addOrMod(ExpectOtherInformation expectOtherInformation){
        return expectOtherInformationMapper.addOrMod(expectOtherInformation);
    }

    public boolean delete(Integer id){
        return expectOtherInformationMapper.delete(id);
    }

    public List<ExpectOtherInformation> findOrderData(Integer pageNum, Integer pageSize){
        int currentPageNum = (pageNum - 1) * pageSize;
        return expectOtherInformationMapper.findOrderData(currentPageNum, pageSize);
    }

    public List<ExpectOtherInformation> likeFindAll(String age, String household_registration, Integer pageNum, Integer pageSize){
        int currentPageNum = (pageNum - 1) * pageSize;
        return expectOtherInformationMapper.likeFindAll(age, household_registration, currentPageNum, pageSize);
    }
}
