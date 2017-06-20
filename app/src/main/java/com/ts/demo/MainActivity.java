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
        Person person = builder.age(12)
                .name("ts")
                .height(23)
                .weight(32)
                .build();
    }

    private void initViews() {
        tvDemo = (CustomTextView) findViewById(R.id.tv_demo);
    }
}
