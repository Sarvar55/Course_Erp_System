package com.erp.erpbackend.utils;

import com.erp.erpbackend.exception.BaseException;

public class CommonUtils {

    @FunctionalInterface
    public interface Checker {
        boolean check();
    }

    public static void throwIf(Checker checker, BaseException e) {
        if (checker.check())
            throw e;
    }
}
