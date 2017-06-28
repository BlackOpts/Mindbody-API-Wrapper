package mbapi.Services.ClientService;

import android.os.AsyncTask;

import java.util.Date;

import mbapi.Constants.ServiceType;
import mbapi.Helper.SoapHelper;
import mbapi.RequestBuilder.ClientServiceXML;
import mbapi.Result.VisitsResult;

public class GetClientScheduleRequest extends AsyncTask<Void, Void, Void>
{
    private VisitsResult soapResult;
    private String payload = null;

    /**
     * Get client visits<br/>
     * onCompletion(VisitsResult)
     */
    public GetClientScheduleRequest(String clientID)
    {
        payload = ClientServiceXML.getXmlGetClientSchedule(clientID);
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