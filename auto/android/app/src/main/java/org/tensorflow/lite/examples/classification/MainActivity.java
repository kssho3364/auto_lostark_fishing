package org.tensorflow.lite.examples.classification;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    Button bt;

    private static final int REQEST_ENABLE_LOCATION = 10;
    private static final int REQUEST_ENABLE_BT = 3;
    private static final int PERMISSIONS_REQUEST_RESULT = 1;

    private BluetoothAdapter bluetoothAdapter;
    private LocationManager locationManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt = findViewById(R.id.start_bt);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_RESULT);

        if (bluetoothAdapter == null) {
            Toast.makeText(this,"블루투스를 지원하지 않는 기기입니다,",Toast.LENGTH_SHORT).show();
            finish();
        }

        bt.setOnClickListener(v->{
            setDevice();
        });
    }

    public void setDevice() {
        //블루투스를 켜지 않았다면 실행
        if (!bluetoothAdapter.isEnabled()) {
            Log.d("탔냐","탔어?");
            if(!bluetoothAdapter.isEnabled()){
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);//빨간줄 개무시
            }
        }
        //위치를 켜지 않았다면 실행
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("위치 서비스를 켜시겠습니까?");
            builder.setPositiveButton("예", (dialog, which) -> {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(intent,REQEST_ENABLE_LOCATION);
            });
            builder.setNegativeButton("아니오",(dialog, which) -> {
                Toast.makeText(getApplicationContext(),"위치 서비스를 켜야합니다.",Toast.LENGTH_SHORT).show();
            });
            builder.create().show();
        }
        //위치와 블루투스를 모두 켰다면 실행
        if (bluetoothAdapter.isEnabled() && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Intent intent = new Intent(getApplicationContext(), ClassifierActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
