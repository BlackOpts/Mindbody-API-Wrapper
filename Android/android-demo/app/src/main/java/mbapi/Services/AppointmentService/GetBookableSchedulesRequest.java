package mbapi.Services.AppointmentService;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Date;

import mbapi.Constants.ServiceType;
import mbapi.Helper.SoapHelper;
import mbapi.RequestBuilder.AppointmentServiceXML;
import mbapi.Result.SchedulesResult;

public class GetBookableSchedulesRequest extends AsyncTask<Void, Void, Void>
{
    private SchedulesResult soapResult;
    private String payload = null;

    /**
     * Get bookable schedules
     * onCompletion(SchedulesResult)
     */
    public GetBookableSchedulesRequest(Date startDate, Date endDate, String sessionTypeID, String locationID)
    {
        ArrayList list = new ArrayList<String>();
        list.add(sessionTypeID);
        payload = AppointmentServiceXML.getXmlGetBookableItems(startDate, endDate, list, locationID);
    }

    /**
     * Get bookable schedules for given session types
     * onCompletion(SchedulesResult)
     */
    public GetBookableSchedulesRequest(Date startDate, Date endDate, ArrayList<String> sessionTypeIDs, String locationID)
    {
        payload = AppointmentServiceXML.getXmlGetBookableItems(startDate, endDate, sessionTypeIDs, locationID);
    }

    /**
     * Get bookable schedules for given session types
     * onCompletion(SchedulesResult)
     */
    public GetBookableSchedulesRequest(Date startDate, Date endDate, ArrayList<String> sessionTypeIDs)
    {
        payload = AppointmentServiceXML.getXmlGetBookableItems(startDate, endDate, sessionTypeIDs, null);
    }

    /**
     * Get bookable schedules for all session types for all locations
     * onCompletion(SchedulesResult)
     */
    public GetBookableSchedulesRequest(Date startDate, Date endDate)
    {
        payload = AppointmentServiceXML.getXmlGetBookableItems(startDate, endDate, null, null);
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected Void doInBackground(Void... params)
    {
        String response = SoapHelper.GetResponse(payload, ServiceType.AppointmentService, "GetBookableItems");
        if (response != null)
        {
            soapResult = new SchedulesResult(response);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result)
    {
        onCompletion(soapResult);
    }

    public void onCompletion(SchedulesResult result) {}
}