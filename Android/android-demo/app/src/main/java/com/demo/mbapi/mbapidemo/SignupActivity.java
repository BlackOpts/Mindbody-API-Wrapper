package com.demo.mbapi.mbapidemo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import mbapi.Models.Client;
import mbapi.Result.ClientsResult;

public class SignupActivity extends AppCompatActivity
{

    ProgressDialog progressView;
    Context context;
    boolean finished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        context = this;
        getSupportActionBar().setTitle("Create Account");

    }

    public void addNewClient(View view)
    {
        EditText tfFirst = (EditText)findViewById(R.id.tf_signup_first);
        EditText tfLast = (EditText)findViewById(R.id.tf_signup_last);
        EditText tfEmail = (EditText)findViewById(R.id.tf_signup_email);
        EditText tfPassword = (EditText)findViewById(R.id.tf_signup_password);
        EditText tfPhone = (EditText)findViewById(R.id.tf_signup_phone);

        // TODO: add better validation here
        if (tfFirst.getText().toString().equals("") ||
            tfLast.getText().toString().equals("") ||
            tfEmail.getText().toString().equals("") ||
            tfPassword.getText().toString().equals("") ||
            tfPhone.getText().toString().equals(""))
        {
            showMessageDiaglog("Missing required information :(");
        }
        else
        {
            Client item = new Client();
            item.FirstName = tfFirst.getText().toString();
            item.LastName = tfLast.getText().toString();
            item.Email = tfEmail.getText().toString();
            item.Username = item.Email; // make email to login
            item.Password = tfPassword.getText().toString();
            item.MobilePhone = tfPhone.getText().toString();

            progressView = ProgressDialog.show(this, null, "Signing up");

            new mbapi.Services.ClientService.CreateClientRequest(item)
            {
                @Override
                public void onCompletion(ClientsResult result)
                {
                    progressView.dismiss();

                    if (result.Success)
                    {
                        showMessageDiaglog("Welcome! Signup completed successfully :)");
                        finished = true;
                    }
                    else
                    {
                        showMessageDiaglog("Error. Failed to create new account :(");
                    }
                }
            }.execute();
        }
    }

    private void showMessageDiaglog(String msg)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage(msg).setNegativeButton("OK", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                if (finished)
                    finish();
            }
        });
        builder.show();
    }
}
