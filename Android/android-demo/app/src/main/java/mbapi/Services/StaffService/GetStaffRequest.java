package mbapi.Services.StaffService;

import android.os.AsyncTask;

import mbapi.Constants.ServiceType;
import mbapi.Helper.SoapHelper;
import mbapi.RequestBuilder.StaffServiceXML;
import mbapi.Result.StaffResult;

public class GetStaffRequest extends AsyncTask<Void, Void, Void>
{
    private StaffResult soapResult;
    private String payload = null;
    /**
     * Get all staff members for the studio.
     * onCompletion(StaffResult)
     */
    public GetStaffRequest()
    {
        payload = StaffServiceXML.GetStaffXML(null, null);
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected Void doInBackground(Void... params)
    {
        String response = SoapHelper.GetResponse(payload, ServiceType.StaffService, "GetStaff");
        if (response != null)
        {
            soapResult = new StaffResult(response);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result)
    {
        onCompletion(soapResult);
    }

    public void onCompletion(StaffResult result) {}
}
