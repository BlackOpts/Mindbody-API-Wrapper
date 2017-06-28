package mbapi.Services.SiteService;

import android.os.AsyncTask;

import mbapi.Constants.ServiceType;
import mbapi.Helper.SoapHelper;
import mbapi.RequestBuilder.SiteServiceXML;
import mbapi.Result.LocationsResult;

/**
 * Created on 8/23/16.
 */
public class GetLocationsRequest extends AsyncTask<Void, Void, Void>
{
    private LocationsResult soapResult;
    private String payload = null;

    /**
     * Get all active locations for the site that the source has access to.<br/>
     * onCompletion(LocationsResult)
     * @param cache cache the results
     */
    public GetLocationsRequest(boolean cache)
    {
        payload = SiteServiceXML.getXmlGetLocations();
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected Void doInBackground(Void... params)
    {
        String response = SoapHelper.GetResponse(payload, ServiceType.SiteService, "GetLocations");
        if (response != null)
        {
            soapResult = new LocationsResult(response);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result)
    {
        onCompletion(soapResult);
    }

    public void onCompletion(LocationsResult result) {}
}
