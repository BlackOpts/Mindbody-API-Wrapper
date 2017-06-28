package mbapi.Models;

public class SourceCredentials
{
    private static SourceCredentials instance = null;

    public  String SourceName = null;
    public  String Password = null;
    public  String SiteID = null;

    public SourceCredentials(String sourceName, String password, String siteID)
    {
        this.SourceName = sourceName;
        this.Password = password;
        this.SiteID = siteID;
        instance = this;
    }

    public static SourceCredentials getInstance()
    {
        if (instance == null)
        {
            instance = new SourceCredentials(null, null, null);
        }
        return instance;
    }
}
