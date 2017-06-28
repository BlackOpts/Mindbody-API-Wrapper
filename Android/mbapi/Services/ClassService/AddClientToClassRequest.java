package mbapi.Services.ClassService;

import android.os.AsyncTask;
import java.util.Date;

import mbapi.Constants.ServiceType;
import mbapi.Helper.SoapHelper;
import mbapi.RequestBuilder.ClassServiceXML;
import mbapi.Result.ClassesResult;

public class AddClientToClassRequest extends AsyncTask<Void, Void, Void>
{
    private ClassesResult soapResult;
    private String payload = null;

    /**
     * Add a client to the class and attempt to use the client's pass to pay for it. Fails if the client does not have the pass.
     * onCompletion(ClassesResult)
     * @param classID ID of a Class
     * @param clientID ID of a Client
     * @param serviceID ID of a Service. Service is so called a pass, or pricing option
     * @param sendEmail Send confirmation email to the client
     */
    public AddClientToClassRequest(String classID, String clientID, String serviceID, Boolean sendEmail)
    {
        payload = ClassServiceXML.getXmlAddClientsToClasses(classID, clientID, serviceID, sendEmail);
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected Void doInBackground(Void... params)
    {
        String response = SoapHelper.GetResponse(payload, ServiceType.ClassService, "AddClientsToClasses");
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

