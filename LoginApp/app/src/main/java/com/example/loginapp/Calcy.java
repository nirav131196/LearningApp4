package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Context;

public class Calcy extends AppCompatActivity implements View.OnClickListener {

    Button btnOne, btnTwo, btnThree, btnPlus, btnFour, btnFive, btnSix, btnMinus, btnSeven, btnEight, btnNine, btnMultiply, btnZero, btnEnter, btnClear, btnDivision;

    TextView txtSolution, txtResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcy);


        txtResult = findViewById(R.id.txtResult);
        txtSolution = findViewById(R.id.txtSolution);

        assignView(btnOne,R.id.btnOne);
        assignView(btnTwo,R.id.btnTwo);
        assignView(btnThree,R.id.btnThree);
        assignView(btnPlus,R.id.btnPlus);
        assignView(btnFour,R.id.btnFour);
        assignView(btnFive,R.id.btnFive);
        assignView(btnSix,R.id.btnSix);
        assignView(btnMinus,R.id.btnMinus);
        assignView(btnSeven,R.id.btnSeven);
        assignView(btnEight,R.id.btnEight);
        assignView(btnNine,R.id.btnNine);
        assignView(btnMultiply,R.id.btnMultiply);
        assignView(btnZero,R.id.btnZero);
        assignView(btnEnter,R.id.btnEnter);
        assignView(btnClear,R.id.btnClear);
        assignView(btnDivision,R.id.btnDivision);
    }
    public void assignView(Button btn,int id)
    {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String buttontext = button.getText().toString();
        String dataToCalculate = txtSolution.getText().toString();


        if(buttontext.equals("="))
        {
            txtSolution.setText(txtResult.getText());
            return;
        }
        else if(buttontext.equals("Clear"))
        {
            txtSolution.setText("");
            txtResult.setText("0");
            return;
        }
        else
        {
            dataToCalculate = dataToCalculate + buttontext;
        }
        txtSolution.setText(dataToCalculate);

        String finalresult = getResult(dataToCalculate);
        if(!finalresult.equals("Error"))
        {
            txtResult.setText(finalresult);
        }
        else
        {
            Log.e("Error","Error");
        }
    }
    public String getResult(String data)
    {
        try
        {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalstring = context.evaluateString(scriptable,data,"Javascript",1,null).toString();

            return  finalstring;
        }
        catch (Exception ex)
        {
            return "Error";
        }
    }
}