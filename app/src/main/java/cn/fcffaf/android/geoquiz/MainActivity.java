package cn.fcffaf.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "mainActivity";

    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_first,true),
            new Question(R.string.question_second,false),
            new Question(R.string.question_three,false),
            new Question(R.string.question_four,true),
            new Question(R.string.question_five,false)
    };

    private int mCurrentIndex = 0;
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;
    private boolean mIsCheater;

    private void updataQuestion(){
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressTure){
        boolean AnswerIsTrue = mQuestionBank[mCurrentIndex].getAnswerTrue();

        String result= AnswerIsTrue ? "istrue" : "isfalse";
        Log.d(TAG,result);
        int messageResId = 0;

        if (mIsCheater){
            messageResId = R.string.ischeate;
        }else {
            if (userPressTure == AnswerIsTrue) {
                messageResId = R.string.correct_toast;
            }else {
                messageResId = R.string.nocorrect_toast;
            }

        }


        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
        }

        Log.d(TAG,"onCreate()");

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updataQuestion();

        Button trueButton = (Button) findViewById(R.id.true_button);
        Button falseButton = (Button) findViewById(R.id.false_button);
        Button nextButton = (Button) findViewById(R.id.next_button);
        Button prevButton = (Button) findViewById(R.id.prev_button);
        Button cheatButton = (Button) findViewById(R.id.cheat_button);

        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean answer_is_true = mQuestionBank[mCurrentIndex].getAnswerTrue();
                Intent intent = CheatActivity.newIntent(MainActivity.this,answer_is_true);
                //startActivity(intent);
                startActivityForResult(intent,REQUEST_CODE_CHEAT);
            }
        });


        trueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex+1) % mQuestionBank.length;
                mIsCheater = false;
                updataQuestion();
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentIndex>0) {
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                }else {
                    mCurrentIndex = mQuestionBank.length-1;
                }
                updataQuestion();
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG,"onSaveInstanceState()");
        outState.putInt(KEY_INDEX,mCurrentIndex);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT){
            if (data == null) {
                return;
            }
           mIsCheater = CheatActivity.wasAnswerShow(data);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStrart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy()");
    }




}
