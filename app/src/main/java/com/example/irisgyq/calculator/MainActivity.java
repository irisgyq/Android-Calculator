package com.example.irisgyq.calculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import static com.example.irisgyq.calculator.Constants.inputS;
import static com.example.irisgyq.calculator.Constants.resS;
import static com.example.irisgyq.calculator.Constants.ansS;


public class MainActivity extends AppCompatActivity {

    Calculate cal = new Calculate();
    InputProcess in = new InputProcess();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void reset(View view){
        setContentView(R.layout.content_main);
        TextView input=(TextView)findViewById(R.id.inputbox);
        input.setText(R.string.reset);
        inputS = "";
    }


    public void addNumberOrOpe(View view) {
        setContentView(R.layout.content_main);
        TextView input = (TextView) findViewById(R.id.inputbox);
        switch(view.getId()){
            case R.id.leftBrace:
                inputS += "(";
                break;
            case R.id.rightBrace:
                inputS += ")";
                break;
            case R.id.mod:
                inputS += "%";
                break;
            case R.id.div:
                inputS += "/";
                break;
            case R.id.pow:
                inputS += "^";
                break;
            case R.id.sqrt:
                inputS += "√";
                break;
            case R.id.log:
                inputS += "log";
                break;
            case R.id.seven:
                inputS += "7";
                break;
            case R.id.eight:
                inputS += "8";
                break;
            case R.id.nine:
                inputS += "9";
                break;
            case R.id.mul:
                inputS += "*";
                break;
            case R.id.four:
                inputS += "4";
                break;
            case R.id.five:
                inputS += "5";
                break;
            case R.id.six:
                inputS += "6";
                break;
            case R.id.sub:
                inputS += "-";
                break;
            case R.id.one:
                inputS += "1";
                break;
            case R.id.two:
                inputS += "2";
                break;
            case R.id.three:
                inputS += "3";
                break;
            case R.id.add:
                inputS += "+";
                break;
            case R.id.comma:
                inputS += ",";
                break;
            case R.id.e:
                inputS += "e";
                break;
            case R.id.pi:
                inputS += "π";
                break;
            case R.id.point:
                inputS += ".";
                break;
            case R.id.cos:
                inputS += "cos";
                break;
            case R.id.sin:
                inputS += "sin";
                break;
            case R.id.tan:
                inputS += "tan";
                break;
            case R.id.zero:
                Constants.inputS += "0";
                break;
            case R.id.delete:
                if(Constants.inputS==null){
                    Constants.inputS = "";
                } else{
                    Constants.inputS = Constants.inputS.substring(0,inputS.length()-1);
                }
                break;
            default:
                break;
        }
        input.setText(Constants.inputS);
    }

    public void calculate(View view){
        setContentView(R.layout.content_main);
        if(in.inputValid(inputS)) {
            double resd = cal.calculate(cal.createTree(in.tokenize(inputS)));
            if(resd!=(double)Integer.MIN_VALUE){
                resS = String.valueOf(resd);
            }
        }

        TextView inputt = (TextView) findViewById(R.id.inputbox);
        TextView ress = (TextView) findViewById(R.id.resbox);
        inputt.setText(inputS);
        ress.setText(resS);

        ansS = resS;


    }

    public void history(View view){
        setContentView(R.layout.content_main);
        TextView inputt = (TextView) findViewById(R.id.inputbox);
        inputS = ansS;
        inputt.setText(inputS);
    }








}