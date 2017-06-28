package com.demo.mbapi.mbapidemo.ClientProfile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.demo.mbapi.mbapidemo.ListViewAdapter.ListViewEntryItem;
import com.demo.mbapi.mbapidemo.ListViewAdapter.ListViewItemAdapter;
import com.demo.mbapi.mbapidemo.R;

import java.util.ArrayList;

import mbapi.Context.Application;
import mbapi.Helper.Utility;

public class ClientProfileActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_profile);

        getSupportActionBar().setTitle("My Profile");

        populateClientInfo();
    }

    private void populateClientInfo()
    {
        ArrayList<ListViewEntryItem> items = new ArrayList<>();
        String title;
        title = Application.context().CurrentClient.FirstName + "  " + Application.context().CurrentClient.LastName;
        items.add(new ListViewEntryItem(null, title, "Name", 0));

        items.add(new ListViewEntryItem(null, Application.context().CurrentClient.MobilePhone, "Mobile", 1));

        items.add(new ListViewEntryItem(null, Application.context().CurrentClient.Email, "Email", 2));

        items.add(new ListViewEntryItem(null, "$" + Application.context().CurrentClient.AccountBalance, "Balance", 3));

        title = Utility.getStringFromDate(Application.context().CurrentClient.BirthDate, "MMM, dd yyyy");
        items.add(new ListViewEntryItem(null, title, "Birthday", 4));

        // to my schedules
        items.add(new ListViewEntryItem(null, "My Schedules", "Tap to view schedules", 5));

        // to my passes
        items.add(new ListViewEntryItem(null, "My Passes", "Tap to view passes and memberships", 6));

        // to my visit history
        items.add(new ListViewEntryItem(null, "Visit History", "Tap to view past visits", 7));

        ListView lv = (ListView) findViewById(R.id.lv_my_info);
        ListViewItemAdapter adapter = new ListViewItemAdapter(this, items);
        lv.setAdapter(adapter);
        // support onClick
        lv.setOnItemClickListener(this);
    }

    public void showMyPasses()
    {
        Intent intent = new Intent(this, MyPassesActivity.class);
        startActivity(intent);
    }

    public void showMySchedule()
    {
        Intent intent = new Intent(this, MyScheduleActivity.class);
        startActivity(intent);
    }

    public void showMyPastVisits()
    {
        Intent intent = new Intent(this, MyPastVisitsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        if (position == 5)
        {
            showMySchedule();
        } else if (position == 6)
        {
            showMyPasses();
        } else if (position == 7)
        {
            showMyPastVisits();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        startActivity(new Intent(this, ResetPasswordActivity.class));

        return false;
    }
}
