package com.example.myapplication;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    TextView editText;
    ScrollView scroll;
    Calculator calculator=new Calculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.numberField);
        scroll=findViewById(R.id.activity_main);
    }

    public void opClick(View view) {
        Button button = (Button)view;
        if(editText.length()>50){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Хватит тыкать на кнопки!!!", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            editText.append(button.getText());
            scroll.fullScroll(ScrollView.FOCUS_DOWN);
        }
    }

    public void pointClick(View view) {
        Button button = (Button)view;
        if(editText.getText().length()==0){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Пустая строка", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            if (!calculator.isOp(editText.getText().charAt(editText.getText().length() - 1)) &&
                    editText.getText().charAt(editText.getText().length() - 1) != '.' && editText.getText().length() > 1
                    && !hasPoint(editText.getText().toString())) {
                editText.append(button.getText());
            }
        }
    }

    public boolean hasPoint(String string){
        boolean res=false;
        if(string.length()>1)
            for(int i=string.length()-1;i>0;i--){
                if(calculator.isOp(string.charAt(i))) break;
                if(string.charAt(i)=='.'){
                    res=true;
                }
            }
        return res;
    }

    public void calcClick(View view) throws Exception {
        String res="";
        String expr= String.valueOf(editText.getText());
        try {
            expr=calculator.opn(expr);
            res = Double.toString(calculator.calculate(expr));
        }
        catch (Exception ex){
            Toast toast = Toast.makeText(getApplicationContext(),
                    ex.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }
        editText.setText(res);
    }

    public void numClick(View view) {
        Button button = (Button)view;
        if(editText.length()>50){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Хватит тыкать на кнопки!!!", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            editText.append(button.getText());
            scroll.fullScroll(ScrollView.FOCUS_DOWN);
        }
    }

    public void bracketClick(View view) {
        Button button = (Button)view;
        if(editText.length()>50){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Хватит тыкать на кнопки!!!", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            editText.append(button.getText());
            scroll.fullScroll(ScrollView.FOCUS_DOWN);
        }
    }

    public void clearOneClick(View view) {
        if(editText.length()>0)
            editText.setText(editText.getText().subSequence(0,editText.getText().length()-1));
    }

    public void clearClick(View view) {

        editText.setText("");
    }


    public void divXClick(View view) {
        String res="";
        String expr= String.valueOf(editText.getText());
        try {
            expr=calculator.opn(expr);
            res = Double.toString(1/calculator.calculate(expr));
        }
        catch (Exception ex){
            Toast toast = Toast.makeText(getApplicationContext(),
                    ex.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }
        editText.setText(res);
    }

    public void minClick(View view) {
        String res="";
        String expr= String.valueOf(editText.getText());
        try {
            double x=Double.parseDouble(expr);
            res=Double.toString(-x);
        } catch (Exception e) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Выражение не является числом", Toast.LENGTH_SHORT);
            toast.show();
        }
        editText.setText(res);
    }

    public void sqrtClick(View view) {
        String res="";
        String expr= String.valueOf(editText.getText());
        try {
            double x=Double.parseDouble(expr);
            if(x>=0) {
                res = Double.toString(Math.sqrt(x));
            }
            else{
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Корень из отрицательного числа", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        catch (Exception e) {
            try {
                expr=calculator.opn(expr);
                Double num=calculator.calculate(expr);
                if(num>=0) {
                    res = Double.toString(Math.sqrt(num));
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Корень из отрицательного числа", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
            catch (Exception ex){
                Toast toast = Toast.makeText(getApplicationContext(),
                        ex.getMessage(), Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        editText.setText(res);
    }
}
