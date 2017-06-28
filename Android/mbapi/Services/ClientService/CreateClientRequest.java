package mbapi.Services.ClientService;

import android.os.AsyncTask;

import mbapi.Constants.ServiceType;
import mbapi.Helper.SoapHelper;
import mbapi.Models.Client;
import mbapi.RequestBuilder.ClientServiceXML;
import mbapi.Result.ClientsResult;

public class CreateClientRequest extends AsyncTask<Void, Void, Void>
{
    private ClientsResult soapResult;
    private String payload = null;

    /**
     * Add a new client to the studio.
     * <br/>onCompletion(ClientsResult)
     */
    public CreateClientRequest(Client client)
    {
        payload = ClientServiceXML.getXmlAddOrUpdateClients(client, null);
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
