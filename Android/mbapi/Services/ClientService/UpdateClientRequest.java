package mbapi.Services.ClientService;

import android.os.AsyncTask;

import mbapi.Constants.ServiceType;
import mbapi.Helper.SoapHelper;
import mbapi.Models.Client;
import mbapi.RequestBuilder.ClientServiceXML;
import mbapi.Result.ClientsResult;

/**
 * Created on 8/23/16.
 */
public class UpdateClientRequest extends AsyncTask<Void, Void, Void>
{
    private ClientsResult soapResult;
    private String payload = null;

    /**
     * Update a client for the specified client ID.
     * <br/>onCompletion(ClientsResult)
     */
    public UpdateClientRequest(Client client, String clientID)
    {
        payload = ClientServiceXML.getXmlAddOrUpdateClients(client, clientID);
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected Void doInBackground(Void... params)
    {
        String response = SoapHelper.GetResponse(payload, ServiceType.ClientService, "AddOrUpdateClients");
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
