package mbapi.Services.ClientService;

import android.os.AsyncTask;

import java.util.ArrayList;

import mbapi.Constants.ClientField;
import mbapi.Constants.ServiceType;
import mbapi.Helper.SoapHelper;
import mbapi.Models.ServiceCategory;
import mbapi.RequestBuilder.ClientServiceXML;
import mbapi.Result.ClientServicesResult;
import mbapi.Result.ClientsResult;

public class GetClientServicesRequest extends AsyncTask<Void, Void, Void>
{
    private ClientServicesResult soapResult;
    private String payload = null;

    /**
     * Get client services
     * onCompletion(ClientServicesResult)
     */
    public GetClientServicesRequest(String clientID, ArrayList<ServiceCategory> serviceCategories)
    {
        ArrayList<String> ids = new ArrayList<>();
        for (ServiceCategory item : serviceCategories)
        {
            ids.add(item.ID);
        }


        payload = ClientServiceXML.getXmlGetClientServices(clientID, null, null, ids);
    }

    @Override
    protected void onPreExecute()
    {
    }

    @Override
    protected Void doInBackground(Void... params)
    {
        String response = SoapHelper.GetResponse(payload, ServiceType.ClientService, "GetClientServices");
        if (response != null)
        {
            soapResult = new ClientServicesResult(response);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result)
    {
        onCompletion(soapResult);
    }

    public void onCompletion(ClientServicesResult result)
    {
    }
}
