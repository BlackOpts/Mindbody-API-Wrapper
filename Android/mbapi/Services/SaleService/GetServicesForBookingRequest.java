package mbapi.Services.SaleService;

import android.os.AsyncTask;

import mbapi.Constants.ServiceType;
import mbapi.Helper.SoapHelper;
import mbapi.RequestBuilder.SaleServiceXML;
import mbapi.Result.ServicesResult;

public class GetServicesForBookingRequest extends AsyncTask<Void, Void, Void>
{
    private ServicesResult soapResult;
    private String payload = null;

    /**
     * Get services that can be paid for the specific session type.<br/>
     * onCompletion(ServicesResult)
     */
    /*public GetServicesForBookingRequest(String sessionTypeID, String locationID)
    {
        payload = SaleServiceXML.getXmlGetServices(null, sessionTypeID, null, null, locationID);
    }*/

    /**
     * Get services that can be paid for the specific session type for all locations.<br/>
     * onCompletion(ServicesResult)
     */
    public GetServicesForBookingRequest(String sessionTypeID)
    {
        payload = SaleServiceXML.getXmlGetServices(null, sessionTypeID, null, null, null);
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected Void doInBackground(Void... params)
    {
        String response = SoapHelper.GetResponse(payload, ServiceType.SaleService, "GetServices");
        if (response != null)
        {
            soapResult = new ServicesResult(response);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result)
    {
        onCompletion(soapResult);
    }

    public void onCompletion(ServicesResult result) {}
}
