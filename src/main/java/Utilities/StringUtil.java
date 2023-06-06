package Utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static String extractIntegerFromString(String text, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? matcher.group(1) : null;
    }
}
