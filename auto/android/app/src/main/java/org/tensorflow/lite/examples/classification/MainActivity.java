package org.tensorflow.lite.examples.classification;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button bt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt = findViewById(R.id.button);

        bt.setOnClickListener(v->{
            Intent intent = new Intent(this, ClassifierActivity.class);

            startActivity(intent);
            finish();

        });



    }
}
