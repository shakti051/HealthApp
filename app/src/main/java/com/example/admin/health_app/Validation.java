package com.example.admin.health_app;

import android.text.TextUtils;
import android.widget.EditText;

import java.util.regex.Pattern;

/**
 * Created by Admin on 4/24/2018.
 */

public class Validation {
    private static String emailPattern1 = "^.+@.+\\..+$";
    public static boolean emailAddressIsValid(String emailAddress, EditText fieldEditText) {
        if (TextUtils.isEmpty(emailAddress)) {
            fieldEditText.requestFocus();
            fieldEditText.setError("Please enter valid email address");
            fieldEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.error_img, 0);
            return false;
        } else if (!Pattern.matches(emailPattern1, emailAddress)) {
            fieldEditText.requestFocus();

            fieldEditText.setError("Please enter valid email address");
            fieldEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.error_img, 0);
            return false;
        } else {
            fieldEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            return true;
        }
    }
    public static boolean matchPassword(String password, EditText fieldEditText) {
        if (password.equals("")) {
            fieldEditText.requestFocus();
            fieldEditText.setError("Please enter valid password");
            fieldEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.error_img, 0);
            return false;
        }
//        else if (password.length() != 10) { //temporary stop this validation
//            fieldEditText.requestFocus();
//            fieldEditText.setError("Please enter valid mobile number");
//            fieldEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.error_img, 0);
//            return false;
//
//        }
        else {
            fieldEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            return true;
        }
    }

    public static boolean normalValidation(String value, EditText editText) {
        if (value.isEmpty()) {
            editText.requestFocus();
            editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.error_img, 0);
            return false;
        } else {
            editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            return true;
        }
    }

}
