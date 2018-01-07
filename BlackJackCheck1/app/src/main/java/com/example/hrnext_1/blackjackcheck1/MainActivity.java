package com.example.hrnext_1.blackjackcheck1;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
    String mWordDecision;
    String mWordCommand;
    int mNumTotal = 0;
    int mNumAdd = 0;
    int mNumSum = 0;
    int mInputCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初期処理
        final ImageView imageViewDroid = (ImageView) findViewById(R.id.imageViewDroid);
        final TextView textViewDecision = (TextView) findViewById(R.id.textViewDecision);
        final TextView textViewCommand = (TextView) findViewById(R.id.textViewCommand);
        final EditText editText = (EditText) findViewById(R.id.editText);
        Button buttonExe = (Button) findViewById(R.id.buttonExe);
        Button buttonClear = (Button) findViewById(R.id.buttonClear);
        final int colorGold = getResources().getColor(R.color.colorGold);
        final int colorRed = getResources().getColor(R.color.colorRed);
        final int colorBlue = getResources().getColor(R.color.colorBlue);
        final int colorDeepPink = getResources().getColor(R.color.colorDeepPink);
        mWordCommand = getString(R.string.commandNumber) + mInputCount + getString(R.string.commandCount);
        textViewCommand.setText(mWordCommand);

        //実行ボタン
        buttonExe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //入力値の取得
                String str = editText.getText().toString();
                editText.setText("");
                if (!(str == null || str.length() == 0)) {
                    ++mInputCount;
                    //１回目の処理
                    if (mNumTotal == 0) {
                        mNumTotal = Integer.parseInt(str);
                        mWordCommand = getString(R.string.commandNumber) + mInputCount + getString(R.string.commandCount) + getString(R.string.commandNow) + mNumTotal;
                    } else {
                        //２回目以降の処理
                        mNumAdd = Integer.parseInt(str);
                        mNumSum = mNumTotal + mNumAdd;
                        mNumTotal = mNumSum;
                        //HIT時の処理
                        if (mNumSum < 16) {
                            mWordDecision = getString(R.string.decision1);
                            mWordCommand = getString(R.string.commandNumber) + mInputCount + getString(R.string.commandCount) + getString(R.string.commandNow) + mNumSum;
                            textViewDecision.setTextColor(colorDeepPink);
                            imageViewDroid.setImageResource(R.drawable.androidonskateboardwithonearmup);
                        //PIG時の処理
                        } else if (mNumSum > 21) {
                            mWordDecision = getString(R.string.decision4);
                            mWordCommand = "\n";
                            editText.setVisibility(View.INVISIBLE);
                            textViewDecision.setTextColor(colorRed);
                            imageViewDroid.setImageResource(R.drawable.androidwitharmuponskateboard);
                        //BlackJack時の処理
                        } else if (mNumSum == 21) {
                            mWordDecision = getString(R.string.decision3);
                            mWordCommand = "\n";
                            editText.setVisibility(View.INVISIBLE);
                            textViewDecision.setTextColor(colorGold);
                            imageViewDroid.setImageResource(R.drawable.androidonskateboardwithtwoarmsup);
                        //STAND時の処理
                        } else if (mNumSum >= 16) {
                            mWordDecision = getString(R.string.decision2);
                            mWordCommand = "\n";
                            editText.setVisibility(View.INVISIBLE);
                            textViewDecision.setTextColor(colorBlue);
                            imageViewDroid.setImageResource(R.drawable.androidonskateboardwithleftarmraised);
                        }
                    }
                    //判定結果の出力
                    textViewDecision.setText(mWordDecision);
                    textViewCommand.setText(mWordCommand);
                }
            }
        });

        //クリアボタン
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNumTotal = 0;
                mNumAdd = 0;
                mNumSum = 0;
                mInputCount = 1;
                mWordDecision = "";
                mWordCommand = getString(R.string.commandNumber) + mInputCount + getString(R.string.commandCount);
                textViewDecision.setText(mWordDecision);
                textViewCommand.setText(mWordCommand);
                editText.setVisibility(View.VISIBLE);
                editText.setText("");
                imageViewDroid.setImageResource(R.drawable.androidonskateboard);
            }
        });
    }
}
