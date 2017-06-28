package com.demo.mbapi.mbapidemo.ClassRegistration;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.demo.mbapi.mbapidemo.BookingActivity;
import com.demo.mbapi.mbapidemo.ListViewAdapter.ListViewEntryItem;
import com.demo.mbapi.mbapidemo.ListViewAdapter.ListViewItemAdapter;
import com.demo.mbapi.mbapidemo.R;

import java.util.ArrayList;
import java.util.Date;

import mbapi.Constants.ErrorCode;
import mbapi.Context.Application;
import mbapi.Helper.Utility;
import mbapi.Models.Class;
import mbapi.Models.ClientService;
import mbapi.Result.ClassesResult;
import mbapi.Result.ClientServicesResult;

public class ClassesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{

    ProgressDialog progressView;
    ArrayList<Class> classes;
    Date currentDate = new Date();
    Context context;

    ArrayList<ListViewEntryItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);

        context = this;

        getSupportActionBar().setTitle("Class selection");

        loadToday();

    }

    private void loadToday()
    {
        currentDate = new Date();
        loadClasses();
    }

    private void loadNext()
    {
        currentDate = Utility.addDaysToDate(currentDate, 1);
        loadClasses();
    }

    private void loadPrev()
    {
        currentDate = Utility.addDaysToDate(currentDate, -1);
        loadClasses();
    }

    private void loadClasses()
    {

        // Do not load anything before today
        if (Utility.dateIsBeforeDate(currentDate, new Date()))
        {
            classes = new ArrayList<>();
            populateListView();
            return;
        }

        progressView = ProgressDialog.show(this, "Loading...", null);

        new mbapi.Services.ClassService.GetClassesRequest(currentDate, currentDate)
        {
            @Override
            public void onCompletion(ClassesResult result)
            {
                if (result.Success)
                {
                    classes = result.Classes;
                    populateListView();

                    progressView.dismiss();
                }
            }
        }.execute();
    }

    private void populateListView()
    {
        // New list for each day
        items = new ArrayList<>();

        items.add(new ListViewEntryItem(Utility.getStringFromDate(currentDate, "MMM, dd yyyy"))); // header
        for (int i = 0; i < classes.size(); i++)
        {
            Class item = classes.get(i);
            String detail = Utility.getStringFromDate(item.StartDateTime, "HH:mm") + " - " +
                    Utility.getStringFromDate(item.EndDateTime, "HH:mm") +
                    " with " + item.Staff.Name;
            items.add(new ListViewEntryItem(Class.class, item.ClassDescription.Name, detail, i));
        }
        ListView lv = (ListView) findViewById(R.id.lv_classes);
        ListViewItemAdapter adapter = new ListViewItemAdapter(this, items);
        lv.setAdapter(adapter);

        // register listview tapping event
        lv.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_classes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_classes_today)
        {
            loadToday();
        }
        else if (id == R.id.action_classes_next)
        {
            loadNext();
        }
        else if (id == R.id.action_classes_prev)
        {
            loadPrev();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {

        int itemIndex = position - 1; // -1 because the really first item is the table header
        final mbapi.Models.Class item = classes.get(itemIndex);
        String detail = "At " + Utility.getStringFromDate(item.StartDateTime, "HH:mm a") + " - " +
                Utility.getStringFromDate(item.EndDateTime, "HH:mm a") +
                " with " + item.Staff.Name;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Book " + item.ClassDescription.Name);

        builder.setMessage(detail).setPositiveButton("Book", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                progressView.show();

                // book if the client has service to pay for it
                // otherwise, make the client purchase a pass
                new mbapi.Services.ClientService.GetClientServicesForClassRequest(Application.context().CurrentClient.ID, item.ID)
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
                                addClientToClass(item, service);
                            }
                            else
                            {

                                // Set the global what class to book
                                Application.context().ClassToCheckout = item;
                                showClassBookingActivity();
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

    private void addClientToClass(Class cls, ClientService serviceToPayFor)
    {
        new mbapi.Services.ClassService.AddClientToClassRequest(cls.ID, Application.context().CurrentClient.ID, serviceToPayFor.ID, true)
        {
            @Override
            public void onCompletion(ClassesResult result)
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

    private void showClassBookingActivity()
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
