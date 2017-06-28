package mbapi.Services.ClientService;

import android.os.AsyncTask;

import java.util.ArrayList;

import mbapi.Constants.ClientField;
import mbapi.Constants.ServiceType;
import mbapi.Helper.SoapHelper;
import mbapi.RequestBuilder.ClientServiceXML;
import mbapi.Result.ClientServicesResult;
import mbapi.Result.ClientsResult;

public class GetClientServicesForBookingRequest extends AsyncTask<Void, Void, Void>
{
    private ClientServicesResult soapResult;
    private String payload = null;

    /**
     * Get client services (passes) to pay for booking
     * onCompletion(ClientServicesResult)
     */
    public GetClientServicesForBookingRequest(String clientID, String sessionTypeID)
    {
        payload = ClientServiceXML.getXmlGetClientServices(clientID, sessionTypeID, null, null);
    }

    @Override
    protected void onPreExecute() {}

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

    public void onCompletion(ClientServicesResult result) {}
}
