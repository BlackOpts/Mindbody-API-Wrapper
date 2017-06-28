package mbapi.Helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import mbapi.Constants.ServiceType;
import mbapi.Models.SourceCredentials;
import mbapi.Models.UserCredentials;

/**
 * Created on 4/15/16.
 */
public class SoapHelper
{
    /**
     * Make a request for a specified payload with the specified endpoint
     * Available endpoints: AppointmentService, StaffService, ClientService, SaleService, ClassService, SiteService
     * */
    public static String GetResponse(String payload, ServiceType service, String methodName)
    {
        String serviceEndpoint = service.toString() + ".asmx";
        String responseString = null;

        try
        {
            String line;
            StringBuffer res = new StringBuffer();
            URL url = new URL("https://api.mindbodyonline.com/0_5/" + serviceEndpoint);
            HttpURLConnection httpClient = (HttpURLConnection)url.openConnection();
            httpClient.setRequestMethod("POST");
            httpClient.setDoInput(true);
            httpClient.setDoOutput(true);

            httpClient.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            httpClient.setRequestProperty("SOAPAction", "http://clients.mindbodyonline.com/api/0_5/" + methodName);
            httpClient.connect();

            OutputStreamWriter writer = new OutputStreamWriter(httpClient.getOutputStream(), "UTF-8");
            writer.write(payload);
            writer.close();
            try
            {
                BufferedReader br = new BufferedReader(new InputStreamReader(httpClient.getInputStream()));
                while((line = br.readLine()) != null)
                {
                    res.append(line);
                }
                br.close();
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }

            httpClient.disconnect();

            responseString = res.toString();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return responseString;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line;
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public static String GetSourceCredentialsNode()
    {
        SourceCredentials src = SourceCredentials.getInstance();
        StringBuilder sb = new StringBuilder();
        sb.append("<ns:SourceCredentials><ns:SourceName>" + src.SourceName + "</ns:SourceName>");
        sb.append("<ns:Password>" + src.Password + "</ns:Password>");
        sb.append("<ns:SiteIDs><ns:int>"+ src.SiteID+ "</ns:int></ns:SiteIDs></ns:SourceCredentials>");
        return sb.toString();
    }


    public static String GetUserCredentialsNode()
    {
        UserCredentials user = UserCredentials.getInstance();
        StringBuilder sb = new StringBuilder();
        sb.append("<ns:UserCredentials><ns:Username>" + user.SourceName + "</ns:Username>");
        sb.append("<ns:Password>" + user.Password + "</ns:Password>");
        sb.append("<ns:SiteIDs><ns:int>" + SourceCredentials.getInstance().SiteID + "</ns:int></ns:SiteIDs></ns:UserCredentials>");
        return sb.toString();
    }
}
