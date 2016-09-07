package com.topfootballtips.topfootballtip.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.topfootballtips.topfootballtip.R;
import com.topfootballtips.topfootballtip.api.api_models.BestTip;
import com.topfootballtips.topfootballtip.constants.Preferences;
import com.topfootballtips.topfootballtip.constants.RequestParameters;
import com.topfootballtips.topfootballtip.ui.adapters.TipAdapter;
import com.topfootballtips.topfootballtip.ui.fragments.MatchesListFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String REQUEST_STRING = "match_date at or after \'%1$s\' AND match_date before \'%2$s\' AND tip_type=%3$d";
    private TextView textField;
    private int mCurrentType;
    private Button mFreeBet;
    private Button mVipBet;
    private Button mRiskBet;
    private Button mDangerBet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        long startOfTheDay = this.getStartOfDay();

        loadMatches(Preferences.TIP_TYPE_FREE, startOfTheDay);


        //todo check if the device is registered
        Backendless.Messaging.registerDevice(RequestParameters.SENDER_ID, RequestParameters.CHANNEL_NAME);

        //if no data this wont be hidden
        textField = (TextView) findViewById(R.id.tw_empty_body);
        textField.setVisibility(View.GONE);

        //set OnClickListeners on main buttons
        mFreeBet = (Button) findViewById(R.id.btn_free_bet);
        mVipBet = (Button) findViewById(R.id.btn_vip_bet);
        mRiskBet = (Button) findViewById(R.id.btn_risk_bet);
        mDangerBet =  (Button) findViewById(R.id.btn_danger_matches);


        mFreeBet.setOnClickListener(this);
        mVipBet.setOnClickListener(this);
        mRiskBet.setOnClickListener(this);
        mDangerBet.setOnClickListener(this);
        findViewById(R.id.btn_get_vip).setOnClickListener(this);
        findViewById(R.id.btn_rate_us).setOnClickListener(this);

    }

    private void loadMatches(final int tipTye, long startOfTheDay) {
        final ProgressDialog progressDialog = getProgressDialog();
        progressDialog.show();
        mCurrentType = tipTye;
        if(textField != null){
            textField.setVisibility(View.GONE);
            textField.setText(getString(R.string.there_is_noting_to_show));
            textField.setTextColor(Color.BLACK);
        }


        String whereClause = String.format(
                Locale.getDefault(),
                REQUEST_STRING,
                startOfTheDay, (startOfTheDay + 86400000), tipTye);//one day  is 86 400 000 milliseconds

        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        dataQuery.setPageSize(100);
        dataQuery.setWhereClause( whereClause );



        Backendless.Persistence.of( RequestParameters.TABLE_NAME )
                .find( dataQuery, new AsyncCallback<BackendlessCollection<Map>>() {

                    @Override
                    public void handleResponse(BackendlessCollection<Map> mapBackendlessCollection) {
                        List<BestTip> listResults = new ArrayList<>();
                        List<Map> map = mapBackendlessCollection.getData();

                        for (Map tipMap : map) {
                            Log.d("TFTDebug",tipMap.toString());
                            listResults.add(castToTip(tipMap));
                        }

                        MatchesListFragment fragment = new MatchesListFragment();
                        TipAdapter adapter = new TipAdapter(MainActivity.this, R.layout.match_tip_item, listResults);
                        fragment.setListAdapter(adapter);
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.list_fragment_holder, fragment)
                                .commitAllowingStateLoss();

                        if(listResults.isEmpty()){
                            textField.setVisibility(View.VISIBLE);
                        }

                        toggleButtons(tipTye);
                        progressDialog.dismiss();
                    }

                    @Override
                    public void handleFault(BackendlessFault backendlessFault) {
                        progressDialog.dismiss();
                        textField.setVisibility(View.VISIBLE);
                        textField.setText(R.string.error_loading);
                        textField.setTextColor(Color.RED);
                        Log.d("TFTdebug", getString(R.string.error_occurred) + backendlessFault.getMessage());
                    }

                    private void toggleButtons(int tipTye) {
                        switch (tipTye){
                            case Preferences.TIP_TYPE_FREE:
                                mFreeBet.setPressed(true);
                                break;
                            case Preferences.TIP_TYPE_VIP:
                                mVipBet.setPressed(true);
                                break;
                            case Preferences.TIP_TYPE_RISK:
                                mRiskBet.setPressed(true);
                                break;
                            case Preferences.TIP_TYPE_DANGER:
                                mDangerBet.setPressed(true);
                                break;
                            default:
                                throw new UnsupportedOperationException("No such tiptype as :"+tipTye);
                        }
                    }
                });
    }

    private ProgressDialog getProgressDialog() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle(getString(R.string.loading_screen_title));
        dialog.setMessage(getString(R.string.loading_screen_text));
        dialog.setProgressStyle(android.R.attr.progressBarStyleInverse);
        dialog.setCancelable(false);
        dialog.setIcon(R.mipmap.ic_launcher);

        return dialog;
    }

    private BestTip castToTip(Map map) {
        SimpleDateFormat sdf = new SimpleDateFormat(Preferences.UI_DATE_FORMAT, Locale.getDefault());
        BestTip bestTip = new BestTip();
        bestTip.setTip((String) map.get("tip"));
        bestTip.setMatchDate(sdf.format((Date) map.get("match_date")));
        bestTip.setCoef((String)map.get("coeficient"));
        bestTip.setUpdated(((Date)(map.get("updated"))));
        bestTip.setTipType((int) map.get("tip_type"));
        bestTip.setCountry((String) map.get("country"));
        bestTip.setMeta((String) map.get("__meta"));
        bestTip.setComments((String)map.get("comments"));
        bestTip.setClass_((String)map.get("___class"));
        bestTip.setID((int) map.get("id"));
        bestTip.setOwnerId((String) map.get("ownerId"));
        bestTip.setMatch((String) map.get("match"));
        bestTip.setResult((String) map.get("result"));
        bestTip.setCreated(((Date)(map.get("created"))));
        bestTip.setTipResult((int) map.get("tip_result"));
        bestTip.setObjectId((String)map.get("objectId"));

        return bestTip;
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
        if (id == R.id.action_reload) {
            loadMatches(mCurrentType, getStartOfDay());
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onStop() {
        super.onStop();
        Crouton.cancelAllCroutons();
    }



    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.btn_free_bet:
                loadMatches(Preferences.TIP_TYPE_FREE, getStartOfDay());
                break;
            case R.id.btn_vip_bet:
                loadMatches(Preferences.TIP_TYPE_VIP, getStartOfDay());
                break;
            case R.id.btn_risk_bet:
                loadMatches(Preferences.TIP_TYPE_RISK, getStartOfDay());
                break;
            case R.id.btn_danger_matches:
                loadMatches(Preferences.TIP_TYPE_DANGER, getStartOfDay());
                break;
            case R.id.btn_rate_us:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(Preferences.RATE_SITE));
                if (browserIntent.resolveActivity(getPackageManager()) != null) {
                    view.getContext().startActivity(browserIntent);
                } else {
                    Toast.makeText(this, R.string.no_browser_text, Toast.LENGTH_LONG).show();
                }
                return;
            case R.id.btn_get_vip:
                startActivity(new Intent(this, GetVipActivity.class));
                break;
            default:
                showCrouton("КО КЛИКНА ВА!", Style.ALERT);
                break;
        }
    }

    private Long getStartOfDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0); //set hours to zero
        calendar.set(Calendar.MINUTE, 0); // set minutes to zero
        calendar.set(Calendar.SECOND, 0); //set seconds to zero
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime().getTime();
    }

    void showCrouton(String message, Style style) {
        Crouton.makeText(this, message, style, Toast.LENGTH_LONG).show();
    }
}

