package com.topfootballtips.topfootballtip.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.topfootballtips.topfootballtip.R;
import com.topfootballtips.topfootballtip.constants.Preferences;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class GetVipActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_vip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        findViewById(R.id.btn_vip_one).setOnClickListener(this);
        findViewById(R.id.btn_vip_two).setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.btn_vip_one:
                Crouton.makeText(this, "ДАЙ VIP ИДНО", Style.ALERT, Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_vip_two:
                Crouton.makeText(this, "ДАЙ VIP ДВЯ", Style.ALERT, Toast.LENGTH_LONG).show();
                break;
            default:
                Crouton.makeText(this, "КО КЛИКНА ВА!", Style.ALERT, Toast.LENGTH_LONG).show();
                break;
        }
    }

}
