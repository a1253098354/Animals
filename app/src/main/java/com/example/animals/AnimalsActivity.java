package com.example.animals;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class AnimalsActivity extends AppCompatActivity {
   private ImageView img_animal0,img_animal1,img_animal2,img_animal3;
   private TextView tv_animal;
   private  int score_for_this_time=0;
   private int current=0;
   private int right_answer=-1;
   private int user_answer=-1;
   private SharedPreferences sp;
   private SharedPreferences.Editor editor;
   private String[] animals={"bear","bird","cat","elephant","fish","giraffe","honey","hypo","kangaroo","leo","lion","pig","rhino","tiger","wolf"};
   private int[] animals_R_id ={ R.drawable.bearartboard, R.drawable.birdartboard, R.drawable.catartboard,R.drawable.elephantartboard,R.drawable.fishartboard,R.drawable.giraffeartboard,
   R.drawable.honeyartboard,R.drawable.hypoartboard,R.drawable.kangarooartboard,R.drawable.leoartboard,R.drawable.lionartboard,R.drawable.pigartboardi,R.drawable.rhinoartboard,
   R.drawable.tigerartboard,R.drawable.wolfartboard};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals);
        tv_animal=findViewById(R.id.tv_animal);
        sp=getSharedPreferences("information",MODE_PRIVATE);
        editor=sp.edit();
        (img_animal0=findViewById(R.id.img_animal0)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_answer=0;
                img_onclick(v);
            }
        });
        (img_animal1=findViewById(R.id.img_animal1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_answer=1;
                img_onclick(v);
            }
        });
        (img_animal2=findViewById(R.id.img_animal2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_answer=2;
                img_onclick(v);
            }
        });
        (img_animal3=findViewById(R.id.img_animal3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_answer=3;
                img_onclick(v);
            }
        });
        Bundle bundle=new Bundle();
        bundle.putInt("score_for_this_time",0);
        Intent intent=new Intent();
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK,intent);
        switch_to_next();
    }

    public void img_onclick(View v)
    {
        if(user_answer==right_answer)
        {
            set_sp(true);
            score_for_this_time++;
            Bundle bundle=new Bundle();
            bundle.putInt("score_for_this_time",score_for_this_time);
            Intent intent=new Intent();
            intent.putExtras(bundle);
            setResult(Activity.RESULT_OK,intent);
            Toast.makeText(getApplicationContext(),"Yes,it's "+animals[current]+" ,+1 score",Toast.LENGTH_SHORT).show();
        }
        else
        {
            set_sp(false);
            Toast.makeText(getApplicationContext(),"No,it's not "+animals[current],Toast.LENGTH_SHORT).show();
        }
        switch_to_next();
    }

    public void switch_to_next()
    {
        Random rand=new Random();
        current=rand.nextInt(15);
        right_answer=rand.nextInt(4);
        tv_animal.setText(animals[current]);
        switch (right_answer)
        {
            case 0:
                img_animal0.setImageResource(animals_R_id[current]);
                break;
            case 1:
                img_animal1.setImageResource(animals_R_id[current]);
                break;
            case 2:
                img_animal2.setImageResource(animals_R_id[current]);
                break;
            case 3:
                img_animal3.setImageResource(animals_R_id[current]);
                break;
        }
        for(int i=0;i<4;i++)
        {
            int j=-1;
            while((j=rand.nextInt(15))==current);
            if(i!=right_answer)
            {
                switch (i){
                    case 0:
                        img_animal0.setImageResource(animals_R_id[j]);
                        break;
                    case 1:
                        img_animal1.setImageResource(animals_R_id[j]);
                        break;
                    case 2:
                        img_animal2.setImageResource(animals_R_id[j]);
                        break;
                    case 3:
                        img_animal3.setImageResource(animals_R_id[j]);
                        break;
                }
            }
        }
    }

    public int get_sp(String s)
    {
        if(s.equals("total_score"))
            return sp.getInt("total_score",-1);
        else
            return sp.getInt("score",-1);

    }
    public void set_sp(boolean b)
    {
        if(b)
        {
            editor.putInt("total_score", get_sp("total_score") + 1);
            editor.putInt("score",get_sp("score")+1);
        }
        else
        {
            editor.putInt("total_score", get_sp("total_score") + 1);
        }
        editor.apply();
    }
}
