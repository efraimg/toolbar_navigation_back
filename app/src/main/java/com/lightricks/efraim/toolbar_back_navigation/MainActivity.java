package com.lightricks.efraim.toolbar_back_navigation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToolbarNavigationBackView toolbarNavigationBackView = findViewById(R.id.back);
                toolbarNavigationBackView.setNumOfArrows(toolbarNavigationBackView.getNumOfArrows() + 1);
            }
        });
        findViewById(R.id.button_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToolbarNavigationBackView toolbarNavigationBackView = findViewById(R.id.back);
                toolbarNavigationBackView.setNumOfArrows(toolbarNavigationBackView.getNumOfArrows() - 1);
            }
        });
    }
}
