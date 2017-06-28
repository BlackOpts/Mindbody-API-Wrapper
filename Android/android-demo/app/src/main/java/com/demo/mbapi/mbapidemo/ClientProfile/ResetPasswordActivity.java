package com.demo.mbapi.mbapidemo.ClientProfile;

import android.app.AlertDialog;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.demo.mbapi.mbapidemo.R;

import mbapi.Models.Client;
import mbapi.Result.ClientsResult;

public class ResetPasswordActivity extends AppCompatActivity
{

    ProgressDialog progressView;
    Context context;
    boolean finished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        context = this;
        getSupportActionBar().setTitle("Reset Password");

    }

    public void resetPassword(View view)
    {
        EditText tfNewPass = (EditText)findViewById(R.id.tf_new_password);
        EditText tfCurrPass = (EditText)findViewById(R.id.tf_current_password);
        final String newPass = tfNewPass.getText().toString();

        // TODO: check password complexity here
        if (tfNewPass.getText().toString().equals("") ||
            tfCurrPass.getText().toString().equals(""))
        {
            showMessageDiaglog("Missing required information :(");
        }
        else
        {
            progressView = ProgressDialog.show(this, null, "Resetting password");

            // Validate current password
            new mbapi.Services.ClientService.ValidateLoginRequest(mbapi.Context.Application.context().CurrentClient.Email, tfCurrPass.getText().toString())
            {
                @Override
                public void onCompletion(ClientsResult result)
                {
                    progressView.dismiss();

                    if (result.Success)
                    {
                        Client item = new Client();
                        item.FirstName = "UpdatedFirstName";
                        item.Username = mbapi.Context.Application.context().CurrentClient.Email;
                        item.Password = newPass;

                        new mbapi.Services.ClientService.UpdateClientRequest(item, mbapi.Context.Application.context().CurrentClient.ID)
                        {
                            @Override
                            public void onCompletion(ClientsResult result)
                            {
                                progressView.dismiss();

                                if (result.Success)
                                {
                                    showMessageDiaglog("Password has been reset!");
                                    finished = true;
                                }
                                else
                                {
                                    showMessageDiaglog("Error. Failed to reset password.");
                                }

                                finished = true;
                            }
                        }.execute();
                    }
                    else
                    {
                        showMessageDiaglog("Current password in invalid.");
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
