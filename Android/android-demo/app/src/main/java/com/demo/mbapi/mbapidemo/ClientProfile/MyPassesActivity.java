package com.demo.mbapi.mbapidemo.ClientProfile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.demo.mbapi.mbapidemo.ListViewAdapter.ListViewEntryItem;
import com.demo.mbapi.mbapidemo.ListViewAdapter.ListViewItemAdapter;
import com.demo.mbapi.mbapidemo.R;

import java.util.ArrayList;

import mbapi.Constants.ErrorCode;
import mbapi.Context.Application;
import mbapi.Helper.Utility;
import mbapi.Models.ClientService;
import mbapi.Result.ClientServicesResult;

public class MyPassesActivity extends AppCompatActivity
{

    ArrayList<ClientService> myPasses;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_passes);

        getSupportActionBar().setTitle("My Passes");
        loadMyPasses();
    }

    private void populateListView()
    {
        ArrayList<ListViewEntryItem> items = new ArrayList<>();

        for (int i = 0; i < myPasses.size(); i++)
        {
            ClientService item = myPasses.get(i);
            String detail = Utility.getStringFromDate(item.ActiveDate, "MMM, dd yyyy") + " - " +
                    Utility.getStringFromDate(item.ExpirationDate, "MMM, dd yyyy");

            if (item.Remaining > 0)
            {
                detail = detail + " (" + item.Remaining + " remaining)";
            }

            items.add(new ListViewEntryItem(item.getClass(), item.Name, detail, i));
        }

        ListView lv = (ListView) findViewById(R.id.lv_my_passes);
        ListViewItemAdapter adapter = new ListViewItemAdapter(this, items);
        lv.setAdapter(adapter);
    }

    private void loadMyPasses()
    {
        new mbapi.Services.ClientService.GetClientServicesRequest(Application.context().CurrentClient.ID, Application.context().ServiceCategories)
        {
            @Override
            public void onCompletion(ClientServicesResult result)
            {
                if (result.Success)
                {

                    myPasses = new ArrayList<ClientService>();
                    // Only active ones
                    for (ClientService item : result.ClientServices)
                    {
                        if (item.Current)
                        {
                            myPasses.add(item);
                        }
                    }

                    populateListView();
                }
            }
        }.execute();
    }
}
