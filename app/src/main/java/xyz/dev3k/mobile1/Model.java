package xyz.dev3k.mobile1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Model {
    public boolean comprobarCorreo(String correo) {
        Pattern patron = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);

        Matcher matcher = patron.matcher(correo);
        return matcher.find();
    }


}
