package com.topfootballtips.topfootballtip.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.topfootballtips.topfootballtip.R;
import com.topfootballtips.topfootballtip.api.ApiService;
import com.topfootballtips.topfootballtip.api.api_models.TipParseObject;
import com.topfootballtips.topfootballtip.constants.Preferences;
import com.topfootballtips.topfootballtip.constants.RequestParameters;
import com.topfootballtips.topfootballtip.ui.adapters.TipAdapter;
import com.topfootballtips.topfootballtip.ui.fragments.MatchesListFragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //shows Please wait
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle(getString(R.string.loading_screen_title));
        progress.setMessage(getString(R.string.loading_screen_text));
        progress.setProgressStyle(android.R.attr.progressBarStyleInverse);
        progress.setCancelable(false);
        progress.setIcon(R.mipmap.ic_launcher);
        progress.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Preferences.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApiService = retrofit.create(ApiService.class);
        Call<TipParseObject> match_tip_call = mApiService.getAllTMatchTips();

        match_tip_call.enqueue(new Callback<TipParseObject>() {
            @Override
            public void onResponse(Call<TipParseObject> call, Response<TipParseObject> response) {
                MatchesListFragment fragment = new MatchesListFragment();
                TipAdapter adapter = new TipAdapter(MainActivity.this, R.layout.match_tip_item, response.body().getData());
                fragment.setListAdapter(adapter);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.list_fragment_holder, fragment)
                        .commitAllowingStateLoss();

                progress.dismiss();
            }

            @Override
            public void onFailure(Call<TipParseObject> call, Throwable t) {
                progress.dismiss();
                showCrouton("Match loading failed. Please reload!", Style.ALERT);
            }
        });

        //todo check if the device is registered
        Backendless.initApp( getBaseContext(),
                RequestParameters.APP_ID,
                RequestParameters.SECRET_KEY,
                RequestParameters.MESSAGING_VERSION);

        Backendless.Messaging.registerDevice(RequestParameters.SENDER_ID, RequestParameters.CHANNEL_NAME);


        //set OnClickListeners on main buttons
        findViewById(R.id.btn_free_bet).setOnClickListener(this);
        findViewById(R.id.btn_vip_bet).setOnClickListener(this);
        findViewById(R.id.btn_risk_bet).setOnClickListener(this);
        findViewById(R.id.btn_danger_matches).setOnClickListener(this);
        findViewById(R.id.btn_get_vip).setOnClickListener(this);
        findViewById(R.id.btn_rate_us).setOnClickListener(this);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //No call for super(). Bug on API Level > 11.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //todo remove this shit
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Crouton.cancelAllCroutons();
    }

    private void showCrouton(String message, Style style) {
        Crouton.makeText(this, message, style, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_free_bet) {
            showCrouton("ДАЙ FREE TIP Б\'А СТАСЧО!", Style.ALERT);
        } else if (id == R.id.btn_vip_bet) {
            showCrouton("ДАЙ VIP TIP Б\'А СТАСЧО!", Style.ALERT);
        } else if (id == R.id.btn_risk_bet) {
            showCrouton("ДАЙ RISK TIP Б\'А СТАСЧО!", Style.ALERT);
        } else if (id == R.id.btn_danger_matches) {
            showCrouton("ДАЙ DANGER MATCHES Б\'А СТАСЧО!", Style.ALERT);
        } else if (id == R.id.btn_rate_us) {
            showCrouton("ДАЙ ОЦЕНЧИСА У ГОШУТУ Б\'А СТАСЧО!", Style.ALERT);
        } else if (id == R.id.btn_get_vip) {
            showCrouton("НАПРАЙ МА VIP Б\'А СТАСЧО!", Style.ALERT);
        } else {
            showCrouton("КО КЛИКНА ЛА!", Style.ALERT);
        }
    }
}

