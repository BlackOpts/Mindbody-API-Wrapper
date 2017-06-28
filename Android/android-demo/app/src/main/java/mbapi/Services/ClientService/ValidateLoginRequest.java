package mbapi.Services.ClientService;

import android.os.AsyncTask;

import java.util.ArrayList;

import mbapi.Constants.ClientField;
import mbapi.Constants.ServiceType;
import mbapi.Helper.SoapHelper;
import mbapi.RequestBuilder.ClientServiceXML;
import mbapi.Result.ClientsResult;

/**
 * Created on 8/23/16.
 */
public class ValidateLoginRequest extends AsyncTask<Void, Void, Void>
{
    private ClientsResult soapResult;
    private String payload = null;

    /**
     * Validate client login credentials.
     * <br/>onCompletion(ClientsResult)
     */
    public ValidateLoginRequest(String username, String password)
    {
        payload = ClientServiceXML.getXmlValidateLogin(username, password, null);
    }

    /**
     * Validate client login credentials with additional info on success.
     * <br/>onCompletion(ClientsResult)
     */
    public ValidateLoginRequest(String username, String password, ArrayList<ClientField> fields)
    {
        payload = ClientServiceXML.getXmlValidateLogin(username, password, fields);
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected Void doInBackground(Void... params)
    {
        String response = SoapHelper.GetResponse(payload, ServiceType.ClientService, "ValidateLogin");
        if (response != null)
        {
            soapResult = new ClientsResult(response);



        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result)
    {
        onCompletion(soapResult);
    }

    public void onCompletion(ClientsResult result) {}
}
