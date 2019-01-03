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
                ViewGroup viewGroup = findViewById(R.id.toolbar_navigation_back_arrow_container);
                View view = new View(MainActivity.this);
                int size = getResources().getDimensionPixelSize(R.dimen.toolbar_navigate_back_arrow_height_and_width);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size, size);
                if (count++ > 0) {
                    int space = getResources().getDimensionPixelSize(R.dimen.toolbar_navigate_back_arrow_apace_between_arrows);
                    int margin = space - size;
                    layoutParams.setMargins(margin, 0, 0, 0);
                }
                view.setLayoutParams(layoutParams);
                view.setBackground(getDrawable(R.drawable.ic_back));
                viewGroup.addView(view);
            }
        });
    }
}
