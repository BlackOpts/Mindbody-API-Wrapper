package mbapi.Context;

import java.util.ArrayList;

import mbapi.Models.Client;
import mbapi.Models.Schedule;
import mbapi.Models.ServiceCategory;
import mbapi.Models.SessionType;

/**
 * Created on 6/1/17.
 */

public class Application
{
    private static Application instance = null;

    public Client CurrentClient = null;

    public ArrayList<ServiceCategory> ServiceCategories;

    public ArrayList<SessionType> SessionTypes;

    public ServiceCategory BookingServiceCategory;

    public mbapi.Models.Class ClassToCheckout;

    public Schedule ScheduleToCheckout;

    public Application(Client client)
    {
        this.CurrentClient = client;

        instance = this;
    }

    public static Application context()
    {
        if (instance == null)
        {
            instance = new Application(null);
        }
        return instance;
    }

    public static void clear()
    {
        instance = null;
    }
}
