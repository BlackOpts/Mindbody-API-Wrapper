package com.demo.mbapi.mbapidemo.AppointmentBooking;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.demo.mbapi.mbapidemo.BookingActivity;
import com.demo.mbapi.mbapidemo.ListViewAdapter.ListViewEntryItem;
import com.demo.mbapi.mbapidemo.ListViewAdapter.ListViewItemAdapter;
import com.demo.mbapi.mbapidemo.R;

import java.util.ArrayList;
import java.util.Date;

import mbapi.Context.Application;
import mbapi.Helper.Utility;
import mbapi.Models.Class;
import mbapi.Models.ClientService;
import mbapi.Models.Schedule;
import mbapi.Models.SessionType;
import mbapi.Result.ClassesResult;
import mbapi.Result.ClientServicesResult;
import mbapi.Result.GeneralResult;
import mbapi.Result.SchedulesResult;

public class BookableSessionsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{

    ProgressDialog progressView;
    ArrayList<Schedule> schedules = new ArrayList<>();
    ArrayList<ListViewEntryItem> items = new ArrayList<>();
    ArrayList<SessionType> appointmentSessionTypes = new ArrayList<>();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookable_sessions);

        getSupportActionBar().setTitle("Appointment selection");

        context = this;

        progressView = ProgressDialog.show(this, "Loading...", null);
        loadBookableSchedules();
    }

    private void populateListView()
    {
        for (int i = 0; i < schedules.size(); i++)
        {
            Schedule item = schedules.get(i);
            String detail = Utility.getStringFromDate(item.StartDateTime, "MMM dd, HH:mm a") + " - " +
                    Utility.getStringFromDate(item.EndDateTime, "HH:mm a") + " with " + item.Staff.FirstName;
            items.add(new ListViewEntryItem(null, item.SessionType.Name, detail, i));
        }

        ListView lv = (ListView) findViewById(R.id.lv_bookable_sessions);
        ListViewItemAdapter adapter = new ListViewItemAdapter(this, items);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);

        progressView.dismiss();
    }

    private void loadBookableSchedules()
    {

        Date startDate = new Date();
        Date endDate = Utility.addDaysToDate(startDate, 5);

        // Filter for only appointments
        appointmentSessionTypes = Utility.filterSessionTypesByServiceCategory(Application.context().SessionTypes, Application.context().BookingServiceCategory);
        ArrayList<String> sessionTypeIDs = new ArrayList<>();
        for (SessionType st : appointmentSessionTypes)
        {
            sessionTypeIDs.add(st.ID);
        }

        new mbapi.Services.AppointmentService.GetBookableSchedulesRequest(startDate, endDate, sessionTypeIDs)
        {
            @Override
            public void onCompletion(SchedulesResult result)
            {
                if (result.Success)
                {
                    schedules = result.Schedules;
                    populateListView();
                }
            }
        }.execute();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {

        final mbapi.Models.Schedule item = schedules.get(position);
        String detail = "At " + Utility.getStringFromDate(item.StartDateTime, "HH:mm a") + " - " +
                Utility.getStringFromDate(item.EndDateTime, "HH:mm a") +
                " with " + item.Staff.Name;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Book " + item.SessionType.Name);

        builder.setMessage(detail).setPositiveButton("Book", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                progressView.show();

                // book if the client has service to pay for it
                // otherwise, make the client purchase a pass
                new mbapi.Services.ClientService.GetClientServicesForBookingRequest(Application.context().CurrentClient.ID, item.SessionType.ID)
                {
                    @Override
                    public void onCompletion(ClientServicesResult result)
                    {
                        progressView.dismiss();

                        if (result.Success)
                        {
                            if (!result.ClientServices.isEmpty())
                            {
                                ClientService service = result.ClientServices.get(0);
                                bookAppointment(item, service);
                            }
                            else
                            {

                                // Set the global what class to book
                                Application.context().ScheduleToCheckout = item;
                                showAppointmentBookingActivity();
                            }
                        }
                        else
                        {
                            showMessageDiaglog("Error while checking client passes.");
                        }
                    }
                }.execute();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                // Cancel
            }
        });
        builder.create();
        builder.show();
    }

    private void bookAppointment(Schedule cls, ClientService serviceToPayFor)
    {
        new mbapi.Services.AppointmentService.BookScheduleRequest(cls, Application.context().CurrentClient.ID, serviceToPayFor.ID)
        {
            @Override
            public void onCompletion(GeneralResult result)
            {
                String msg = null;
                if (result.Success)
                {
                    msg = "Successfully booked :)";
                }
                else
                {
                    msg = "Failed to book :(";
                }

                showMessageDiaglog(msg);
            }
        }.execute();
    }

    private void showAppointmentBookingActivity()
    {
        startActivity(new Intent(this, BookingActivity.class));
    }

    private void showMessageDiaglog(String msg)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage(msg).setNegativeButton("OK", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                finish();
            }
        });
        builder.show();
    }
}
