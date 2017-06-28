package mbapi.Services.SiteService;

import android.os.AsyncTask;

import mbapi.Constants.ServiceType;
import mbapi.Helper.SoapHelper;
import mbapi.RequestBuilder.SiteServiceXML;
import mbapi.Result.SessionTypesResult;

public class GetSessionTypesRequest extends AsyncTask<Void, Void, Void>
{
    private SessionTypesResult soapResult;
    private String payload = null;

    /**
     * Get session types.<br/>
     * onCompletion(SessionTypesResult)
     * @param onlineOnly only returns online session types
     */
    public GetSessionTypesRequest(boolean onlineOnly)
    {
        payload = SiteServiceXML.getXmlGetSessionTypes(onlineOnly);
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected Void doInBackground(Void... params)
    {
        String response = SoapHelper.GetResponse(payload, ServiceType.SiteService, "GetSessionTypes");
        if (response != null)
        {
            soapResult = new SessionTypesResult(response);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result)
    {
        onCompletion(soapResult);
    }

    public void onCompletion(SessionTypesResult result) {}
}
