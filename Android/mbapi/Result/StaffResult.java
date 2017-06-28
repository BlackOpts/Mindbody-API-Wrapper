package mbapi.Result;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

import mbapi.Models.Staff;

/**
 * Created on 4/15/16.
 */
public class StaffResult extends ResultParser
{
    public ArrayList<Staff> StaffMembers;

    public StaffResult(String response)
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
        for (int i = 0; i < ResultDOM.getLength(); i++)
        {
            Node n = ResultDOM.item(i);

            ParseResultInfo(n);

            if (n.getNodeName().equals("StaffMembers"))
            {
                StaffMembers = new ArrayList<>();
                NodeList staffMembers = n.getChildNodes();
                for (int c = 0; c < staffMembers.getLength(); c++)
                {
                    StaffMembers.add(Staff.Parse(staffMembers.item(c)));
                }
            }
        }
    }
}
