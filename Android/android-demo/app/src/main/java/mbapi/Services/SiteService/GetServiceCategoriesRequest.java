package mbapi.Services.SiteService;

import android.os.AsyncTask;

import mbapi.Constants.ScheduleType;
import mbapi.Constants.ServiceType;
import mbapi.Helper.SoapHelper;
import mbapi.RequestBuilder.SiteServiceXML;
import mbapi.Result.ServiceCategoriesResult;

/**
 * Created on 8/25/16.
 */
public class GetServiceCategoriesRequest extends AsyncTask<Void, Void, Void>
{
    private ServiceCategoriesResult soapResult;
    private String payload = null;

    /**
     * Get service categories.<br/>
     * onCompletion(ServiceCategoriesResult)
     * @param onlineOnly only returns online service categories
     */
    public GetServiceCategoriesRequest(boolean onlineOnly)
    {
        payload = SiteServiceXML.getXmlGetPrograms(ScheduleType.All, onlineOnly);
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected Void doInBackground(Void... params)
    {
        String response = SoapHelper.GetResponse(payload, ServiceType.SiteService, "GetPrograms");
        if (response != null)
        {
            soapResult = new ServiceCategoriesResult(response);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result)
    {
        onCompletion(soapResult);
    }

    public void onCompletion(ServiceCategoriesResult result) {}
}
