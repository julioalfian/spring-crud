package com.julio.learnSB.util;

import com.julio.learnSB.config.message.MessageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.PushBuilder;

@Service
public class MessageLib {
    @Autowired
    MessageConfig messageConfig;

    public String getAddSuccess() {return messageConfig.get("student.save");}

    public String saveStudentFailed() {return messageConfig.get("student.save.failed");}
}
