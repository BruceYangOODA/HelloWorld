package com.not_a_name.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

public class ThirdActivity extends AppCompatActivity {

    private String answer ;
    private int dig = 3;
    private EditText input;
    private TextView log;
    private int counter;
    private int guessMax=3;
    private long clickTime=0;
    private String[] items = {"3","4","5","6"};
    private String selectedItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        answer = createAnswer(dig);
        input = findViewById(R.id.etGuessInput);
        log = findViewById(R.id.tvGuessContent);
       // clickTime=System.currentTimeMillis();

        Button btnGuess = findViewById(R.id.btnGuess);
        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strInput = input.getText().toString();
                if(strInput.length()==answer.length())
                {
                    String result = checkAB(strInput);
                    log.append(counter+": " +strInput + "=>" + result+"\n");
                    input.setText("");
                    counter++;
                    if(result.equals(dig+"A0B"))
                    {
                        showDialog(true);
                    }
                    else if(counter==guessMax)
                    {
                        showDialog(false);
                        counter++;
                    }
                }
                else
                {
                    Toast.makeText(getBaseContext(),"Wrong Input",Toast.LENGTH_SHORT).show();
                    counter++;
                }
            }
        });

        Button btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(exitClick);

        Button btnSetting = findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp;
                AlertDialog adl = new AlertDialog.Builder(v.getContext())
                        .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which1) {
                                Log.v("Not_A_Name","which1=" +which1);
                                selectedItem = items[which1];
                            }
                        })
                        /*  //點擊立即觸發
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.v("Not_A_Name","which=" +which);
                            }
                        })*/
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which2) {
                                dig = Integer.parseInt(selectedItem);
                                newGame();
                                Log.v("Not_A_Name","selectedItem=" +selectedItem);
                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .create();
                adl.show();
            }
        });
    }

    private View.OnClickListener exitClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog adl = new AlertDialog.Builder(getBaseContext())
                    .setMessage("Exit?")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setPositiveButton("NO", null)
                    .create();
            adl.show();
        }
    };

    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis() - clickTime>3*1000)
        {
            clickTime = System.currentTimeMillis();
            Toast.makeText(this,"point backKey one more time",Toast.LENGTH_SHORT).show();
        }
        else
        {
            super.onBackPressed();
        }
        Log.v("Not_A_Name","onBackPressed");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("Not_A_Name","onDestroy");
    }

    @Override
    public void finish() {
        super.finish();
        Log.v("Not_A_Name","finish");
    }

    private void showDialog(boolean isWinner)
    {
        AlertDialog adl = new AlertDialog.Builder(this)
                .setTitle(isWinner?"WINNER":"LOSER")
                .setMessage(isWinner?"恭喜老爺":"謎底為"+answer)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        newGame();
                    }
                })
                .create();
        adl.show();
    }

    private void newGame()
    {
        counter=0;
        answer = createAnswer(dig);
        input.setText("");
        log.setText("");
    }

    private String checkAB(String guess)
    {
        int a,b ;
        a=b=0;
        for(int i=0;i<guess.length();i++)
        {
            if(guess.charAt(i)==answer.charAt(i))
            {  a++;}
            else if(answer.indexOf(guess.charAt(i))>=0){  b++;}
        }
        return a+"A"+b+"B";
    }

    private String createAnswer(int dig)
    {
        LinkedList<Integer> list = new LinkedList<>();
        for(int i=0;i<10;i++)
            list.add(i);
        Collections.shuffle(list);
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<dig;i++)
        {
            sb.append(list.get(i));
        }
        Log.v("Not_A_Name",sb.toString());
        /*
        HashSet<Integer> set = new HashSet<>();
        while (set.size()<dig)
        {
            set.add((int)(Math.random()*10));
        }
        StringBuffer sb = new StringBuffer();
        for(Integer i : set)
        {
            sb.append(i);
        }
        */
        return sb.toString();
    }

}
