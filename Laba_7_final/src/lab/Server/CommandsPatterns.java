package lab.Server;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class CommandsPatterns {

    public static boolean checkWithRegExpRemove(String commands) {
        Pattern p = Pattern.compile("remove_greater \\{.*\\}$");
        Matcher m = p.matcher(commands);
        return m.matches();
    }

    public static boolean checkWithRegExpInsert(String commands) {
        Pattern p = Pattern.compile("insert \\{[0-9]*\\} \\{.*\\}$");
        Matcher m = p.matcher(commands);
        return m.matches();
    }

    public static boolean checkWithRegExpImport(String commands) {
        Pattern p = Pattern.compile("import \\{.*\\}$");
        Matcher m = p.matcher(commands);
        return m.matches();
    }
}