package br.com.vsgdev.socialsoftware.utils;


import android.content.Context;
import android.widget.EditText;

public class ValidateUtils {

    public static Boolean checkEmpty(EditText editText) {
        if (editText.getText().toString().matches(""))
            return true;
        return false;
    }

    public static EditText checkEmptyWithErro(EditText editText, Context context, String erroMessage) {
        if (editText.getText().toString().matches(""))
            editText.setError(erroMessage);
        return editText;
    }
}
