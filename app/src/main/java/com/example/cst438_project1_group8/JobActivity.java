package com.example.cst438_project1_group8;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

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

/**
 * This is an activity that allows users to look up jobs so they can find job information that match their skills and location.
 * @author Lily Joh
 * @version 1.1
 * @since 1.1
 */
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

        int currentUserId = getIntent().getIntExtra("currentUserId", -1);

        Intent intent = new Intent(JobActivity.this, JobAdapter.class);
        intent.putExtra("currentUserId", currentUserId);

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
                    // if checked
                    getLocation();
                    et_search_location.setText("");
                    location = "";
                    et_search_location.setEnabled(false);
                } else {
                    // if not checked
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

                // clear current location
                cb_current_location.setChecked(false);
                city = "";

                // clear previous job list
                jobs.clear();
                adapter = new JobAdapter(jobs, JobActivity.this);
                rv_job.setAdapter(adapter);
            }
        });
    }

    /**
     * This method makes a GET request to the endpoints and retrieves a list of job data.
     * @param term a parameter indicates the endpoint parameter "description", which is a search term, such as "ruby" or "java".
     * @param city a parameter indicates the endpoint parameter "location", which is a city name, zip code, or other location search term.
     * @param lat a parameter indicates the endpoint parameter "lat", which is a specific latitude. If used, you must also send "lon" and must not send "location".
     * @param lon a parameter indicates the endpoint parameter "long", which is a specific longitude. If used, you must also send "lat" and must not send "location".
     */
    public void callApi(String term, String city, String lat, String lon) {
        apiInterface = RetrofitInstance.getRetrofitInstance().create(JsonPlaceHolderApi.class);
        Call<List<Job>> call = apiInterface.getJobs(term, city, lat, lon);
        call.enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                jobs = response.body();
                // if results found
                if(jobs.size() != 0) {
                    adapter = new JobAdapter(jobs, JobActivity.this);
                    rv_job.setAdapter(adapter);
                }
                // if no results found
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

    /**
     * This method provides access to the system location services.
     */
    @SuppressLint("MissingPermission")
    private void getLocation() {
        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, JobActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method retrieves user's current location information such as latitude, longitude, and a city name.
     * @param currentLocation user's current location
     */
    @Override
    public void onLocationChanged(@NonNull Location currentLocation) {
        // get current latitude and longitude
        lat = String.valueOf(currentLocation.getLatitude());
        lon = String.valueOf(currentLocation.getLongitude());

        // get current city name
        try {
            Geocoder geocoder = new Geocoder(JobActivity.this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude(), 1);
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

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}