package mbapi.RequestBuilder;

import java.util.List;

import mbapi.Helper.SoapHelper;
import mbapi.Models.CreditCardInfo;
import mbapi.Models.DebitAccountInfo;
import mbapi.Models.PaymentInfo;
import mbapi.Models.SourceCredentials;
import mbapi.Models.UserCredentials;

public class SaleServiceXML
{
    public static String getXmlGetServices(String serviceCategoryID, String sessionTypeID, String classID, String classScheduleID, String locationID)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<x:Envelope xmlns:x=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://clients.mindbodyonline.com/api/0_5\">");
        sb.append("<x:Header/><x:Body><ns:GetServices><ns:Request>");

        sb.append(SoapHelper.GetSourceCredentialsNode());

        if (serviceCategoryID != null) sb.append(String.format("<ns:ProgramIDs><ns:int>%s</ns:int></ns:ProgramIDs>", serviceCategoryID));
        if (sessionTypeID != null) sb.append(String.format("<ns:SessionTypeIDs><ns:int>%s</ns:int></ns:SessionTypeIDs>", sessionTypeID));
        if (classID != null) sb.append(String.format("<ns:ClassID>%s</ns:ClassID>", classID));
        if (classScheduleID != null) sb.append(String.format("<ns:ClassScheduleID>%s</ns:ClassScheduleID>", classScheduleID));
        if (locationID != null) sb.append(String.format("<ns:LocationID>%s</ns:LocationID>", locationID));
        sb.append("</ns:Request></ns:GetServices></x:Body></x:Envelope>");

        return sb.toString();
    }

    public static String getXmlCheckoutService(String serviceID, String clientID, PaymentInfo payment)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<x:Envelope xmlns:x=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
        sb.append("<x:Header/><x:Body><CheckoutShoppingCart xmlns=\"http://clients.mindbodyonline.com/api/0_5\"><Request>");

        SourceCredentials src = SourceCredentials.getInstance();
        sb.append("<SourceCredentials><SourceName>" + src.SourceName + "</SourceName>");
        sb.append("<Password>" + src.Password + "</Password>");
        sb.append("<SiteIDs><int>"+ src.SiteID + "</int></SiteIDs></SourceCredentials>");

        UserCredentials user = UserCredentials.getInstance();
        sb.append("<UserCredentials><Username>" + user.SourceName + "</Username>");
        sb.append("<Password>" + user.Password + "</Password>");
        sb.append("<SiteIDs><int>"+ src.SiteID + "</int></SiteIDs></UserCredentials>");

        if (clientID != null) sb.append(String.format("<ClientID>%s</ClientID>", clientID));

        sb.append("<CartItems><CartItem><Quantity>1</Quantity>");
        sb.append(String.format("<Item xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"Service\"><ID>%s</ID></Item>", serviceID));
        sb.append("</CartItem></CartItems>");
        sb.append(getPaymentXml(payment));

        sb.append("</Request></CheckoutShoppingCart></x:Body></x:Envelope>");

        return sb.toString();
    }

    private static String getPaymentXml(PaymentInfo payment)
    {
        StringBuilder sb = new StringBuilder();
        if (payment.getClass() == DebitAccountInfo.class)
        {
            DebitAccountInfo info = (DebitAccountInfo)payment;
            sb.append("<Payments><PaymentInfo xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"DebitAccountInfo\">");
            sb.append(String.format("<Amount>%s</Amount>", info.Amount));
            //sb.append("<DiscountAmount>0</DiscountAmount>");
            //DiscountAmount
            sb.append("</PaymentInfo></Payments>");
        }
        else if (payment.getClass() == CreditCardInfo.class)
        {
            CreditCardInfo info = (CreditCardInfo)payment;
            sb.append("<Payments><PaymentInfo xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.w3.org/2001/XMLSchema\" xsi:type=\"CreditCardInfo\">");
            sb.append(String.format("<Amount>%s</Amount>", info.Amount));
            sb.append(String.format("<BillingAddress>%s</BillingAddress>", info.BillingAddress));
            sb.append(String.format("<BillingName>%s</BillingName>", info.BillingName));
            sb.append(String.format("<CreditCardNumber>%s</CreditCardNumber>", info.CreditCardNumber));
            sb.append(String.format("<CVV>%s</CVV>", info.CVV));
            sb.append(String.format("<ExpMonth>%s</ExpMonth>", info.ExpMonth));
            sb.append(String.format("<ExpYear>%s</ExpYear>", info.ExpYear));
            sb.append("</PaymentInfo></Payments>");
        }
        return sb.toString();
    }
}
