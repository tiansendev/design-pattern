package com.ts.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CustomTextView tvDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initData();
        
    }

    private void initData() {
        Person.Builder builder = new Person.Builder();

        Person person = builder.setAge(12)
                .setName("ts")
                .setHeight(23)
                .setWeight(32)
                .build();

    }

    private void initViews() {
        tvDemo = (CustomTextView) findViewById(R.id.tv_demo);
    }
}
