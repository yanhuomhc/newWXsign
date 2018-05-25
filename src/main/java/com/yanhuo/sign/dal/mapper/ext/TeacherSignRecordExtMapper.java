package com.yanhuo.sign.dal.mapper.ext;

import com.yanhuo.sign.dal.mapper.TeacherSignRecordMapper;
import com.yanhuo.sign.dal.model.TeacherSignRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherSignRecordExtMapper extends TeacherSignRecordMapper {
    /**
     * 根据教师id查询签到记录表
     * @param tId
     * @return
     */
    TeacherSignRecord selectBytId(Integer  tId);

}