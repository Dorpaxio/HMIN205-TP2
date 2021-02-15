package io.dorpax.cours.hmin205.tp2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private Sensor accelerometer;
    private SensorManager sensorManager;
    private View layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }

        layout = findViewById(R.id.constraint);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0],
                y = event.values[1],
                z = event.values[2];

        float avg = (x + y + z) / 3;

        ((TextView) findViewById(R.id.textView3)).setText("X:" + x + " Y:" + y + " Z:" + z);

        if (avg > 0) {
            layout.setBackgroundColor(getResources().getColor(R.color.colorSuccess, null));
        } else if (avg == 0) {
            layout.setBackgroundColor(getResources().getColor(R.color.colorBlack, null));
        } else {
            layout.setBackgroundColor(getResources().getColor(R.color.colorDanger, null));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
