package com.erp.erpbackend.models.enums.otp;

import com.erp.erpbackend.exception.BaseException;
import com.erp.erpbackend.models.mybatis.user.User;

public enum OTPChannel {
    SMS,
    EMAIL;

    public String getTarget(User user) {
        if (this.equals(SMS)) {
            return user.getPhoneNumber();
        } else if (this.equals(EMAIL)) {
            return user.getEmail();
        } else {
            throw BaseException.unexpected();
        }
    }
}
