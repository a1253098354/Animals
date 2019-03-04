package com.example.animals;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btn_play,btn_exit;
    private TextView tv_welcome;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_play=findViewById(R.id.btn_play);
        btn_exit=findViewById(R.id.btn_exit);
        tv_welcome=findViewById(R.id.tv_welcome);
        sp=getSharedPreferences("information",MODE_PRIVATE);
        editor=sp.edit();
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AnimalsActivity.class);
                startActivityForResult(intent,0);
            }
        });
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if(get_sp("total_score")==-1)
        {
            tv_welcome.setText("Welcome to animals game!");
            editor.putInt("total_score",0);
            editor.putInt("score",0);
            editor.apply();
        }
        else
        {
            tv_welcome.setText("Welcome back!\n You've got "+get_sp("score")+" scores.");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        tv_welcome.setText("Welcome back!\n You've got "+get_sp("score")+" scores.");
        Toast.makeText(getApplicationContext(),"You got "+data.getExtras().getInt("score_for_this_time",0)+" scores this time.",Toast.LENGTH_LONG).show();
    }
    public int get_sp(String s)
    {
        if(s.equals("total_score"))
            return sp.getInt("total_score",-1);
        else
            return sp.getInt("score",-1);
    }
}
