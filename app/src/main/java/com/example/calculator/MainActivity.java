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
    boolean Mod = false, Div = false, Add = false, Sub = false, Mul = false, Deci = false;
    TextView displayText, displayHistory;
    String temp2 = "0";
    String temp = "0";
    String temp1 = "";
    long a = 0, b = 0, c = 0, i = 0, j = 0, k = 0;
    ArrayList<String> input = new ArrayList<>();
    double a1 = 0, b1 = 0, c1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mod = findViewById(R.id.mod);
        mod.setOnClickListener(this);
        div = findViewById(R.id.div);
        div.setOnClickListener(this);
        add = findViewById(R.id.add);
        add.setOnClickListener(this);
        sub = findViewById(R.id.sub);
        sub.setOnClickListener(this);
        mul = findViewById(R.id.mul);
        mul.setOnClickListener(this);
        deci = findViewById(R.id.deci);
        deci.setOnClickListener(this);
        btn0 = findViewById(R.id.btn0);
        btn0.setOnClickListener(this);
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
        btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(this);
        btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(this);
        btn6 = findViewById(R.id.btn6);
        btn6.setOnClickListener(this);
        btn7 = findViewById(R.id.btn7);
        btn7.setOnClickListener(this);
        btn8 = findViewById(R.id.btn8);
        btn8.setOnClickListener(this);
        btn9 = findViewById(R.id.btn9);
        btn9.setOnClickListener(this);
        displayText = findViewById(R.id.display);
        displayHistory = findViewById(R.id.display1);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn0) {
            store("0");
            display("0");
        } else if (id == R.id.btn1) {
            store("1");
            display("1");
        } else if (id == R.id.btn2) {
            store("2");
            display("2");
        } else if (id == R.id.btn3) {
            store("3");
            display("3");
        } else if (id == R.id.btn4) {
            store("4");
            display("4");
        } else if (id == R.id.btn5) {
            store("5");
            display("5");
        } else if (id == R.id.btn6) {
            store("6");
            display("6");
        } else if (id == R.id.btn7) {
            store("7");
            display("7");
        } else if (id == R.id.btn8) {
            store("8");
            display("8");
        } else if (id == R.id.btn9) {
            store("9");
            display("9");
        } else if (id == R.id.deci) {
            Deci = true;
            store(".");
            display(".");
        } else if (id == R.id.mod) {
            ifDisplay("%");
            Mod = true;
            input.add("%");
            check();
        } else if (id == R.id.add) {
            ifDisplay("+");
            Add = true;
            input.add("+");
            check();
        } else if (id == R.id.sub) {
            ifDisplay("-");
            Sub = true;
            input.add("-");
            check();
        } else if (id == R.id.mul) {
            ifDisplay("×");
            Mul = true;
            input.add("×");
            check();
        } else if (id == R.id.div) {
            ifDisplay("/");
            Div = true;
            input.add("/");
            check();
        }
    }

    void store(String value) {
        try {
            if (storeAtA()) {
                temp += value;
                if (!Deci) {
                    a = Long.parseLong(temp);
                } else {
                    a1 = Double.parseDouble(temp);
                }
            } else {
                temp2 += value;
                if (!Deci) {
                    b = Long.parseLong(temp2);
                } else {
                    b1 = Double.parseDouble(temp2);
                }
                calculate();
            }
            k++;
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "You have reached maximum input.", Toast.LENGTH_SHORT).show();
            if (!temp1.isEmpty()) {
                temp1 = temp1.substring(0, temp1.length() - 1);
            }
        }
    }

    private void check() {
        if (input.size() == 2) {
            input.set(0, input.get(1));
            input.remove(1);
            temp2 = "0";
        }
    }

    private void calculate() {
        if (input.isEmpty()) return;
        String op = input.get(0);

        switch (op) {
            case "+":
                if (!Deci) {
                    c = a + b;
                } else {
                    c1 = a1 + b1;
                    truncate(c1);
                }
                break;
            case "-":
                if (!Deci) {
                    c = a - b;
                } else {
                    c1 = a1 - b1;
                    truncate(c1);
                }
                break;
            case "/":
                if (!Deci) {
                    if (b != 0) c = a / b;
                } else {
                    if (b1 != 0) c1 = a1 / b1;
                }
                break;
            case "×":
                if (!Deci) {
                    c = a * b;
                } else {
                    c1 = a1 * b1;
                    truncate(c1);
                }
                break;
            case "%":
                if (!Deci) {
                    if (b != 0) c = a % b;
                } else {
                    if (b1 != 0) c1 = a1 % b1;
                }
                break;
        }

        if (!Deci) {
            temp = String.valueOf(c);
            a = c;
            b = 0;
        } else {
            temp = String.valueOf(c1);
            a1 = c1;
            b1 = 0;
        }
        displayText.setText(temp);
    }

    private void truncate(double value) {
        int decimalPoint = 2;
        value = value * Math.pow(10, decimalPoint);
        value = Math.floor(value);
        value = value / Math.pow(10, decimalPoint);
        c1 = value;
    }

    void display(String value) {
        temp1 += value;
        displayHistory.setText(temp1);
        j++;
        while (i != 0) i--;
    }

    boolean storeAtA() {
        return !(Mod || Add || Div || Mul || Sub);
    }

    void ifDisplay(String value) {
        temp1 += value;
        if (i >= 1) {
            temp1 = temp1.substring(0, temp1.length() - 2);
            temp1 += value;
        }
        displayHistory.setText(temp1);
        i++;
    }

    public void Clear(View view) {
        try {
            temp2 = "0";
            temp1 = "";
            temp = "0";
            Mod = false;
            Div = false;
            Add = false;
            Sub = false;
            Mul = false;
            Deci = false;
            displayText.setText(temp);
            displayHistory.setText("0");
            a = 0;
            b = 0;
            c = 0;
            i = 0;
            j = 0;
            k = 0;
            a1 = 0;
            b1 = 0;
            c1 = 0;
            input.clear();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Cleared!", Toast.LENGTH_SHORT).show();
        }
    }

    public void Equal(View view) {
        if (temp1.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter input.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Your output is : " + temp, Toast.LENGTH_LONG).show();
        }
    }
}
