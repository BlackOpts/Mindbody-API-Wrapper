package mbapi.Services.ClientService;

import android.os.AsyncTask;

import mbapi.Constants.ServiceType;
import mbapi.Helper.SoapHelper;
import mbapi.RequestBuilder.ClientServiceXML;
import mbapi.Result.VisitsResult;


public class GetClientVisitHistoryRequest extends AsyncTask<Void, Void, Void>
{
    private VisitsResult soapResult;
    private String payload = null;

    /**
     * Get client past visits<br/>
     * onCompletion(VisitsResult)
     */
    public GetClientVisitHistoryRequest(String clientID)
    {
        payload = ClientServiceXML.getXmlGetClientPastSchedule(clientID);
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected Void doInBackground(Void... params)
    {
        String response = SoapHelper.GetResponse(payload, ServiceType.ClientService, "GetClientSchedule");
        if (response != null)
        {
            soapResult = new VisitsResult(response);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result)
    {
        onCompletion(soapResult);
    }

    public void onCompletion(VisitsResult result) {}
}
