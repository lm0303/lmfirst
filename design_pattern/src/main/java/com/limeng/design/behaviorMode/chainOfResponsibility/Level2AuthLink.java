package com.limeng.design.behaviorMode.chainOfResponsibility;

import java.text.ParseException;
import java.util.Date;

public class Level2AuthLink extends AuthLink{

    private Date beginDate = format.parse("2021-06-11 00:00:00");
    private Date endDate = format.parse("2021-06-22 23:59:59");

    public Level2AuthLink(String levelUserId, String levelUserName) throws ParseException {
        super(levelUserId, levelUserName);
    }

    @Override
    public AuthInfo doAuth(String uId, String orderId, Date authDate) {
        Date date = AuthService.queryAuthInfo(levelUserId, orderId);
        if (null == date) {
            return new AuthInfo("0001", "单号：", orderId, " 状态：待⼆级审批负责⼈ ", levelUserName);
        }
        AuthLink next = super.getNext();
        if (null == next) {
            return new AuthInfo("0000", "单号：", orderId, " 状态：⼆级审批完成负责⼈", " 时间：", format.format(date), " 审批⼈：", levelUserName);
        }
        if (authDate.before(beginDate) || authDate.after(endDate)) {
            return new AuthInfo("0000", "单号：", orderId, " 状态：⼆级审批完成负责⼈", " 时间：", format.format(date), " 审批⼈：", levelUserName);
        }
        return next.doAuth(uId, orderId, authDate);
    }
}
