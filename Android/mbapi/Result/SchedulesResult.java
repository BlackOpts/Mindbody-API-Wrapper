package mbapi.Result;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import mbapi.Models.Schedule;

public class SchedulesResult extends ResultParser
{
    public ArrayList<Schedule> Schedules;

    public SchedulesResult(String response)
    {
        if (response != null)
        {
            CreateResultDOM(response);
            Parse();
        }
    }

    @Override
    protected void Parse()
    {
        for (int index1 = 0; index1 < ResultDOM.getLength(); index1++)
        {
            Node n = ResultDOM.item(index1);

            ParseResultInfo(n);

            if (n.getNodeName().equals("ScheduleItems"))
            {
                Schedules = new ArrayList<>();
                NodeList locs = n.getChildNodes();
                for (int index2 = 0; index2 < locs.getLength(); index2++)
                {
                    Schedules.add(Schedule.Parse(locs.item(index2)));
                }
            }
        }
    }
}
