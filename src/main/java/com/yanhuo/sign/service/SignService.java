package com.yanhuo.sign.service;

import com.yanhuo.sign.dal.model.Sign;
import com.yanhuo.sign.dal.model.StudentInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author 烟火（yanhuo@maihaoche.com）
 * @version V1.0
 * @Description:
 * @date 2018/3/20 下午4:28
 */
public interface SignService {

    /**
     * 生成二维码
     */
     void createQRCode();

    /**
     * 根据课程名查询签到明细
     * @param cName
     * @return
     */
    List<Sign> selectSignDetailBycName(String cName);

    /**
     * 根据班级查询签到明细
     * @param sClass
     * @return
     */
    List<Sign> selectSignDetailBysClass(Long sClass);

    /**
     * 根据当前签到次数查询签到明细
     * @param signNum
     * @return
     */
    List<Sign> selectSignDetailBysignNum(int signNum);

    /**
     * 学生操作：
     * 根据学生Id查询签到明细
     * @param sId
     * @return
     */
    List<Sign> selectSignDetailBysId(Long sId);

    /**
     *实现Excel导入
     * @param xlsPath
     * @return
     */
    List<StudentInfo> importExcel(String xlsPath);



}
