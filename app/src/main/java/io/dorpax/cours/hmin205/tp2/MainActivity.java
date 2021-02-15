package io.dorpax.cours.hmin205.tp2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager smm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        LinearLayout layout = findViewById(R.id.linear_layout);
        List<Sensor> sensors = smm.getSensorList(Sensor.TYPE_ALL);

        for (Sensor sensor : sensors) {
            TextView tv = new TextView(this);
            tv.setText(sensor.getName());
            tv.setTextSize(20);
            layout.addView(tv);
        }
    }
}
