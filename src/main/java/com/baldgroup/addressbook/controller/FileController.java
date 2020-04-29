package com.baldgroup.addressbook.controller;

import com.baldgroup.addressbook.mapper.ModifyInfo;
import com.baldgroup.addressbook.mapper.SearchInfo;
import com.baldgroup.addressbook.utils.KeyUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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
            }
            catch (IllegalStateException e){
                //TODO
                System.out.println("ERROR====================================================>"+e.getMessage());
            }
            //将文件信息放入数据库中
        }catch (IOException e){
            //TODO
            System.out.println("ERROR====================================================>"+e.getMessage());
        }







        return "redirect:/user/list";

    }
}
