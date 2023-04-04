package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int age = 33;
        char c = 'a';
        double d = 33.0;
        long l = 333333L;
        boolean b = true;
        byte bb = 127;
        float f = 33.01f;
        short s = 33;
        LOG.debug("int is {}, char equals {}, double: {}, long: {}, "
                + "boolean: {}, byte: {}, float: {}, short: {}", age, c, d, l, b, bb, f, s);
    }
}