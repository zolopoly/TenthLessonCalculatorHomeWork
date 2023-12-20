package com.msaggik.tenthlessoncalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {

    // поля
    private EditText input;
    private Button btnAdd, btnSub, btnMul, btnDiv, btnRem;

    private Calculation calculationAdd;
    private Calculation calculationSub;
    private Calculation calculationMul;
    private Calculation calculationDiv;
    private Calculation calculationRem;

    private double result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Лябмда выражения
        calculationAdd = (x, y) -> x + y;
        calculationSub = (x, y) -> x - y;
        calculationMul = (x, y) -> x * y;
        calculationDiv = (x, y) -> x / y;
        calculationRem = (x, y) -> x % y;

        // связь полей представления с разметкой по id
        input = findViewById(R.id.input);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        btnRem = findViewById(R.id.btnRem);

        // обработаем нажатие кнопок
        btnAdd.setOnClickListener(listener);
        btnSub.setOnClickListener(listener);
        btnMul.setOnClickListener(listener);
        btnDiv.setOnClickListener(listener);
        btnRem.setOnClickListener(listener);
    }

    // создадим слушатель кнопки и с помощью анонимного класса переопределим метод onClick(View view)
    private View.OnClickListener listener = view -> {

        // считывание введённого пользователем списка чисел
        String dataIn = input.getText().toString();

        // проверяем ввод
        if (dataIn.length() > 0) {

            // возвращаем hint
            input.setHint("Ввод чисел");

            //ToDo надо бы еще массивчик проверять на предмет чисел и что их больше одного =)

            // формирование массива чисел
            String[] arrayDataIn = dataIn.split("\\s*,\\s*"); // "\\s*" - регулярное выражение пробелов от 0 до большого числа

            double xInput = Double.parseDouble(arrayDataIn[0]);
            double yInput = Double.parseDouble(arrayDataIn[1]);

            switch (view.getId()) {
                case R.id.btnAdd:
                    result = calculationAdd.calculate(xInput, yInput);
                    break;
                case R.id.btnSub:
                    result = calculationSub.calculate(xInput, yInput);
                    break;
                case R.id.btnMul:
                    result = calculationMul.calculate(xInput, yInput);
                    break;
                case R.id.btnDiv:
                    result = calculationDiv.calculate(xInput, yInput);
                    break;
                case R.id.btnRem:
                    result = calculationRem.calculate(xInput, yInput);
                    break;
            }
            input.setText("" + result);
        } else {
            // выводим уведомление в hint
            input.setHint("Введите через запятую два числа!");
        }
    };
}