## Mindbody-API-Wrapper
Mindbody API Wrapper for mobile. Now only available for Android. Comming up soon for iOS.

## Features

- Support class + appointment booking workflows.
- Client account creation. View and update client profile with ease.
- Provide application context for your app.
- Purchase through account credit, giftcard, and credit card (not tested)

## Techinical aspects

- There are requests ready for you. It takes care of object mapping for you. All you do is make the call.
- Uses blocks. Keep your code organize.
- There's a demo project.
- Built off system library. No other dependencies.

## Android
- Requires Android Studio for the demo project
- Requires SDK version 23
- Import the mbapi folder and you're all set.

Example:
```Java
// Initialize credentials once in the MainActivity.java
new SourceCredentials("MySourceName", "Password", "-99");
new UserCredentials("OwnerUsername", "Password");

// Client login
new mbapi.Services.ClientService.ValidateLoginRequest("ClientUserName", "ClientPassword")
{
    @Override
    public void onCompletion(ClientsResult result)
    {
        if (result.Success)
        {
            Client clt = result.Clients.get(0);
            // Load client profile
            loadClientProfile(clt.ID);
        }
        else
        {
            showMessageDiaglog("Username or password is invalid");
        }
    }
}.execute();
```

For more examples, check out the demo project

## License
- Use the code in a moral way.
- This software is as is, so use it at your own risk.
- The creator is not reponsible for any lose.

