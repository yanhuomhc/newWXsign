package com.yanhuo.sign.service;


/**
 * @author 烟火（yanhuo@maihaoche.com）
 * @version V1.0
 * @Description:
 * @date 2018/4/15 下午9:52
 */
public interface StudentInfoService {
    /**
     * 发送带附件的邮件
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    void sendAttachmentsMail(String to,String subject,String content,String filePath);
}
