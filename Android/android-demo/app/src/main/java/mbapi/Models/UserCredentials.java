package mbapi.Models;

/**
 * Created on 4/15/16.
 */
public class UserCredentials
{
    private static UserCredentials instance = null;

    public  String SourceName = null;
    public  String Password = null;

    public UserCredentials(String sourceName, String password)
    {
        this.SourceName = sourceName;
        this.Password = password;
        instance = this;
    }

    public static UserCredentials getInstance()
    {
        if (instance == null)
        {
            instance = new UserCredentials(null, null);
        }
        return instance;
    }
}
