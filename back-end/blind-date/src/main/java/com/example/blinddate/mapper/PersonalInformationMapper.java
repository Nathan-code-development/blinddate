package com.example.blinddate.mapper;

import com.example.blinddate.entity.PersonalInformation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PersonalInformationMapper {
    List<PersonalInformation> findAll();
    List<PersonalInformation> findAllPro(String gender);
    boolean add(PersonalInformation personalInformation);
    boolean addHeadImage(Integer id, String head_image);
    boolean mod(PersonalInformation personalInformation);
    boolean addOrMod(PersonalInformation personalInformation);
    boolean delete(Integer id);
    Integer hasGenerateId(Integer id);
    List<PersonalInformation> recommendPeople(String household_registration, String gender);

    List<PersonalInformation> recommendPeoplePro(String household_registration, String gender, Integer pageNum, Integer pageSize);
    boolean addGenerateId(String personal_id);
    List<PersonalInformation> findOrderData(String gender, Integer pageNum, Integer pageSize);
    List<PersonalInformation> likeFindAll(String nick_name, Integer pageNum, Integer pageSize);
}
