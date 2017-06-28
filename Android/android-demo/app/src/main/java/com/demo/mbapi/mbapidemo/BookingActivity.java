package com.demo.mbapi.mbapidemo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Date;

import mbapi.Context.Application;
import mbapi.Helper.Utility;
import mbapi.Models.Class;
import mbapi.Models.ClientService;
import mbapi.Models.CreditCardInfo;
import mbapi.Models.DebitAccountInfo;
import mbapi.Models.PaymentInfo;
import mbapi.Models.Schedule;
import mbapi.Models.Service;
import mbapi.Result.ClassesResult;
import mbapi.Result.ClientServicesResult;
import mbapi.Result.GeneralResult;
import mbapi.Result.ServicesResult;

public class BookingActivity extends AppCompatActivity
{

    boolean isBookingClass = true; // Are we booking class or appointment? You can add options for enrollments or courses.

    //boolean clientNowHasPass = false;

    TextView tvTitle;
    TextView tvTime;
    TextView tvTotal;
    RadioButton rbAccount;
    Context context;
    // This stores service that pays for booking
    Service passToBuy;
    ProgressDialog progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_booking);

        context = this;

        // r we doing class or appointment?
        isBookingClass = Application.context().ClassToCheckout != null;

        tvTitle = (TextView) findViewById(R.id.tv_booking_name);
        tvTime = (TextView) findViewById(R.id.tv_booking_time);
        tvTotal = (TextView) findViewById(R.id.tv_booking_total);
        rbAccount = (RadioButton) findViewById(R.id.rb_booking_account);

        // Display balance

        rbAccount.setText("Account credit ($" + Application.context().CurrentClient.AccountBalance + ")");

        displayBookingInfo();
        loadServiceThatPaysForBooking();
    }

    private void displayBookingInfo()
    {
        String titleText = isBookingClass ? "Class Booking" : "Appointment Booking";
        getSupportActionBar().setTitle(titleText);

        Date startTime = null, endTime = null;
        if (isBookingClass)
        {
            Class item = Application.context().ClassToCheckout;
            tvTitle.setText("Title: " + item.ClassDescription.Name + " with " + item.Staff.FirstName);
            startTime = item.StartDateTime;
            endTime = item.EndDateTime;
        }
        else
        {
            Schedule item = Application.context().ScheduleToCheckout;
            tvTitle.setText("Title: " + item.SessionType.Name + " with " + item.Staff.FirstName);
            startTime = item.StartDateTime;
            endTime = item.EndDateTime;
        }

        String time = "Time: " + Utility.getStringFromDate(startTime, "HH:mm") + " - " +
                Utility.getStringFromDate(endTime, "HH:mm");
        tvTime.setText(time);
    }

    private void loadServiceThatPaysForBooking()
    {
        if (isBookingClass)
        {
            // class
            // if the client has service to pay for, use it
            // otherwise, buy one


            new mbapi.Services.SaleService.GetServicesForClassRequest(Application.context().ClassToCheckout.ID)
            {
                @Override
                public void onCompletion(ServicesResult result)
                {
                    if (result.Success)
                    {
                        // assume you have setup the service for the class OR IT CRASHES
                        // Try pick the least expensive one
                        // pricing option = service = passToBuy. yea so many terms!
                        passToBuy = Utility.filterForLowestPriceService(result.Services);

                        // display price
                        tvTotal.setText("$" + passToBuy.OnlinePrice);
                    }
                }
            }.execute();
        }

        else
        {
            // appointment
            // if the client has service to pay for, use it
            // otherwise, buy one
            new mbapi.Services.SaleService.GetServicesForBookingRequest(Application.context().ScheduleToCheckout.SessionType.ID)
            {
                @Override
                public void onCompletion(ServicesResult result)
                {
                    if (result.Success)
                    {
                        passToBuy = Utility.filterForLowestPriceService(result.Services);

                        tvTotal.setText("$" + passToBuy.OnlinePrice);
                    }
                }
            }.execute();
        }
    }

    public void buyAndBook(View view)
    {
        PaymentInfo paymentInfo;

        // Use account credit
        if (rbAccount.isChecked())
        {
            DebitAccountInfo payment = new DebitAccountInfo();
            payment.Amount = passToBuy.OnlinePrice;

            paymentInfo = payment;
        }
        // Use credit card
        else
        {
            // TODO: You need to do input validation here
            CreditCardInfo payment = new CreditCardInfo();
            payment.Amount = passToBuy.OnlinePrice;
            payment.CreditCardNumber = ((EditText) findViewById(R.id.tf_booking_cc_num)).getText().toString();
            payment.BillingName = ((EditText) findViewById(R.id.tf_booking_cc_name)).getText().toString();
            payment.CVV = ((EditText) findViewById(R.id.tf_booking_back_code)).getText().toString();
            payment.BillingAddress = ((EditText) findViewById(R.id.tf_booking_address)).getText().toString();

            String monthYear = ((EditText) findViewById(R.id.tf_booking_exp_month_year)).getText().toString();
            String[] list = monthYear.split("/");
            payment.ExpMonth = list[0];
            payment.ExpYear = list[1];

            paymentInfo = payment;
        }

        progressView = ProgressDialog.show(this, "Loading...", null);

        new mbapi.Services.SaleService.CheckoutServiceRequest(passToBuy.ID, Application.context().CurrentClient.ID, paymentInfo)
        {
            @Override
            public void onCompletion(GeneralResult result)
            {
                String msg;
                if (result.Success)
                {
                    if (isBookingClass)
                    {
                        addClientToClass(Application.context().ClassToCheckout);
                    }
                    else
                    {
                        bookAppointment(Application.context().ScheduleToCheckout);
                    }
                }
                else
                {
                    showMessageDiaglog("Failed to buy the pass :(");
                }
            }
        }.execute();
    }

    private void addClientToClass(Class cls)
    {
        new mbapi.Services.ClassService.AddClientToClassRequest(cls.ID, Application.context().CurrentClient.ID, null /* let the system resolve the pass */, false /* true to send email */)
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

    private void bookAppointment(Schedule sch)
    {
        final Schedule schedule = sch;

        new mbapi.Services.ClientService.GetClientServicesForBookingRequest(Application.context().CurrentClient.ID, sch.SessionType.ID)
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

                        new mbapi.Services.AppointmentService.BookScheduleRequest(schedule, Application.context().CurrentClient.ID, service.ID)
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
                    else
                    {
                        showMessageDiaglog("Something wrong. Client has no pass for this session!");
                    }
                }
                else
                {
                    showMessageDiaglog("Error while checking client passes.");
                }
            }
        }.execute();



    }

    private void showMessageDiaglog(String msg)
    {
        progressView.dismiss();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage(msg).setNegativeButton("OK", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                // any result will close the view
                finish();
            }
        });
        builder.show();
    }
}

