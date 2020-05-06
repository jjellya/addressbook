package com.baldgroup.addressbook.controller;

import com.baldgroup.addressbook.enums.ResultEnum;
import com.baldgroup.addressbook.exception.UserException;
import com.baldgroup.addressbook.mapper.ModifyInfo;
import com.baldgroup.addressbook.mapper.SearchInfo;
import com.baldgroup.addressbook.pojo.PersonInfo;
import com.baldgroup.addressbook.pojo.UserInfo;
import com.baldgroup.addressbook.service.impl.FileServiceImpl;
import com.baldgroup.addressbook.service.impl.PersonServiceImpl;
import com.baldgroup.addressbook.utils.CsvExportUtil;
import com.baldgroup.addressbook.utils.KeyUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Create By  @林俊杰
 * 2020/4/26 12:19
 *
 * @version 1.0
 */
@Controller
public class FileController {

    @Resource
    private ModifyInfo modifyInfo;
    @Resource
    private SearchInfo searchInfo;
    @Autowired
    private FileServiceImpl fileService;
    @Autowired
    private PersonServiceImpl personService;

    @PostMapping("/user/upload")
    public String upload(MultipartFile multipartFile,
                         HttpSession session){

        //获取用户id
        String userId = String.valueOf(session.getAttribute("loginUserId"));

        if (multipartFile.isEmpty()) {
            System.out.println("WARING============================================>该文件无任何内容!!!");
        }


        //获取文件原始名称
        String oldFileName = multipartFile.getOriginalFilename();

        //获取文件后缀
        String extension = "."+FilenameUtils.getExtension(multipartFile.getOriginalFilename());//这东西没有点,例如".vCard"只有"vCard"

        //生成新的文件名称 (时间撮+UUID.extension)
        String newFileName = new SimpleDateFormat("yyyyMMddHHmmss")+ KeyUtil.genUniqueKey()+extension;

        //文件的大小
        Long size = multipartFile.getSize();

        String realPath="";
        //处理文件上传
        try{
            realPath = ResourceUtils.getURL("classpath:").getPath() + "static/files";
            //生成以时间命名的目录名
            String dateDirPath = realPath + "/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            //生成以时间命名的目录
            File dateDir = new File(dateDirPath);
            if(!dateDir.exists()) {
                dateDir.mkdirs();//新建文件夹;
            }
            //文件上传
            try {
                File targetFile =new File(dateDir, newFileName);
                multipartFile.transferTo(targetFile);


                List<PersonInfo> personList = fileService.transformPersonInfo(targetFile,userId);
                System.out.println(personList);
                if(!personList.isEmpty()){
                    personService.addPersonList(personList,userId);
                }


            }
            catch (IllegalStateException e){
                //TODO
                System.out.println("ERROR====================================================>"+e.getMessage());
            }
            //将文件信息放入数据库中
        }catch (IOException e){
            //TODO
            System.out.println("ERROR====================================================>"+e.getMessage());
        }catch (UserException e){
            System.out.println(e.getMessage());
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }


        return "redirect:/user/list";

    }

    @GetMapping("/user/download")
    public String download(HttpServletResponse response,HttpSession session){
        try {

            //获取用户id
            String userId = String.valueOf(session.getAttribute("loginUserId"));
            UserInfo user  = (UserInfo) session.getAttribute("loginUser");

            List<Map<String,Object>> dataList =new ArrayList<>();
            Map<String, Object> map = null;

            List<PersonInfo> personList= searchInfo.queryAllPersonInfos(userId);

            if (personList.isEmpty()){
                throw new UserException(ResultEnum.USER_NULL_EXPORT);
            }

            //导出数据结构
            String titles = "姓名,手机,电话,WeChat,QQ,E-mail,图像,工作单位,家庭住址,生日,邮编,备注";
            String keys = "name,phone1,phone2,weChat,QQ,email,icon,company,address,birthday,postcode,remark";

            for (PersonInfo person:personList
                 ) {
                map = new HashMap<>();

                map.put("name",person.getPersonName());
                map.put("phone1",person.getPersonPhone1());
                map.put("phone2",person.getPersonPhone2());
                map.put("weChat",person.getPersonWechat());
                map.put("QQ",person.getPersonQq());
                map.put("email",person.getPersonMail());
                map.put("icon",person.getPersonIcon());
                map.put("company",person.getPersonCompany());
                map.put("address",person.getPersonAddress());
                map.put("birthday",person.getPersonBirthday());
                map.put("postcode",person.getPersonPostcode());
                map.put("remark",person.getPersonRemark());

                dataList.add(map);
            }


            //文件导出
            OutputStream os = response.getOutputStream();
            os.write(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF});
            CsvExportUtil.responseSetProperties(response,user.getUserName()+"的通讯录");
            CsvExportUtil.doExport(dataList,titles,keys,os);
            os.close();

        }catch (UserException e){
            //TODO
            System.out.println(e.getMessage());
        }catch (Exception e){
            //TODO
            System.out.println(e.getMessage());
        }

        return null;
    }
}
