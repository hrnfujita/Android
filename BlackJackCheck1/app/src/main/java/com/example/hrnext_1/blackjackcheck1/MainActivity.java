package com.example.hrnext_1.blackjackcheck1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    String mWord1;
    String mWord2;
    int mNum1 = 0;
    int mNum2 = 0;
    int mSumNum = 0;
    int mInputCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView1 = (TextView) findViewById(R.id.textView1);
        final TextView textView2 = (TextView) findViewById(R.id.textView2);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        final EditText editText = (EditText) findViewById(R.id.editText);
        mWord2 = getString(R.string.input_num1) + mInputCount + getString(R.string.input_num2);
        textView2.setText(mWord2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //数値のみの入力制限を実装する
//                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                String str = editText.getText().toString();
                editText.setText("");
                if (!(str == null || str.length() == 0)) {
                    ++mInputCount;
                    if (mNum1 == 0) {
                        mNum1 = Integer.parseInt(str);
                        mWord2 = getString(R.string.input_num1) + mInputCount + getString(R.string.input_num2);
                    } else {
                        mNum2 = Integer.parseInt(str);
                        mSumNum = mNum1 + mNum2;
                        mNum1 = mSumNum;
                        if (mSumNum < 16) {
                            mWord1 = getString(R.string.input_num3);
                            mWord2 = getString(R.string.input_num1) + mInputCount + getString(R.string.input_num2);
                        } else if (mSumNum > 21) {
                            mWord1 = getString(R.string.input_num4);
                            mWord2 = "";
                            editText.setVisibility(View.INVISIBLE);
                        } else if (mSumNum == 21) {
                            mWord1 = getString(R.string.input_num5);
                            mWord2 = "";
                            editText.setVisibility(View.INVISIBLE);
                        } else if (mSumNum >= 16) {
                            mWord1 = getString(R.string.input_num6);
                            mWord2 = "";
                            editText.setVisibility(View.INVISIBLE);
                        }
                    }
                    textView1.setText(mWord1);
                    textView2.setText(mWord2);
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNum1 = 0;
                mNum2 = 0;
                mSumNum = 0;
                mInputCount = 1;
                mWord1 = "";
                mWord2 = getString(R.string.input_num1) + mInputCount + getString(R.string.input_num2);
                textView1.setText(mWord1);
                textView2.setText(mWord2);
                editText.setVisibility(View.VISIBLE);
                editText.setText("");
            }
        });
    }
}
