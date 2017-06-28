package mbapi.Services.ClientService;

import android.os.AsyncTask;

import java.util.ArrayList;

import mbapi.Constants.ClientField;
import mbapi.Constants.ServiceType;
import mbapi.Helper.SoapHelper;
import mbapi.RequestBuilder.ClientServiceXML;
import mbapi.Result.ClientsResult;

public class GetClientRequest extends AsyncTask<Void, Void, Void>
{
    private ClientsResult soapResult;
    private String payload = null;

    /**
     * Get client corresponding to the ID.
     * onCompletion(ClientsResult)
     * @param clientID
     */
    public GetClientRequest(String clientID)
    {
        payload = ClientServiceXML.GetClientsXML(clientID, null);
    }

    /**
     * Get client corresponding to the ID with additional info specified in the fields.
     * onCompletion(ClientsResult)
     * @param clientID
     * @param fields nullable
     */
    public GetClientRequest(String clientID, ArrayList<ClientField> fields)
    {
        payload = ClientServiceXML.GetClientsXML(clientID, fields);
    }

    @Override
    protected void onPreExecute() {}


    @Override
    protected Void doInBackground(Void... params)
    {
        String response = SoapHelper.GetResponse(payload, ServiceType.ClientService, "GetClientAccountBalances");
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
