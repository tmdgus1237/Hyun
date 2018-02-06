package com.mybluemix.bluemixb.random_grid_layout;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final int NUM_OF_BUTTONS = 9;
    private final int MAX_SCORE = 20;
    private TextView tv_score;
    private TextView tv_combo;
    private Button btn_exit;
    private Button btn_reset;
    private Button[] Buttons;
    GridLayout container;
    private int score = 0, combo = 0, clickNum = 1, count = 0, reset;
    private Context c = this;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void random_buttons(){
        reset=0;
        count = 0;
        clickNum = 1;
        int[] check = new int[NUM_OF_BUTTONS];
        for(int i=0;i<NUM_OF_BUTTONS;i++){
            int n;
            Random random = new Random();

            while(true){
                n = random.nextInt(NUM_OF_BUTTONS);

                if(check[n] != 1){
                    break;
                }
            }
            Buttons[n].setText(""+(i+1));
            check[n] = 1;
            //Buttons[n].setBackgroundColor(Color.parseColor("#FFC19E"));
            Buttons[n].setBackground(ContextCompat.getDrawable(this, R.drawable.button_shape));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_score = (TextView)findViewById(R.id.tv_score);
        tv_combo = (TextView)findViewById(R.id.tv_combo);
        btn_reset = (Button)findViewById(R.id.btn_reset);
        btn_exit=(Button)findViewById(R.id.btn_exit);
        container = (GridLayout)findViewById(R.id.btn_container);

        Buttons = new Button[NUM_OF_BUTTONS];

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score -= reset;
                tv_score.setText("SCORE: "+score);
                random_buttons();
            }
        });

        for (int i = 0 ; i<NUM_OF_BUTTONS; i++){
            Buttons[i] = new Button(this);
            container.addView(Buttons[i]);
            Buttons[i].setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(View view) {
                    Button b = (Button)view;
                    int num = Integer.parseInt(b.getText().toString());

                    count++;

                    if(clickNum != num) {
                        score -= 50;
                        reset-=50;
                        tv_score.setText("SCORE: "+(score));
                    }
                    else {
                        //b.setBackgroundColor(Color.parseColor("#FFA7A7"));
                        b.setBackground(ContextCompat.getDrawable(c, R.drawable.button_click));
                        score += 10;
                        reset+=10;
                        clickNum++;
                        tv_score.setText("SCORE: "+(score));
                        if (clickNum > 9) {
                            if(count == 9){
                                combo++;
                                score += 100;
                                tv_score.setText("SCORE: "+(score));
                                tv_combo.setText("COMBO: "+(combo));
                            }
                            random_buttons();
                        }
                    }

                }
            });
        }
        random_buttons();
    }
}