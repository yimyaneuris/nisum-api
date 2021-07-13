import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator
{
    private static PasswordValidator _instance = new PasswordValidator();
    private static String pattern = null;

    private PasswordValidator() { }

    public static PasswordValidator buildValidator( boolean capitalLetter = true, boolean number = true)
    {
        // minisculas
        StringBuilder patternBuilder = new StringBuilder("((?=.*[a-z])");

        if (capitalLetter)
        {
            patternBuilder.append("(?=.*[A-Z])");
        }

        if (forceNumber)
        {
            patternBuilder.append("(?=.[2]d)");
        }

        pattern = patternBuilder.toString();

        return _instance;
    }

    public static boolean validatePassword(final String password)
    {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(password);
        return m.matches();
    }
}