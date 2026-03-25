package com.example.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button mod, div, add, sub, mul, deci, btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    boolean Mod, Div, Add, Sub, Mul, Deci = false;
    TextView dispText, dispText1;
    String temp2 = String.valueOf(0);   String temp = String.valueOf(0);    String temp1 = "";
    long a, b, c, i, j, k = 0;
    ArrayList<String> input = new ArrayList<>();
    double a1, b1, c1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//      calling onClick() method
        mod = findViewById(R.id.mod);       mod.setOnClickListener(this);
        div = findViewById(R.id.div);       div.setOnClickListener(this);
        add = findViewById(R.id.add);       add.setOnClickListener(this);
        sub = findViewById(R.id.sub);       sub.setOnClickListener(this);
        mul = findViewById(R.id.mul);       mul.setOnClickListener(this);
        deci = findViewById(R.id.deci);     deci.setOnClickListener(this);
        btn0 = findViewById(R.id.btn0);     btn0.setOnClickListener(this);
        btn1 = findViewById(R.id.btn1);     btn1.setOnClickListener(this);
        btn2 = findViewById(R.id.btn2);     btn2.setOnClickListener(this);
        btn3 = findViewById(R.id.btn3);     btn3.setOnClickListener(this);
        btn4 = findViewById(R.id.btn4);     btn4.setOnClickListener(this);
        btn5 = findViewById(R.id.btn5);     btn5.setOnClickListener(this);
        btn6 = findViewById(R.id.btn6);     btn6.setOnClickListener(this);
        btn7 = findViewById(R.id.btn7);     btn7.setOnClickListener(this);
        btn8 = findViewById(R.id.btn8);     btn8.setOnClickListener(this);
        btn9 = findViewById(R.id.btn9);     btn9.setOnClickListener(this);
        dispText = findViewById(R.id.display);  dispText1 = findViewById(R.id.display1); }

    @Override
    public void onClick(View view) {
//        Giving jobs to every Buttons.
        switch (view.getId()) {
            case R.id.btn0:    store("0");      display("0");         break;
            case R.id.btn1:    store("1");      display("1");         break;
            case R.id.btn2:    store("2");      display("2");         break;
            case R.id.btn3:    store("3");      display("3");         break;
            case R.id.btn4:    store("4");      display("4");         break;
            case R.id.btn5:    store("5");      display("5");         break;
            case R.id.btn6:    store("6");      display("6");         break;
            case R.id.btn7:    store("7");      display("7");         break;
            case R.id.btn8:    store("8");      display("8");         break;
            case R.id.btn9:    store("9");      display("9");         break;
            case R.id.deci:    Deci = true;     store(".");             display(".");   break;
            case R.id.mod:     ifDisplay("%");  Mod = true;    input.add("%");    check();   break;
            case R.id.add:     ifDisplay("+");  Add = true;    input.add("+");    check();   break;
            case R.id.sub:     ifDisplay("-");  Sub = true;    input.add("-");    check();   break;
            case R.id.mul:     ifDisplay("×");  Mul = true;    input.add("×");    check();   break;
            case R.id.div:     ifDisplay("/");  Div = true;    input.add("/");    check();   break; } }
//      Storing user inputs in variables.
    void store(String disp) {
       try {
           if(!Deci) {
               if (storeAtA()) {
                   temp += disp;
                   a = Long.parseLong(temp);
                   k++; } else {
                   temp2 += disp;
                   b = Long.parseLong(temp2);
                   calculate();
                   k++; } } else {
               if (storeAtA()) {
                   temp += disp;
                   a1 = Double.parseDouble(temp);
                   k++; } else {
                   temp2 += disp;
                   b1 = Double.parseDouble(temp2);
                   calculate();
                   k++; } }
       } catch (Exception e) {
           Toast.makeText(getApplicationContext(),"You have reached maximum input.", Toast.LENGTH_SHORT).show();
           temp1 = temp1.substring(0, temp1.length()-1); } }
//  Managing user given Arithmetic Operators in an ArrayList so calculate() can access it accordingly.
    private void check() {
        if (input.size() == 2) {
            input.set(0, input.get(1)); input.remove(1); temp2 = String.valueOf(0); } }
//      All calculations are done here.
    private void calculate() {
//        calculate addition.
        if (input.get(0) == "+") {
            if(!Deci) {
                c = a + b;
                temp = String.valueOf(c); dispText.setText(temp);
            } else {
               c1 = a1 + b1; truncate(c1, 2);
                temp = String.valueOf(c1); dispText.setText(temp); }
            a1 = c1; b1 = 0;
            a = c; b = 0; }
//        calculate subtraction.
        if (input.get(0) == "-") {
            if(!Deci) {
                c = a - b;
                temp = String.valueOf(c); dispText.setText(temp);
            } else {
                c1 = a1 - b1; truncate(c1, 2);
                temp = String.valueOf(c1); dispText.setText(temp); }
            a1 = c1; b1 = 0;
            a = c; b = 0; }
//      calculate divisons.
        if (input.get(0) == "/") {
            if(!Deci) {
                if (a > 0 && b > 0) c = a / b;
                temp = String.valueOf(c); dispText.setText(temp);
            } else {
                if(a1 > 0 && b1 > 0) c1 = a1 / b1;
                temp = String.valueOf(c1); dispText.setText(temp); }
            a1 = c1; b1 = 0;
            a = c; b = 0; }
//      calculate multiplications.
        if (input.get(0) == "×") {
            if(!Deci) {
                c = a * b;
                temp = String.valueOf(c); dispText.setText(temp);
            } else {
                c1 = a1 * b1; truncate(c1, 2);
                temp = String.valueOf(c1); dispText.setText(temp); }
            a1 = c1; b1 = 0;
            a = c; b = 0; }
//      calculate modulus.
        if (input.get(0) == "%") {
            if(!Deci) {
                c = a % b;
                temp = String.valueOf(c); dispText.setText(temp);
            } else {
                c1 = a1 % b1;
                temp = String.valueOf(c1); dispText.setText(temp); }
            a1 = c1; b1 = 0;
            a = c; b = 0; } }
//      Truncatanation of big numbers of values of c1 for + and -.
    private void truncate(double value, int decimelPoint) {
        value = value * Math.pow(10, decimelPoint);
        value = Math.floor(value);
        value = value/Math.pow(10, decimelPoint);
        c1 = value; }
    //  Display for 2nd textview in the results box at top.
    void display(String disp) {
        temp1 += disp; dispText1.setText(temp1); j++;
        while (i != 0) i--; }
//  Check if user has clicked any Arithmetic operator. If yes then now store user inputs in variable b.
    boolean storeAtA() {
        if (Mod || Add || Div || Mul || Sub) return false;
        return true; }
//  Arithmetic buttons if clicked then redirected to this function first.
//  It checks if user has already called this function before. If yes it removes the last element
//  and replaces it with new arithmetic operator given by user.
    void ifDisplay(String disp) {
        temp1 += disp;
        if (i >= 1) {
            temp1 = temp1.substring(0, temp1.length() - 2); temp1 += disp; }
        dispText1.setText(temp1);
        i++; }
//      If user clicked Clear button.
    public void Clear(View view) {
        try {
        temp2 = String.valueOf(0); temp1 = ""; temp = String.valueOf(0);
        Mod = false; Div = false; Add = false; Sub = false; Mul = false; Deci = false;
        dispText.setText(temp); dispText1.setText("0");
        a = 0; b = 0; c = 0; i = 0; j = 0; k = 0;
        a1 = 0; b1 = 0; c1 = 0;
        input.remove(0);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Cleared!", Toast.LENGTH_SHORT).show(); } }
//      If user clicked Equal button.
    public void Equal(View view) {
        if(temp1 == "")
            Toast.makeText(getApplicationContext(),"Please enter input. ", Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),"Your output is : " + temp, Toast.LENGTH_LONG).show(); }

}
