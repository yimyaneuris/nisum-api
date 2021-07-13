import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator
{
    private static EmailValidator _instance = new EmailValidator();
    private static String pattern = null;

    private EmailValidator() { }

    public static EmailValidator buildValidator()
    {
        StringBuilder patternBuilder = new StringBuilder("^(.+)@(.+)$");
        pattern = patternBuilder.toString();

        return _instance;
    }
    
    public static boolean validateEmail(final String email)
    {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}