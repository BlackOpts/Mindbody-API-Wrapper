package mbapi.Services.SaleService;

import android.os.AsyncTask;

import mbapi.Constants.ServiceType;
import mbapi.Helper.SoapHelper;
import mbapi.RequestBuilder.SaleServiceXML;
import mbapi.Result.ServicesResult;

public class GetServicesForClassRequest extends AsyncTask<Void, Void, Void>
{
    private ServicesResult soapResult;
    private String payload = null;

    /**
     * Get services that can be paid for the specific class.<br/>
     * onCompletion(ServicesResult)
     */
    /*public GetServicesForClassRequest(String classID, String locationID)
    {
        payload = SaleServiceXML.getXmlGetServices(null, null, classID, null, locationID);
    }*/

    /**
     * Get services that can be paid for the specific class for all locations.<br/>
     * onCompletion(ServicesResult)
     */
    public GetServicesForClassRequest(String classID)
    {
        payload = SaleServiceXML.getXmlGetServices(null, null, classID, null, null);
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
