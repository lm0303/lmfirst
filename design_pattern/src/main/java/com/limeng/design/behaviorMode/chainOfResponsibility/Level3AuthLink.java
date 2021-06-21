package com.limeng.design.behaviorMode.chainOfResponsibility;

import java.text.ParseException;
import java.util.Date;

public class Level3AuthLink extends AuthLink {

    private Date beginDate = format.parse("2021-06-01 00:00:00");
    private Date endDate = format.parse("2021-06-25 23:59:59");

    public Level3AuthLink(String levelUserId, String levelUserName) throws ParseException {
        super(levelUserId, levelUserName);
    }

    @Override
    public AuthInfo doAuth(String uId, String orderId, Date authDate) {
        Date date = AuthService.queryAuthInfo(levelUserId, orderId);
        if (null == date) {
            return new AuthInfo("0001", "单号：", orderId, " 状态：待三级审批负责⼈ ", levelUserName);
        }
        AuthLink next = super.getNext();
        if (null == next) {
            return new AuthInfo("0000", "单号：", orderId, " 状态：三级审批负责⼈完成", " 时间：", format.format(date), " 审批⼈：", levelUserName);
        }
        if (authDate.before(beginDate) || authDate.after(endDate)) {
            return new AuthInfo("0000", "单号：", orderId, " 状态：三级审批负责⼈完成", " 时间：", format.format(date), " 审批⼈：", levelUserName);
        }
        return next.doAuth(uId, orderId, authDate);
    }
}
