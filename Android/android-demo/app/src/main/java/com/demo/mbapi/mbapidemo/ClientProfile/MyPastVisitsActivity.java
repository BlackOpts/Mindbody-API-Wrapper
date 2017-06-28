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
import mbapi.Models.Visit;
import mbapi.Result.VisitsResult;

public class MyPastVisitsActivity extends AppCompatActivity
{
    ArrayList<Visit> visits;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_past_visits);

        getSupportActionBar().setTitle("My Visit History");

        loadMyPastVisits();
    }

    private void populateListView()
    {
        ArrayList<ListViewEntryItem> items = new ArrayList<>();
        // For each visit, render the detail to a list entry
        for (int index = 0; index < visits.size(); index++)
        {
            Visit item = visits.get(index);
            String detail = Utility.getStringFromDate(item.StartDateTime, "MMM, dd yyyy HH:mm a") + " with " + item.Staff.FirstName;

            items.add(new ListViewEntryItem(item.getClass(), item.Name, detail, index));
        }

        ListView lv = (ListView) findViewById(R.id.lv_my_past_visits);
        ListViewItemAdapter adapter = new ListViewItemAdapter(this, items);
        lv.setAdapter(adapter);
    }

    private void loadMyPastVisits()
    {
        new mbapi.Services.ClientService.GetClientVisitHistoryRequest(Application.context().CurrentClient.ID)
        {
            @Override
            public void onCompletion(VisitsResult result)
            {
                if (result.Success)
                {

                    visits = result.Visits;
                    populateListView();
                }
            }
        }.execute();
    }
}
