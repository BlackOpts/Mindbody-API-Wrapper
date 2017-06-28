package com.demo.mbapi.mbapidemo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import mbapi.Models.Client;
import mbapi.Models.SourceCredentials;
import mbapi.Models.UserCredentials;
import mbapi.Result.ClientsResult;

import static mbapi.Context.Application.context;

public class MainActivity extends AppCompatActivity
{
    ProgressDialog progressView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        context = this;

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        // Initialize credentials
        new SourceCredentials("", "", "-99");
        new UserCredentials("Siteowner", "apitest1234");
    }

    public void logIn(View view)
    {
        progressView = ProgressDialog.show(this, "Signing in...", null);

        TextView tvUsername = (TextView) findViewById(R.id.tfUsername);
        TextView tvPassword = (TextView) findViewById(R.id.tf_password);


        new mbapi.Services.ClientService.ValidateLoginRequest(tvUsername.getText().toString(), tvPassword.getText().toString())
        {
            @Override
            public void onCompletion(ClientsResult result)
            {
                if (result.Success)
                {
                    Client clt = result.Clients.get(0);

                    LoadClientInfoWithLandingActivity(clt.ID);
                }
                else
                {
                    showMessageDiaglog("Username or password is invalid");
                }

                progressView.dismiss();
            }
        }.execute();
    }

    public void LoadClientInfoWithLandingActivity(String clientId)
    {

        new mbapi.Services.ClientService.GetClientRequest(clientId)
        {
            @Override
            public void onCompletion(ClientsResult result)
            {
                if (result.Success)
                {
                    context().CurrentClient = result.Clients.get(0);

                    Intent intent = new Intent(context, LandingActivity.class);
                    startActivity(intent);

                }
            }
        }.execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        startActivity(new Intent(this, SignupActivity.class));

        return false;
    }

    private void showMessageDiaglog(String msg)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage(msg).setNegativeButton("OK", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {

            }
        });
        builder.show();
    }
}
