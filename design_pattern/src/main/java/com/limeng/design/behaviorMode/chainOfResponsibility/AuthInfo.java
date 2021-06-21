package com.limeng.design.behaviorMode.chainOfResponsibility;

import lombok.Data;

@Data
public class AuthInfo {

    private String code;

    private String info = "";

    public AuthInfo(String code, String ...infos) {
        this.code = code;
        for (String info : infos) {
            this.info = this.info.concat(info);
        }
    }
}
