package com.example.cst438_project1_group8;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobActivity extends AppCompatActivity implements LocationListener {

    private RecyclerView rv_job;
    private List<Job>jobs;
    private JsonPlaceHolderApi apiInterface;
    private JobAdapter adapter;

    LocationManager locationManager;
    TextView tv_search_conditions;

    String term = "";
    String location = "";
    String lat = "";
    String lon = "";
    String city = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

        rv_job = (RecyclerView)findViewById(R.id.rv_job);
        rv_job.setHasFixedSize(true);
        rv_job.setLayoutManager(new LinearLayoutManager(this));

        EditText et_search_term = findViewById(R.id.et_search_term);
        EditText et_search_location = findViewById(R.id.et_search_location);
        Button bt_search = findViewById(R.id.bt_search);
        tv_search_conditions = findViewById(R.id.tv_search_conditions);
        TextView tv_reset_search = findViewById(R.id.tv_reset_search);
        CheckBox cb_current_location = findViewById(R.id.cb_current_location);

        // to get user's current location
        if(ContextCompat.checkSelfPermission(JobActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(JobActivity.this, new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }

        // current location check box
        cb_current_location.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    // checked
                    getLocation();
                    et_search_location.setText("");
                    location = "";
                    et_search_location.setEnabled(false);
                } else {
                    // not checked
                    lat = "";
                    lon = "";
                    city = "";
                    et_search_location.setEnabled(true);
                }
            }
        });

        // search results
        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                term = String.valueOf(et_search_term.getText());
                location = String.valueOf(et_search_location.getText());

                callApi(term, location, lat, lon);

                String conditions = "Jobs searched by: ";
                if(!term.equals("")) {
                    conditions += term + " + ";
                }
                if(!location.equals("")) {
                    conditions += location + " + ";
                }
                if(!lat.equals("") || !lon.equals("")) {
                    conditions += city + " + ";
                }

                if(!term.equals("") || !location.equals("") || !city.equals("")) {
                    conditions = conditions.substring(0, conditions.length() - 3);
                }

                tv_search_conditions.setText(conditions);
            }
        });

        // reset search conditions
        tv_reset_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // reset keyword editText
                term = "";
                et_search_term.setText("");
                et_search_term.clearFocus();

                // reset location editText
                location = "";
                et_search_location.setText("");
                et_search_location.clearFocus();

                // reset search conditions textView
                tv_search_conditions.setText("");

                // reset current location checkBox
                cb_current_location.setChecked(false);

                // clear previous job list
                jobs.clear();
                adapter = new JobAdapter(jobs, JobActivity.this);
                rv_job.setAdapter(adapter);
            }
        });
    }

    // retrieve job data from API
    public void callApi(String term, String city, String lat, String lon) {
        apiInterface = RetrofitInstance.getRetrofitInstance().create(JsonPlaceHolderApi.class);
        Call<List<Job>> call = apiInterface.getJobs(term, city, lat, lon);
        call.enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                jobs = response.body();
                // results found
                if(jobs.size() != 0) {
                    adapter = new JobAdapter(jobs, JobActivity.this);
                    rv_job.setAdapter(adapter);
                }
                // no results found
                else {
                    tv_search_conditions.setText("We couldn't find any results. Please try another search.");
                    adapter = new JobAdapter(jobs, JobActivity.this);
                    rv_job.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {

            }
        });
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, JobActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        lat = String.valueOf(location.getLatitude());
        lon = String.valueOf(location.getLongitude());

        // get current city name
        try {
            Geocoder geocoder = new Geocoder(JobActivity.this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            String address = addresses.get(0).getAddressLine(0);

            List<String> retvals = new ArrayList<String>();
            for (String retval: address.split(", ")) {
                retvals.add(retval);
            }

            city = retvals.get(retvals.size() - 3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}