package com.erp.erpbackend.utils;

import java.util.Random;

public class OTPUtils {

    public static String generate() {
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000));
    }
}
