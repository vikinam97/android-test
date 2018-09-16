package com.example.vignesh.myapplication;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    RequestQueue request;
    Button lgn;
    String url;
    EditText user;
    EditText pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=findViewById(R.id.username);
        pass=findViewById(R.id.password);
        lgn=(Button)findViewById(R.id.login);
        request= Volley.newRequestQueue(this);
        //url="http://www.tcsportal.com/approval-desk-test/profile_img.php?profile_img=94095";
        url="http://www.tcsportal.com/approval-desk-test/viki/android/attn.php?viki=hi";
        lgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getcall();
                postcall();

            }
        });
        mTextMessage = (TextView) findViewById(R.id.message);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };
    void getcall()
    {   url="http://www.tcsportal.com/approval-desk-test/viki/android/attn.php?username="+user.getText().toString().trim()+"&password="+pass.getText().toString().trim();
        String response=new String("");
        StringRequest str=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("mainactivity",response.toString());
                if(response.equalsIgnoreCase("1"))
                {
                    Toast.makeText(getApplicationContext(),"Login Succesfull",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Invalid username | password !",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("mainactivity",error.toString());
                Toast.makeText(getApplicationContext(),"Unable to login ! please try again later.",Toast.LENGTH_SHORT).show();
            }
        });
        request.add(str);
    }
    void postcall()
    {   url="http://www.tcsportal.com/approval-desk-test/viki/android/attn.php";
        StringRequest str=new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                Log.d("mainactivity",response.toString());
                if(response.equalsIgnoreCase("1"))
                {
                    Toast.makeText(getApplicationContext(),"Login Succesfull",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Invalid username | password !",Toast.LENGTH_SHORT).show();
                }
            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("mainactivity",error.toString());
                Toast.makeText(getApplicationContext(),"Unable to login ! please try again later.",Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("username",user.getText().toString().trim());
                params.put("password",pass.getText().toString().trim());
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        request.add(str);
    }
}
