package com.example.sotiks;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


public class AccountActivity extends AppCompatActivity {
    public static final int PERMS_REQUEST_CODE = 1;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    public String[] Lists;

    public ArrayAdapter<String> adapter;
    private ListView listView;
    private TextView googleResult ;
    private TextView mailResult ;
    private TextView vkResult ;
    private TextView whatsAppResult ;
    private TextView viberResult ;
    private TextView otherResult ;
    AccountManager mAccountManager;

    private static final int PERMISSIONS_REQUEST_GET_ACCOUNTS = 111;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        }




    @RequiresApi(api = Build.VERSION_CODES.M)
    public void test(View view) {
        googleResult= findViewById(R.id.googleResult);
        mailResult= findViewById(R.id.mailResult);
        vkResult= findViewById(R.id.vkResult);
        viberResult= findViewById(R.id.viberResult);
        whatsAppResult= findViewById(R.id.whatsAppResult);
        otherResult= findViewById(R.id.otherResult);


        if (checkSelfPermission(android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED
                ||
                (ContextCompat.checkSelfPermission(AccountActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        )
        {
            requestPermissions(new String[]{android.Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        AccountManager am = AccountManager.get(this);
        Account[] googleAccounts = am.getAccountsByType("com.google");
        Account[] mailAccounts = am.getAccountsByType("ru.mail");
        Account[] vkAccounts = am.getAccountsByType("com.vkontakte.account");
        Account[] legacyAccounts = am.getAccountsByType("com.google.android.gm.legacyimap");
        Account[] viberAccounts = am.getAccountsByType("com.viber.voip");
        Account[] whatsappAccounts = am.getAccountsByType("com.whatsapp");




        searchAccounts(googleResult,googleAccounts);
        searchAccounts(mailResult,mailAccounts);
        searchAccounts(vkResult,vkAccounts);
        searchAccounts(viberResult,viberAccounts);
        searchAccounts(whatsAppResult,whatsappAccounts);
        searchAccounts(otherResult,legacyAccounts);


    }


    private void searchAccounts(TextView result,Account[] accounts){
        if (accounts.length>0){
            result.setText("");
            for(Account account: accounts)
            {
                Log.i("acss", String.valueOf(account.name));
                result.append(String.valueOf(account.name)+"\n");
            }
        }
    }
    }