package com.example.user.afinal;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.net.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.regex.Pattern;
import android.net.NetworkInfo.*;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    Button b1;
    Button b2;
    Button b3;
    TextView t1;
    TextView t2;
    TextView t3;
    String IPaddress;
    Boolean IPValue;
    static final Pattern PATTERN = Pattern.compile(
            "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
    static final Pattern PATTERN2 = Pattern.compile("^((([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})|" +
            "(([0-9A-Fa-f]{1,4}:){6}:[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){5}:([0-9A-Fa-f]{1,4}:)?[0-9A-Fa-f]{1,4})|" +
            "(([0-9A-Fa-f]{1,4}:){4}:([0-9A-Fa-f]{1,4}:){0,2}[0-9A-Fa-f]{1,4})|" +
            "(([0-9A-Fa-f]{1,4}:){3}:([0-9A-Fa-f]{1,4}:){0,3}[0-9A-Fa-f]{1,4})|" +
            "(([0-9A-Fa-f]{1,4}:){2}:([0-9A-Fa-f]{1,4}:){0,4}[0-9A-Fa-f]{1,4})|" +
            "(([0-9A-Fa-f]{1,4}:){6}((\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|" +
            "(\\d{1,2}))\\b)\\.){3}(\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b))|" +
            "(([0-9A-Fa-f]{1,4}:){0,5}:((\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|" +
            "(\\d{1,2}))\\b)\\.){3}(\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b))|" +
            "(::([0-9A-Fa-f]{1,4}:){0,5}((\\b((25[0-5])|(1\\d{2})|" +
            "(2[0-4]\\d)|(\\d{1,2}))\\b)\\.){3}(\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|" +
            "(\\d{1,2}))\\b))|([0-9A-Fa-f]{1,4}::([0-9A-Fa-f]{1,4}:){0,5}[0-9A-Fa-f]{1,4})|" +
            "(::([0-9A-Fa-f]{1,4}:){0,6}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){1,7}:))$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        t1=(TextView)findViewById(R.id.textView);
        t2=(TextView)findViewById(R.id.textView2);
        t3=(TextView)findViewById(R.id.textView3);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                NetwordDetect();
                if (IPaddress.equals("")){
                    t1.setText("Currently You are not connected to any network!!\nNo Internet connection.");
                    t2.setText("");
                }else {
                    if (validate(IPaddress) ){
                        String new_ipaddress = IPaddress;
                        String[] arr=NewActivity.new_split(new_ipaddress);
                        int l =  arr.length;
                        int[] ints=new int[arr.length];
                        for(int i=0;i<arr.length;i++)
                        {
                            ints[i]=Integer.parseInt(arr[i]);

                        }
                        int set_cidr=NewActivity.setCidr(ints);
                        IPv4 ipv4 = new IPv4(new_ipaddress+"/"+Integer.toString(set_cidr));
                        Log.d("before check","before check");
                        try {
                            if (isNetworkAvailable()){
                                Log.d("before check","before check");
                                t1.setText("IP Address is "+IPaddress);
                            }else{
                                Log.d("before check","before check");
                                t1.setText("Oops..!!!There is No Internet Connection" + "\n"+"IP Address is "+IPaddress);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            t1.setText("Exceptionm :::: Oops..!!!There is No Internet Connection" + "\n"+"IP Address is "+IPaddress);
                        }

                        //t2.setText("Sub Net Mask:" + ipv4.getNetmask() +"\n"+"BroadCast Address:"+ipv4.getBroadcastAddress()+ "\n");
                        t2.setText("Sub Net Mask:" + ipv4.getNetmask() + "\n"+"Broadcast Address :"+ipv4.getBroadcastAddress()+"\n"+"Classs of the Address : "+NewActivity.findClass(ints)+"\n");
                    }else if (validate2(IPaddress)) {
                        String new_ipaddress = IPaddress+"/"+64;
                        IPv6Network strangeNetwork = IPv6Network.fromString(new_ipaddress);
                        try {
                            if (isNetworkAvailable()){
                                t1.setText("IP Address is "+IPaddress);
                            }else{
                                t1.setText("Oops..!!!There is No Internet Connection" + "\n"+"IP Address is "+IPaddress);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            t1.setText("Oops..!!!There is No Internet Connection" + "\n"+"IP Address is "+IPaddress);
                        }

                        t2.setText("Sub Net Mask:" + strangeNetwork.getNetmask().asAddress() + "\n");

                    }
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this,
                        NewActivity.class);
                startActivity(myIntent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=8zEVA-Bxs-0")));
                Log.i("Video","Video Playing");
            }
        });
    }
    private void NetwordDetect() {
        boolean WIFI = false;
        boolean MOBILE = false;
        ConnectivityManager CM = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfo = CM.getAllNetworkInfo();
        for (NetworkInfo netInfo : networkInfo) {
            if (netInfo.getTypeName().equalsIgnoreCase("WIFI"))
                if (netInfo.isConnected())
                    WIFI = true;
            if (netInfo.getTypeName().equalsIgnoreCase("MOBILE"))
                if (netInfo.isConnected())
                    MOBILE = true;
        }
        if(WIFI == true) {
            IPaddress = GetDeviceipWiFiData();
        }
        if(MOBILE == true) {
            IPaddress = GetDeviceipMobileData();
        }
        if (WIFI== false && MOBILE== false){
            IPaddress = "";
        }
    }
    public String GetDeviceipMobileData(){
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
                 en.hasMoreElements();) {
                NetworkInterface networkinterface = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = networkinterface.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception ex) {
            Log.e("Current IP", ex.toString());
        }
        return null;
    }
    public boolean isNetworkAvailable() throws IOException {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
            Log.d("before check","4");
            if(isOnline()){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
    public String GetDeviceipWiFiData() {
        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        @SuppressWarnings("deprecation")
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        return ip;
    }
    public static boolean validate( String ip) {
        return PATTERN.matcher(ip).matches();
    }
    public static boolean validate2( String ip){
        return PATTERN2.matcher(ip).matches();
    }
    public Boolean isOnline() {
        try {
            Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
            Log.d("before check","2");
            int returnVal = p1.waitFor();
            Log.d("before check wait for",Integer.toString(p1.waitFor()));
            boolean reachable = (returnVal==0);
            return reachable;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean connectGoogle() {
        try {
            HttpURLConnection urlc = (HttpURLConnection)(new URL("http://www.google.com").openConnection());
            urlc.setRequestProperty("User-Agent", "Test");
            urlc.setRequestProperty("Connection", "close");
            urlc.setConnectTimeout(10000);
            urlc.connect();
            return (urlc.getResponseCode() == 200);

        } catch (IOException e) {
            return false;
        }
    }
}