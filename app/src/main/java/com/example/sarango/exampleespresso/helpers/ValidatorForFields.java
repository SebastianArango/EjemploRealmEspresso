package com.example.sarango.exampleespresso.helpers;

/**
 * Created by sarango on 24/08/2016.
 */
public class ValidatorForFields {

    public static boolean validatorStringsEmpty(String... strings) {
        for (String string : strings) {
            if (string.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
