//package com.example.user.afinal;

/**
 * Created by BHARATH P on 12/14/2017.
// */
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.net.Uri;
//import android.net.wifi.WifiManager;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.text.format.Formatter;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
////import android.widget.EditText;
//import android.widget.RadioButton;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.net.InetAddress;
//import java.net.NetworkInterface;
//import java.util.Enumeration;
//import java.util.StringTokenizer;
//import java.util.regex.Pattern;
//
//public class validation {
//    public  static final String IP_REGEX = "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
//    public  static final String CIDR_REGEX = "^[0-9]{2}$";
//
//
//    public static final String REQUIRED_MSG = "required";
//    public static final String IP_MSG = "invalid ip";
//    public static final String CIDR_MSG = "###-#######";
//
//    public static boolean isIPAddress(EditText editText, boolean required) {
//        return isValid(editText, IP_REGEX, IP_MSG, required);
//    }
//
//    public static boolean isCIDRNumber(EditText editText, boolean required) {
//        return isValid(editText, CIDR_REGEX, CIDR_MSG, required);
//    }
//
//    public static boolean isValid(EditText editText, String regex, String errMsg, boolean required) {
//
//        String text = editText.getText().toString().trim();
//        editText.setError(null);
//
//        if ( required && !hasText(editText) ) return false;
//
//        if (required && !Pattern.matches(regex, text)) {
//            editText.setError(errMsg);
//            return false;
//        };
//
//        return true;
//    }
//
//    public static boolean hasText(EditText editText) {
//
//        String text = editText.getText().toString().trim();
//        editText.setError(null);
//
//        if (text.length() == 0) {
//            editText.setError(REQUIRED_MSG);
//            return false;
//        }
//
//        return true;
//    }
//}


//
//
//
//
//package com.example.user.afinal;
//
//        import android.content.Context;
//        import android.content.Intent;
//        import android.net.ConnectivityManager;
//        import android.net.NetworkInfo;
//        import android.net.wifi.WifiManager;
//        import android.support.v7.app.AppCompatActivity;
//        import android.os.Bundle;
//        import android.text.format.Formatter;
//        import android.util.Log;
//        import android.view.Menu;
//        import android.view.View;
//        import android.widget.Button;
//        import android.widget.TextView;
//        import android.net.*;
//        import java.net.InetAddress;
//        import java.net.NetworkInterface;
//        import java.util.Enumeration;
//public class MainActivity extends AppCompatActivity {
//    Button b1;
//    Button b2;
//    Button b3;
//    TextView t1;
//    TextView t2;
//    TextView t3;
//    String IPaddress;
//    Boolean IPValue;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        b1=(Button)findViewById(R.id.button);
//        b2=(Button)findViewById(R.id.button2);
//        b3=(Button)findViewById(R.id.button3);
//        t1=(TextView)findViewById(R.id.textView);
//        t2=(TextView)findViewById(R.id.textView2);
//        t3=(TextView)findViewById(R.id.textView3);
//
//        b1.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//
//                NetwordDetect();
//
//            }
//        });
//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent myIntent = new Intent(MainActivity.this,
//                        NewActivity.class);
//                startActivity(myIntent);
//
//            }
//        });
//        b3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=8zEVA-Bxs-0")));
//                Log.i("Video","Video Playing");
//            }
//        });
//
//    }
//
//
//    //Check the internet connection.
//    private void NetwordDetect() {
//
//        boolean WIFI = false;
//
//        boolean MOBILE = false;
//
//        ConnectivityManager CM = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        NetworkInfo[] networkInfo = CM.getAllNetworkInfo();
//
//        for (NetworkInfo netInfo : networkInfo) {
//
//            if (netInfo.getTypeName().equalsIgnoreCase("WIFI"))
//
//                if (netInfo.isConnected())
//
//                    WIFI = true;
//
//            if (netInfo.getTypeName().equalsIgnoreCase("MOBILE"))
//
//                if (netInfo.isConnected())
//
//                    MOBILE = true;
//        }
//
//        if(WIFI == true)
//
//        {
//            IPaddress = GetDeviceipWiFiData();
//            t1.setText(IPaddress);
//
//
//
//        }
//
//        if(MOBILE == true)
//        {
//
//            IPaddress = GetDeviceipMobileData();
//            t1.setText(IPaddress);
//
//        }
//
//    }
//
//
//    public String GetDeviceipMobileData(){
//        try {
//            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
//                 en.hasMoreElements();) {
//                NetworkInterface networkinterface = en.nextElement();
//                for (Enumeration<InetAddress> enumIpAddr = networkinterface.getInetAddresses(); enumIpAddr.hasMoreElements();) {
//                    InetAddress inetAddress = enumIpAddr.nextElement();
//                    if (!inetAddress.isLoopbackAddress()) {
//                        return inetAddress.getHostAddress().toString();
//                    }
//                }
//            }
//        } catch (Exception ex) {
//            Log.e("Current IP", ex.toString());
//        }
//        return null;
//    }
//
//    public String GetDeviceipWiFiData()
//    {
//
//        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
//
//        @SuppressWarnings("deprecation")
//
//        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
//
//        return ip;
//
//    }

//    public String findClass(int sbnm1[])
//    {
//        String cla1="";
//        if(sbnm1[0]>=1&&sbnm1[0]<=168)
//        {
//            cla1="A";
//        }
//        if(sbnm1[0]>=128&&sbnm1[0]<=191)
//        {
//            cla1="B";
//        }
//        if(sbnm1[0]>=192&&sbnm1[0]<=223)
//        {
//            cla1="C";
//        }
//        if(sbnm1[0]>=224&&sbnm1[0]<=239)
//        {
//            cla1="D";
//        }
//        return cla1;
//
//    }
//    public String findSubNetMask(int sbnm[])
//    {
//        String sbn="";
//        if(sbnm[0]>=1&&sbnm[0]<=126)
//        {
//            sbn="255.0.0.0";
//        }
//        if(sbnm[0]>=128&&sbnm[0]<=191)
//        {
//            sbn="255.255.0.0";
//        }
//        if(sbnm[0]>=192&&sbnm[0]<=223)
//        {
//            sbn="255.255.255.0";
//        }
//        if(sbnm[0]>=224&&sbnm[0]<=239)
//        {
//            sbn="Reserved for multicasting";
//        }
//        return sbn;
//
//    }

//}


//
//
//package com.example.user.afinal;
//
//        import android.app.Activity;
//        import android.content.Context;
//        import android.net.DhcpInfo;
//        import android.net.wifi.WifiManager;
//        import android.os.Bundle;
//        import android.text.Editable;
//        import android.text.TextWatcher;
//        import android.util.Log;
//        import android.view.View;
//        import android.widget.Button;
//        import android.widget.EditText;
//        import android.widget.RadioButton;
//        import android.widget.TextView;
//        import android.widget.Toast;
//        import java.util.regex.Pattern;
//        import java.lang.Object;
//        import java.util.StringTokenizer;
///**
// * Created by user on 12/13/2017.
// */
//
//public class NewActivity extends Activity {
//    RadioButton r1;
//    RadioButton r2;
//    Button b4;
//    TextView t4;
//    EditText e5;
//    EditText e6;
//    String new_ipaddress;
//
//    static final Pattern PATTERN = Pattern.compile(
//            "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
//    static final Pattern PATTERN2 = Pattern.compile("^((([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})|" +
//            "(([0-9A-Fa-f]{1,4}:){6}:[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){5}:([0-9A-Fa-f]{1,4}:)?[0-9A-Fa-f]{1,4})|" +
//            "(([0-9A-Fa-f]{1,4}:){4}:([0-9A-Fa-f]{1,4}:){0,2}[0-9A-Fa-f]{1,4})|" +
//            "(([0-9A-Fa-f]{1,4}:){3}:([0-9A-Fa-f]{1,4}:){0,3}[0-9A-Fa-f]{1,4})|" +
//            "(([0-9A-Fa-f]{1,4}:){2}:([0-9A-Fa-f]{1,4}:){0,4}[0-9A-Fa-f]{1,4})|" +
//            "(([0-9A-Fa-f]{1,4}:){6}((\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|" +
//            "(\\d{1,2}))\\b)\\.){3}(\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b))|" +
//            "(([0-9A-Fa-f]{1,4}:){0,5}:((\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|" +
//            "(\\d{1,2}))\\b)\\.){3}(\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b))|" +
//            "(::([0-9A-Fa-f]{1,4}:){0,5}((\\b((25[0-5])|(1\\d{2})|" +
//            "(2[0-4]\\d)|(\\d{1,2}))\\b)\\.){3}(\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|" +
//            "(\\d{1,2}))\\b))|([0-9A-Fa-f]{1,4}::([0-9A-Fa-f]{1,4}:){0,5}[0-9A-Fa-f]{1,4})|" +
//            "(::([0-9A-Fa-f]{1,4}:){0,6}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){1,7}:))$");
//
//    //   Button b5;
//
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.new_activity);
//        b4=(Button)findViewById(R.id.button4);
//        //  b5=(Button)findViewById(R.id.button5);
//        e5=(EditText)findViewById(R.id.editText);
//        e6=(EditText)findViewById(R.id.editText2);
//        t4=(TextView)findViewById(R.id.textView4);
//        r1=(RadioButton)findViewById(R.id.radioButton);//ipv4
//        r2=(RadioButton)findViewById(R.id.radioButton2);
////        e5.addTextChangedListener(new TextWatcher() {
////            @Override
////            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
////
////            }
////
////            @Override
////            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
////
////            }
////
////            @Override
////            public void afterTextChanged(Editable editable) {
////                validation.isIPAddress(e5, true);
////
////            }
////        });
////        e6.addTextChangedListener(new TextWatcher() {
////            @Override
////            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
////
////
////            }
////
////            @Override
////            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
////
////            }
////
////            @Override
////            public void afterTextChanged(Editable editable) {
////                validation.isCIDRNumber(e6,false);
////            }
////        });
//        b4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("enter","entered inside onclick");
//                if (r1.isChecked())
//                {
////                    if ( checkValidation () )
////                        submitValidate();
////                    else
////                        Toast.makeText(NewActivity.this, "Input contains error", Toast.LENGTH_LONG).show();
//
//                    new_ipaddress=e5.getText().toString();
//
//
//                    Log.d("enter","entered inside onclick inside radio of ipv4");
//                    String[] arr=new_split(new_ipaddress);
//                    int l =  arr.length;
//                    Log.d("enter",Integer.toString(l));
//                    //Log.d("enter", );
//                    int[] ints=new int[arr.length];
//                    Log.d("enter","entered inside onclick inside radio of ipv4      2");
//                    for(int i=0;i<arr.length;i++)
//                    {
//                        ints[i]=Integer.parseInt(arr[i]);
//
//                    }
//                    Log.d("enter","entered inside onclick inside radio of ipv4       3");
//                    //int k=Integer.parseInt(e6.getText().toString());
//                    Log.d("ipv4s",e6.getText().toString());
//                    if((e6.getText().toString()).equals(""))
//                    {
//                        Log.d("enter","in e6(if)");
//                        int set_cidr=setCidr(ints);
//                        boolean x=  validate(e5.getText().toString());
//                        String sb=findSubNetMask(ints);
//                        String clas=findClass(ints);
//                        if(arr[0]=="0"||arr[0]=="127"||arr[0]=="255")
//                        {
//                            x=false;
//                        }
//                        if(x==true) {
//                            if (ints[0] >= 224 && ints[0] <= 239) {
//
//                                Toast.makeText(NewActivity.this, "Reserved for Multicasting", Toast.LENGTH_SHORT).show();
//                            } else if (ints[0] >= 240 && ints[0] <= 254) {
//                                Toast.makeText(NewActivity.this, "Experimental.. Used for Research", Toast.LENGTH_SHORT).show();
//                            } else if (ints[0] == 10) {
//                                String sbn1111 = "255.0.0.0";
//                                Toast.makeText(NewActivity.this, "Private Network", Toast.LENGTH_SHORT).show();
//                                t4.setText("Sub Net Mask:" + sbn1111 + "\n");
//                            } else if (ints[0] == 172 && ints[1] >= 16 && ints[1] <= 31) {
//                                String sbn1111 = "255.240.0.0";
//                                Toast.makeText(NewActivity.this, "Private Network", Toast.LENGTH_SHORT).show();
//                                t4.setText("Sub Net Mask:" + sbn1111 + "\n");
//                            } else if (ints[0] == 192 && ints[1] == 168) {
//                                String sbn1111 = "255.255.0.0";
//                                Toast.makeText(NewActivity.this, "Private Network", Toast.LENGTH_SHORT).show();
//                                t4.setText("Sub Net Mask:" + sbn1111 + "\n");
//                            } else {
//                                String subNetMask = findSubNetMask(ints);
//                                String classOf = findClass(ints);
//                                Log.d("myTag1", "This is my message");
//                                Toast.makeText(NewActivity.this, "IP IS VALID", Toast.LENGTH_SHORT).show();
//                                t4.setText("Network Info\n" + subNetMask + "\n" + classOf + "\n");
//
//                            }
//                        }
//
//                    }
//                    else{
//                        if((Integer.parseInt(e6.getText().toString()) < 10 || (Integer.parseInt(e6.getText().toString())) >30)){
//                            Toast.makeText(NewActivity.this, "Please enter beteen 10-30", Toast.LENGTH_SHORT).show();
//
//                        }else{
//                            IPv4 ipv4 = new IPv4(new_ipaddress+"/"+e6.getText().toString());
//                            t4.setText("Sub Net Mask:" + ipv4.getNetmask() + "\n");
//                            Toast.makeText(NewActivity.this, "Correct cidr", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//
//
//                }
//                else if(r2.isChecked()) {
//                    if ((e6.getText().toString()).equals("")) {
//                        boolean y = validate2(e5.getText().toString());
//                        if (y == true) {
//                            new_ipaddress = e5.getText().toString()+"/"+64;
//                            IPv6Network strangeNetwork = IPv6Network.fromString(new_ipaddress);
//                            Toast.makeText(NewActivity.this, "Given IPV6 Address is valid", Toast.LENGTH_SHORT).show();
//                            t4.setText("Sub Net Mask:" + strangeNetwork.getNetmask().asAddress() + "\n");
//
//                        } else {
//                            Toast.makeText(NewActivity.this, "Given IPV6 Address is not valid", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    else {
//                        if((Integer.parseInt(e6.getText().toString()) <  64)) {
//                            t4.setText("Please enter cidr greater than 64\n");
//                            Toast.makeText(NewActivity.this, "Please enter greater than 64", Toast.LENGTH_SHORT).show();
//                        }
//                        else{
//                            new_ipaddress = e5.getText().toString()+"/"+Integer.parseInt(e6.getText().toString());
//                            IPv6Network strangeNetwork = IPv6Network.fromString(new_ipaddress);
//                            t4.setText("Sub Net Mask:" + strangeNetwork.getNetmask().asAddress() + "\n");
//                            Toast.makeText(NewActivity.this, "Correct cidr", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                }
//            }
//        });
//
//
//    }
//
//    public static String[] new_split(String ipaddress_1)
//    {
//
//        String temp=ipaddress_1;
//        int j=0;
//        String ipadd[]=new String[4];
//        String delims=".";
//        StringTokenizer st=new StringTokenizer(temp,delims);
//        while(st.hasMoreTokens())
//        {
//            ipadd[j]=st.nextToken();
//            j++;
//        }
//        return ipadd;
//
//    }
//    public static int setCidr(int sbnm[])
//    {
//        int cidrr=0;
//        if(sbnm[0]>=1&&sbnm[0]<=168)
//        {
//            cidrr=8;
//        }
//        if(sbnm[0]>=128&&sbnm[0]<=191)
//        {
//            cidrr=16;
//        }
//        if(sbnm[0]>=192&&sbnm[0]<=223)
//        {
//            cidrr=24;
//        }
//        if(sbnm[0]>=224&&sbnm[0]<=239)
//        {
//            //show address cannot be displayed
//        }
//        return cidrr;
//    }
//    public static boolean validate( String ip) {
//        return PATTERN.matcher(ip).matches();
//    }
//    public static boolean validate2( String ip){return PATTERN2.matcher(ip).matches();}
//    public String findSubNetMask(int sbnm[])
//    {
//        String sbn="";
//        if(sbnm[0]>=1&&sbnm[0]<=126)
//        {
//            sbn="255.0.0.0";
//        }
//        if(sbnm[0]>=128&&sbnm[0]<=191)
//        {
//            sbn="255.255.0.0";
//        }
//        if(sbnm[0]>=192&&sbnm[0]<=223)
//        {
//            sbn="255.255.255.0";
//        }
//        if(sbnm[0]>=224&&sbnm[0]<=239)
//        {
//            sbn="Reserved for multicasting";
//        }
//        return sbn;
//
//    }
//    public String findClass(int sbnm1[])
//    {
//        String cla1="";
//        if(sbnm1[0]>=1&&sbnm1[0]<=168)
//        {
//            cla1="A";
//        }
//        if(sbnm1[0]>=128&&sbnm1[0]<=191)
//        {
//            cla1="B";
//        }
//        if(sbnm1[0]>=192&&sbnm1[0]<=223)
//        {
//            cla1="C";
//        }
//        if(sbnm1[0]>=224&&sbnm1[0]<=239)
//        {
//            cla1="D";
//        }
//        return cla1;
//
//    }
////    private void submitValidate() {
////        // Submit your form here. your form is valid
////        Toast.makeText(this, "", Toast.LENGTH_LONG).show();
////    }
////
////    private boolean checkValidation() {
////        boolean ret = true;
////        if (!validation.isIPAddress(e5, true)) ret = false;
////        if (!validation.isCIDRNumber(e6, false)) ret = false;
////
////        return ret;
////    }
//
//}















