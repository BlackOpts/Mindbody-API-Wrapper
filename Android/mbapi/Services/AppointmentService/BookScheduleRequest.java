package mbapi.Services.AppointmentService;

import android.os.AsyncTask;

import mbapi.Constants.ServiceType;
import mbapi.Helper.SoapHelper;
import mbapi.Models.Schedule;
import mbapi.RequestBuilder.AppointmentServiceXML;
import mbapi.Result.GeneralResult;

public class BookScheduleRequest extends AsyncTask<Void, Void, Void>
{
    private GeneralResult soapResult;
    private String payload = null;

    /**
     * Book the schedule for the client with the pass
     * onCompletion(GeneralResult)
     */
    public BookScheduleRequest(Schedule schedule, String clientID, String serviceID)
    {
        payload = AppointmentServiceXML.getXmlAddOrUpdateAppointments(schedule.Location.ID, schedule.SessionType.ID, clientID, schedule.Staff.ID, schedule.StartDateTime, serviceID);
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected Void doInBackground(Void... params)
    {
        String response = SoapHelper.GetResponse(payload, ServiceType.AppointmentService, "AddOrUpdateAppointments");
        if (response != null)
        {
            soapResult = new GeneralResult(response);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result)
    {
        onCompletion(soapResult);
    }

    public void onCompletion(GeneralResult result) {}
}