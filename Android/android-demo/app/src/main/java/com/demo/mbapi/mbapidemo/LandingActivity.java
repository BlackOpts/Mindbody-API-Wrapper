package com.demo.mbapi.mbapidemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.demo.mbapi.mbapidemo.AppointmentBooking.BookableSessionsActivity;
import com.demo.mbapi.mbapidemo.ClassRegistration.ClassesActivity;
import com.demo.mbapi.mbapidemo.ClientProfile.ClientProfileActivity;
import com.demo.mbapi.mbapidemo.ListViewAdapter.ListViewEntryItem;
import com.demo.mbapi.mbapidemo.ListViewAdapter.ListViewItemAdapter;

import java.util.ArrayList;
import java.util.Date;

import mbapi.Context.Application;
import mbapi.Helper.Utility;
import mbapi.Models.Class;
import mbapi.Models.ServiceCategory;
import mbapi.Models.Visit;
import mbapi.Result.ClassesResult;
import mbapi.Result.ServiceCategoriesResult;
import mbapi.Result.SessionTypesResult;
import mbapi.Result.VisitsResult;

import static mbapi.Constants.ScheduleType.Appointment;
import static mbapi.Helper.Utility.filterServiceCategoriesByScheduleType;

public class LandingActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    ProgressDialog progressView;
    // Dashboard stuff
    ArrayList<Class> upcommingClasses;
    ArrayList<ServiceCategory> appointmentServiceCategories;
    ArrayList<Visit> mySchedules;

    ArrayList<ListViewEntryItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        // Setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Dashboard");

        progressView = ProgressDialog.show(this, "Loading...", null);
        loadMySchedule();
        loadServiceCategories();
        loadUpcommingClasses();
        loadSessionTypes(); // later use
    }

    private void tryLoadListViewDashBoard()
    {

        // Ensure everything is loaded before proceed
        if (appointmentServiceCategories == null || upcommingClasses == null || mySchedules == null || Application.context().SessionTypes == null)
        {
            return;
        }

        // Build list view
        items.clear();

        // Upcomming classes
        items.add(new ListViewEntryItem("My schedule"));
        for (int i = 0; i < mySchedules.size(); i++)
        {
            Visit item = mySchedules.get(i);
            String detail = Utility.getStringFromDate(item.StartDateTime, "MMM dd, HH:mm a") + " - " +
                    Utility.getStringFromDate(item.EndDateTime, "HH:mm a") +
                    " with " + item.Staff.Name;
            items.add(new ListViewEntryItem(Visit.class, item.Name, detail, i));
        }

        // Upcomming classes
        items.add(new ListViewEntryItem("Upcomming classes"));
        for (int i = 0; i < upcommingClasses.size(); i++)
        {
            Class item = upcommingClasses.get(i);
            String detail = Utility.getStringFromDate(item.StartDateTime, "MMM dd, HH:mm a") + " with " + item.Staff.Name;
            items.add(new ListViewEntryItem(Class.class, item.ClassDescription.Name, detail, i));
        }
        items.add(new ListViewEntryItem(Class.class));

        // Show types of appointments that user can book
        items.add(new ListViewEntryItem("Book appointment"));
        for (int i = 0; i < appointmentServiceCategories.size(); i++)
        {
            ServiceCategory item = appointmentServiceCategories.get(i);
            items.add(new ListViewEntryItem(ServiceCategory.class, item.Name, "", i));
        }

        ListView lv = (ListView) findViewById(R.id.listview);
        ListViewItemAdapter adapter = new ListViewItemAdapter(this, items);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);

        progressView.dismiss();
    }

    private void loadServiceCategories()
    {
        new mbapi.Services.SiteService.GetServiceCategoriesRequest(true)
        {
            @Override
            public void onCompletion(ServiceCategoriesResult result)
            {
                if (result.Success)
                {
                    Application.context().ServiceCategories = result.ServiceCategories;
                    appointmentServiceCategories = filterServiceCategoriesByScheduleType(Application.context().ServiceCategories, Appointment);
                    tryLoadListViewDashBoard();
                }
            }
        }.execute();
    }

    private void loadSessionTypes()
    {
        new mbapi.Services.SiteService.GetSessionTypesRequest(true)
        {
            @Override
            public void onCompletion(SessionTypesResult result)
            {
                if (result.Success)
                {
                    Application.context().SessionTypes = result.SessionTypes;
                    tryLoadListViewDashBoard();
                }
            }
        }.execute();
    }

    private void loadUpcommingClasses()
    {
        Date today = new Date();
        Date tomorow = Utility.addDaysToDate(today, 1);
        new mbapi.Services.ClassService.GetClassesRequest(today, tomorow)
        {
            @Override
            public void onCompletion(ClassesResult result)
            {
                if (result.Success)
                {
                    upcommingClasses = result.Classes;
                    tryLoadListViewDashBoard();
                }
            }
        }.execute();
    }

    private void loadMySchedule()
    {
        new mbapi.Services.ClientService.GetClientScheduleRequest(Application.context().CurrentClient.ID)
        {
            @Override
            public void onCompletion(VisitsResult result)
            {
                if (result.Success)
                {
                    mySchedules = result.Visits;
                    tryLoadListViewDashBoard();
                }
            }
        }.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {

        ListViewEntryItem item = items.get(position);
        if (item.isMoreContentAction() && item.Class == Class.class)
        {
            startActivity(new Intent(this, ClassesActivity.class));
        }
        else if (item.Class == ServiceCategory.class)
        {
            Application.context().BookingServiceCategory = appointmentServiceCategories.get(item.Index);
            startActivity(new Intent(this, BookableSessionsActivity.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_landing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_profile:
                // load client profile activity
                Intent intent = new Intent(this, ClientProfileActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_logout:
                // distroy context and go to signin screen
                Application.clear();
                finish();
                return true;

            case R.id.action_refresh:
                progressView = ProgressDialog.show(this, "Loading...", null);
                mySchedules = null;
                loadMySchedule();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                //return super.onOptionsItemSelected(item);
                return false;

        }
    }

    @Override
    public void onBackPressed()
    {
        moveTaskToBack(false);
    }
}
