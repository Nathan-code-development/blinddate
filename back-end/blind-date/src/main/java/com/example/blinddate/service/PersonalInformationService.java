package com.example.blinddate.service;

import com.example.blinddate.entity.PersonalInformation;
import com.example.blinddate.mapper.PersonalInformationMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalInformationService {
    @Resource
    PersonalInformationMapper personalInformationMapper;
    String baseUrl = "http://121.40.144.4:54920/PersonalInformation/download?name=";

    public List<PersonalInformation> findAll(){
        return personalInformationMapper.findAll();
    }

    public List<PersonalInformation> findAllPro(String gender){
        return personalInformationMapper.findAllPro(gender);
    }

    public boolean add(PersonalInformation personalInformation){
        return personalInformationMapper.add(personalInformation);
    }

    public boolean addHeadImage(Integer id, String head_image){
        return personalInformationMapper.addHeadImage(id, baseUrl + head_image);
    }

    public boolean mod(PersonalInformation personalInformation){
        return personalInformationMapper.mod(personalInformation);
    }

    public boolean addOrMod(PersonalInformation personalInformation){
        return personalInformationMapper.addOrMod(personalInformation);
    }

    public boolean delete(Integer id){
        return personalInformationMapper.delete(id);
    }

    public boolean hasGenerateId(Integer id){
        return personalInformationMapper.hasGenerateId(id) != 1;
    }

    public List<PersonalInformation> recommendPeople(String household_registration, String gender){
        return personalInformationMapper.recommendPeople(household_registration, gender);
    }

    public List<PersonalInformation> recommendPeoplePro(String household_registration, String gender, Integer pageNum, Integer pageSize){
        int currentPageNum = (pageNum - 1) * pageSize;
        return personalInformationMapper.recommendPeoplePro(household_registration, gender, currentPageNum, pageSize);
    }
    public boolean addGenerateId(String personal_id){
        return personalInformationMapper.addGenerateId(personal_id);
    }

    public List<PersonalInformation> findOrderData(String gender, Integer pageNum, Integer pageSize){
        int currentPageNum = (pageNum - 1) * pageSize;
        return personalInformationMapper.findOrderData(gender, currentPageNum, pageSize);
    }

    public List<PersonalInformation> likeFindAll(String nick_name, Integer pageNum, Integer pageSize){
        int currentPageNum = (pageNum - 1) * pageSize;
        return personalInformationMapper.likeFindAll(nick_name, currentPageNum, pageSize);
    }
}
