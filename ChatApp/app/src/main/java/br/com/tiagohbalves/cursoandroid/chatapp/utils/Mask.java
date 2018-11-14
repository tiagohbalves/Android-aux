package br.com.tiagohbalves.cursoandroid.chatapp.utils;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

public abstract class Mask {


    private static final String telDDDmask = "(##) ####-####";// Tel with DDD
    private static final String celDDDmask = "(##) #####-####";// cel with DDD
    private static final String telmask = "####-####";//Tel 8 digits
    private static final String celmask = "#####-####";//Cel 9 digits
    private static final String basicmask= "####";


    public static String unmask(String s) {
        return s.replaceAll("[^0-9]*", "");
    }

    public static TextWatcher insert(final EditText ediTxt) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";
            public void onTextChanged(CharSequence s, int start, int before,int count) {
                String str = Mask.unmask(s.toString());
                String mask;
                String defaultMask = getDefaultMask(str);
                switch (str.length()) {
                    case 4:
                        mask = basicmask;
                        break;
                    case 9:
                        mask = celmask;
                        break;
                    default:
                        mask = defaultMask;
                        break;
                }



                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;

                    return;
                }
                int i = 0;
                for (char m : mask.toCharArray()) {
                    if (m != '#') {
                        mascara += m;
                        continue;
                    }
                    try {
                        mascara += str.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                ediTxt.setText(mascara);
                ediTxt.setSelection(mascara.length());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void afterTextChanged(Editable s) {}
        };
    }


    private static String getDefaultMask(String str) {
        String defaultMask = basicmask;
        if(str.length()>4)
        {
            defaultMask = telmask;
        }
        if (str.length() > 9){
            defaultMask = celmask;
        }
        return defaultMask;
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}

