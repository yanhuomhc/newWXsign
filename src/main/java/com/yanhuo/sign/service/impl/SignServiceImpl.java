package com.yanhuo.sign.service.impl;

import java.io.File;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sun.rowset.internal.Row;
import com.yanhuo.sign.dal.mapper.SignMapper;
import com.yanhuo.sign.dal.mapper.ext.SignExtMapper;
import com.yanhuo.sign.dal.model.Sign;
import com.yanhuo.sign.dal.model.StudentInfo;
import com.yanhuo.sign.service.SignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.*;

/**
 * @author 烟火（yanhuo@maihaoche.com）
 * @version V1.0
 * @Description: TODO
 * @date 2018/4/14 下午1:13
 */
@Service
@Slf4j
public class SignServiceImpl implements SignService{

    @Autowired
    private SignMapper signMapper;

    @Autowired
    private SignExtMapper signExtMapper;

    /**
     * 生成二维码
     * 解析二维码的工作调用微信"扫一扫"
     * @throws IOException
     * @throws WriterException
     */
    @Override
    public void createQRCode(){
        String text = "xxx.com";
        //LOGO宽度
        int width = 100;
        //LOGO高度
        int height = 100;
        String format = "png";
        //定义二维码参数
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET,"utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN,2);
        //生成二维码
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE,width,height,hints);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        try {
            Path file = new File("/Users/user19/Downloads/new.png").toPath();
            MatrixToImageWriter.writeToPath(bitMatrix,format,file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Sign> selectSignDetailBycName(String cName) {
        if (StringUtils.isEmpty(cName)) {
            return new ArrayList<>();
        }
        List<Sign> signs = new ArrayList<>();
        try {
            signs = signExtMapper.selectCourseBycName(cName);
        } catch (Exception e) {
            log.error("查询签到明细失败");
        }
        return signs;
    }

    @Override
    public List<Sign> selectSignDetailBysClass(Long sClass) {
        if (StringUtils.isEmpty(sClass)) {
            return new ArrayList<>();
        }
        List<Sign> signs = new ArrayList<>();
        try {
            signs = signExtMapper.selectSignDetailBysClass(sClass);
        } catch (Exception e) {
            log.error("查询签到明细失败");
        }
        return signs;
    }

    @Override
    public List<Sign> selectSignDetailBysignNum(int signNum) {
        if (StringUtils.isEmpty(signNum)) {
            return new ArrayList<>();
        }
        List<Sign> signs = new ArrayList<>();
        try {
            signs = signExtMapper.selectCourseBysignNum(signNum);
        } catch (Exception e) {
            log.error("查询签到明细失败");
        }
        return signs;
    }

    @Override
    public List<Sign> selectSignDetailBysId(Long sId) {
        if (StringUtils.isEmpty(sId)) {
            return new ArrayList<>();
        }
        List<Sign> signs = new ArrayList<>();

        try {
            signs = signExtMapper.selectSignDetailBysId(sId);
        } catch (Exception e) {
            log.error("查询签到明细失败");
        }
        return signs;
    }

    @Override
    public List<StudentInfo> importExcel(String xlsPath){
        List infoList = new ArrayList();
       // Map<String,List> map = new HashMap<String, List>();
        //infoList.clear();
        //       FileInputStream fileIn = new FileInputStream(xlsPath);
//        //根据指定的文件输入流导入excel从而产生workbook对象
//        Workbook wb0 = new HSSWorkbook(fileIn);
//        //获取Excel文档中的第一个表单
//        Sheet sht0 = wb0.getSheetAt(0);
//        //对Sheet中对每一行进行迭代
//        for (Row r : sht0) {
//            //如果当前对行号（从0开始）未达到2（第三行）则重新循环
//            if (r.getCurrentRow() < 1) {
//                continue;
//            }
//            StudentInfo studentInfo = new StudentInfo();
//            //取出当前第一个单元格数据，并封装在studentInfo实体sId属性上
//            studentInfo.setsId(r.getCell(0).getStringValue());
//            studentInfo.setsNo(r.getCell(1).getStringValue());
//            studentInfo.setsName(r.getCell(2).getStringValue());
//            studentInfo.setsClass(r.getCell(3).getStringValue());
//            studentInfo.setsSex(r.getCell(4).getStringValue());
//        }
        return null;
    }


}
