package com.yanhuo.sign.dal.mapper.ext;

import com.yanhuo.sign.dal.mapper.TeacherSignRecordMapper;
import com.yanhuo.sign.dal.model.TeacherSignRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherSignRecordExtMapper extends TeacherSignRecordMapper {
    TeacherSignRecord selectBytId(Integer  tId);

}