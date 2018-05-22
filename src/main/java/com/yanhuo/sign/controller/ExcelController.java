package com.yanhuo.sign.controller;

import com.yanhuo.sign.dal.mapper.ext.StudentInfoExtMapper;
import com.yanhuo.sign.dal.model.StudentInfo;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExcelController {

    @Autowired
    private StudentInfoExtMapper studentInfoExtMapper;

    private String filePath;
    private List list = new ArrayList();

    public ExcelController() {
    }


    @RequestMapping("toExcel")
    public String toExcel() {
        return "admin/Excel";
    }


    //上传excel

    /**
     * 单个文件
     *
     * @param file
     * @return
     */
    @RequestMapping("/UploadExcel")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {

                InputStream stream = file.getInputStream();
                Workbook rwb = Workbook.getWorkbook(stream);
                //获取文件的指定工作表 默认的第一个
                Sheet sheet = rwb.getSheet(0);
                //行数(表头的目录不需要，从1开始)
                for (int i = 0; i < sheet.getRows(); i++) {
                    //创建一个数组 用来存储每一列的值
                    String[] str = new String[sheet.getColumns()];
                    Cell cell = null;
                    //列数
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        //获取第i行，第j列的值
                        cell = sheet.getCell(j, i);
                        str[j] = cell.getContents();
                    }
                    //把刚获取的列存入list
                    list.add(str);
                }


                for (int i = 0; i < list.size(); i++) {
                    String[] str = (String[]) list.get(i);
                    for (int j = 0; j < str.length; j++) {
                        System.out.print(str[j] + '\t');
                        //str 就是一个属性值
                    }

                    if (i > 0) {

                        StudentInfo studentInfo = new StudentInfo();
                        studentInfo.setsClass(Long.valueOf(str[3]));
                        studentInfo.setsName(str[1]);
                        studentInfo.setsNo(str[0]);
                        Boolean sex=Boolean.FALSE;
                        if (str[2].equals("男")){
                            sex=Boolean.TRUE;
                        }
                        studentInfo.setsSex(sex);

                        studentInfoExtMapper.insert(studentInfo);


                    }


                    System.out.println(); //所有属性取完  就是换行
                }


            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (BiffException e) {
                return "上传失败," + e.getMessage();
            }

            return "上传成功";
        } else {
            return "上传失败，因为文件是空的.";
        }


    }


}
