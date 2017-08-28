package cn.fcffaf.android.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String TAG = "CheatActivity";

    private static final String EXTRA_ANSWER_IS_TRUE = "com.fcffaf.android.extra_answer_is_true";

    private static final String EXTRA_ANSWER_ISSHOW = "com.fcffaf.android.extra_answer_isshow";

    private Boolean mAnswerIsTrue;
    private TextView mAnswerText;
    private Button mShowAnswerButton;

    public static Intent newIntent(Context packageContext,boolean answer_is_true){
        Intent i = new Intent(packageContext,CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE,answer_is_true);
        return i;
    }

    public static boolean wasAnswerShow(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_ISSHOW,false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        Log.d(TAG,"onCreate()");

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);

        mAnswerText = (TextView) findViewById(R.id.answer_text);

        mShowAnswerButton = (Button) findViewById(R.id.show_answer_button);

        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAnswerIsTrue) {
                    mAnswerText.setText(R.string.true_button);
                }else{
                    mAnswerText.setText(R.string.false_button );
                }
                setAnswerShowResult(true);
            }
        });

    }
    private void setAnswerShowResult(boolean isAnswerShow){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_ISSHOW,isAnswerShow);
        setResult(RESULT_OK,data);
    }
}
