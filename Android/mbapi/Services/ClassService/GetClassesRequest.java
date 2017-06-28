package mbapi.Services.ClassService;

import android.os.AsyncTask;
import java.util.Date;

import mbapi.Constants.ServiceType;
import mbapi.Helper.SoapHelper;
import mbapi.RequestBuilder.ClassServiceXML;
import mbapi.Result.ClassesResult;

public class GetClassesRequest extends AsyncTask<Void, Void, Void>
{
    private ClassesResult soapResult;
    private String payload = null;

    /**
     * Get classes by date range for all locations and the results is limitted to 50.
     * onCompletion(ClassesResult)
     */
    public GetClassesRequest(Date startDate, Date endDate)
    {
        payload = ClassServiceXML.getXmlGetClasses(null, null, null, false, startDate, endDate, null);
    }

    /**
     * Get the class by ID.
     * onCompletion(ClassesResult)
     */
    public GetClassesRequest(String classID)
    {
        payload = ClassServiceXML.getXmlGetClasses(classID, null, null, true, null, null, null);
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected Void doInBackground(Void... params)
    {
        String response = SoapHelper.GetResponse(payload, ServiceType.ClassService, "GetClasses");
        if (response != null)
        {
            soapResult = new ClassesResult(response);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result)
    {
        onCompletion(soapResult);
    }

    public void onCompletion(ClassesResult result) {}
}
