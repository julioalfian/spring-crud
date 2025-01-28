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

    public String getAccountSaveAccountEmailError() {
        return messageConfig.get("account.save.account.email.error");
    }

    public String getAccountNotFoundError() {
        return messageConfig.get("account.notfound.error");
    }

    public String emailNotValid() {return messageConfig.get("account.email.not.valid");}

    public String getLoginFailedError() {
        return messageConfig.get("login.failed.error");
    }
}
