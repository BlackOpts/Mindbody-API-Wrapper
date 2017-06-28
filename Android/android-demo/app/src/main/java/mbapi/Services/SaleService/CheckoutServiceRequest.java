package mbapi.Services.SaleService;

import android.os.AsyncTask;

import mbapi.Constants.ServiceType;
import mbapi.Helper.SoapHelper;
import mbapi.Models.PaymentInfo;
import mbapi.RequestBuilder.SaleServiceXML;
import mbapi.Result.GeneralResult;

public class CheckoutServiceRequest extends AsyncTask<Void, Void, Void>
{
    private GeneralResult soapResult;
    private String payload = null;

    /**
     * Buy a service for the client with given payment info.<br/>
     * onCompletion(GeneralResult)
     */
    public CheckoutServiceRequest(String serviceID, String clientID, PaymentInfo payment)
    {
        payload = SaleServiceXML.getXmlCheckoutService(serviceID, clientID, payment);
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected Void doInBackground(Void... params)
    {
        String response = SoapHelper.GetResponse(payload, ServiceType.SaleService, "CheckoutShoppingCart");
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
