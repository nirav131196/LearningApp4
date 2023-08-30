package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class CalculatorActivity2 extends AppCompatActivity {


    TextView solution_tv,result_tv;
    MaterialButton button_c,button_open_bracket,button_close_bracket,button_divide,button_7,button_8,button_9,button_multiply,button_4,button_5,button_6,button_add;
    MaterialButton button_1,button_2,button_3,button_sub,button_ac,button_zero,button_dot,button_equals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator2);

        initView();

    }

    private void initView() {

        solution_tv = findViewById(R.id.solution_tv);
        result_tv = findViewById(R.id.result_tv);

/*

        button_c = findViewById(R.id.button_c);
        button_open_bracket = findViewById(R.id.button_open_bracket);
        button_close_bracket = findViewById(R.id.button_close_bracket);
        button_divide = findViewById(R.id.button_divide);
        button_7 = findViewById(R.id.button_7);
        button_8 = findViewById(R.id.button_8);
        button_9 = findViewById(R.id.button_9);
        button_multiply = findViewById(R.id.button_multiply);
        button_4 = findViewById(R.id.button_4);
        button_5 = findViewById(R.id.button_5);
        button_6 = findViewById(R.id.button_6);
        button_add = findViewById(R.id.button_add);
        button_1 = findViewById(R.id.button_1);
        button_2 = findViewById(R.id.button_2);
        button_3 = findViewById(R.id.button_3);
        button_sub = findViewById(R.id.button_sub);
        button_ac = findViewById(R.id.button_ac);
        button_zero = findViewById(R.id.button_zero);
        button_dot = findViewById(R.id.button_dot);
        button_equals = findViewById(R.id.button_equals);
*/

        assignId(button_c,R.id.button_c);
        assignId(button_open_bracket,R.id.button_open_bracket);
        assignId(button_close_bracket,R.id.button_close_bracket);
        assignId(button_divide,R.id.button_divide);
        assignId(button_7,R.id.button_7);
        assignId(button_8,R.id.button_8);
        assignId(button_9,R.id.button_9);
        assignId(button_multiply,R.id.button_multiply);
        assignId(button_4,R.id.button_4);
        assignId(button_5,R.id.button_5);
        assignId(button_6,R.id.button_6);
        assignId(button_add,R.id.button_add);
        assignId(button_1,R.id.button_1);
        assignId(button_2,R.id.button_2);
        assignId(button_3,R.id.button_3);
        assignId(button_sub,R.id.button_sub);
        assignId(button_ac,R.id.button_ac);
        assignId(button_zero,R.id.button_zero);
        assignId(button_dot,R.id.button_dot);
        assignId(button_equals,R.id.button_equals);



    }
    void assignId(MaterialButton btn, int id)
    {
        btn  =findViewById(id);
        btn.setOnClickListener(this::onClick);
    }
    public void onClick(View view){
            MaterialButton button = (MaterialButton) view;
            String buttontext = button.getText().toString();
            String datatoCalculate = solution_tv.getText().toString();

            if(buttontext.equals("AC"))
            {
                solution_tv.setText("");
                result_tv.setText("0");
                return;
            }
            if(buttontext.equals("="))
            {
                solution_tv.setText(result_tv.getText());
                return;
            }
            if(buttontext.equals("C")){
                if(datatoCalculate.length() != 0)
                {
                    if(datatoCalculate.length() == 1)
                    {
                        datatoCalculate = datatoCalculate.substring(0,datatoCalculate.length() - 1);
                        solution_tv.setText("");
                        result_tv.setText("0");
                    }
                    else
                    {
                        datatoCalculate = datatoCalculate.substring(0,datatoCalculate.length() - 1);
                    }

                }
                else
                {
                    Toast.makeText(this, "All Clear", Toast.LENGTH_SHORT).show();
                }
                 
            }
            else
            {
                datatoCalculate = datatoCalculate + buttontext;
            }
            solution_tv.setText(datatoCalculate);

            String finalResult = getResult(datatoCalculate);
            if(!finalResult.equals("Err"))
            {
                result_tv.setText(finalResult);
            }
    }
    String getResult(String data)
    {
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();

            if(finalResult.endsWith(".0")){
                finalResult  = finalResult.replace(".0","");
            }
            return finalResult;
        }
        catch (Exception ex)
        {
            return "Err";
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}