package com.example.blinddate.controller;

import com.example.blinddate.common.CommonDto;
import com.example.blinddate.common.GenerateID;
import com.example.blinddate.common.GetStringObjectMap;
import com.example.blinddate.entity.PersonalInformation;
import com.example.blinddate.service.PersonalInformationService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/PersonalInformation")
public class PersonalInformationController {
    @Autowired
    private PersonalInformationService personalInformationService;

    @Value("${images.path}")
    private String basePath;

    @GetMapping("/generateID")
    public String generateID(){
        return GenerateID.generateID();
    }

    @GetMapping("/findAll")
    public Map<String, Object> findAll(){
        List<PersonalInformation> list = personalInformationService.findAll();
        return GetStringObjectMap.getStringObjectMap(list);
    }

    @GetMapping("/findAllPro")
    public Map<String, Object> findAllPro(@RequestParam String gender){
        List<PersonalInformation> list = personalInformationService.findAllPro(gender);
        return GetStringObjectMap.getStringObjectMap(list);
    }

    @PostMapping("/add")
    public boolean add(@RequestBody PersonalInformation personalInformation){
        return personalInformationService.add(personalInformation);
    }

    @GetMapping("/addHeadImage")
    public boolean addHeadImage(@RequestParam Integer id, @RequestParam String head_image){
        return personalInformationService.addHeadImage(id, head_image);
    }

    @PostMapping("/mod")
    public boolean mod(@RequestBody PersonalInformation personalInformation){
        return personalInformationService.mod(personalInformation);
    }

    @PostMapping("/addOrMod")
    public boolean addOrMod(@RequestBody PersonalInformation personalInformation){
        return personalInformationService.addOrMod(personalInformation);
    }

    @GetMapping("/delete")
    public boolean delete(@RequestParam Integer id){
        return personalInformationService.delete(id);
    }

    @GetMapping("/hasGenerateId")
    public boolean hasGenerateId(@RequestParam Integer id){
        return personalInformationService.hasGenerateId(id);
    }

    @GetMapping("/recommendPeople")
    public Map<String, Object> recommendPeople(@RequestParam String household_registration, @RequestParam String gender){
        List<PersonalInformation> list = personalInformationService.recommendPeople(household_registration, gender);
        return GetStringObjectMap.getStringObjectMap(list);
    }

    @GetMapping("/recommendPeoplePro")
    public Map<String, Object> recommendPeoplePro(@RequestParam String household_registration, @RequestParam String gender, @RequestParam Integer pageNum, @RequestParam Integer pageSize){
        List<PersonalInformation> list = personalInformationService.recommendPeoplePro(household_registration, gender, pageNum, pageSize);
        return GetStringObjectMap.getStringObjectMap(list);
    }

    @GetMapping("/addGenerateId")
    public boolean addGenerateId(@RequestParam String personal_id){
        return personalInformationService.addGenerateId(personal_id);
    }

    @PostMapping("/upload")
    public CommonDto<String> upload(MultipartFile file){

        //原始文件名
        String originalFilename = file.getOriginalFilename();
        String suffix = null;
        if (originalFilename != null) {
            suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        //使用UUID重新生成一个文件名,防止文件名重复造成文件覆盖
        String fileName = UUID.randomUUID().toString() + suffix;

        //创建一个目录
        File dir = new File(basePath);
        //判断当前目录是否存在
        if(!dir.exists()){
            //如果目录不存在就直接创建
            dir.mkdirs();
        }
        try {
            //将临时文件转存到执行位置
            file.transferTo(new File(basePath+fileName));
        }catch (IOException e){
            e.printStackTrace();
        }
        CommonDto<String> commonDto = new CommonDto<>();
        commonDto.setContent(fileName);
        return commonDto;
    }

    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){

        try {
            //输入流,通过输入流读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));
            //输出流,通过输出流将文件返回给浏览器,在浏览器中展示图片
            ServletOutputStream outputStream = response.getOutputStream();
//            response.setContentType("image/jpeg");

            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
            //关闭资源
            outputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/findOrderData")
    public Map<String, Object> findOrderData(@RequestParam String gender, @RequestParam Integer pageNum, @RequestParam Integer pageSize){
        List<PersonalInformation> list = personalInformationService.findOrderData(gender, pageNum, pageSize);
        return GetStringObjectMap.getStringObjectMap(list);
    }

    @GetMapping("/likeFindAll")
    public Map<String, Object> likeFindAll(@RequestParam String nick_name, @RequestParam Integer pageNum, @RequestParam Integer pageSize){
        List<PersonalInformation> list = personalInformationService.likeFindAll(nick_name, pageNum, pageSize);
        return GetStringObjectMap.getStringObjectMap(list);
    }
}
