package com.baldgroup.addressbook.service.impl;

import com.baldgroup.addressbook.pojo.PersonInfo;
import com.baldgroup.addressbook.service.FileService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Create By  @林俊杰
 * 2020/4/26 23:53
 *
 * @version 1.0
 */
@Service
public class FileServiceImpl implements FileService {
    @Override
    public List<PersonInfo> transformPersonInfo(File file) {

        List<PersonInfo> personInfoList = Arrays.asList();

        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(file),"UTF-8");){
            BufferedReader bfr=new BufferedReader(isr);
            String line = null ;
            int i=0;
            while((line=bfr.readLine())!= null){

                // Matcher matcher = pattern.matcher(line);
                if (line.trim().length() > 1) {
                    //students[i]=new Student();
                    personInfoList.get(i).setPersonName(line.substring(0,line.indexOf(',')));
                    line=line.substring(line.indexOf(',')+1,line.length());
                    personInfoList.get(i).setPersonPhone1(line.substring(0,line.indexOf(',')));
                    personInfoList.get(i).setPersonPhone2(line.substring(line.indexOf(',')+1,line.length()));

                    //TODO File
                    i++;
                }
            }


        } catch (FileNotFoundException e) {
            throw new NullPointerException();
        } catch (IOException e) {
            throw new NullPointerException();
        }



        return null;
    }
}
