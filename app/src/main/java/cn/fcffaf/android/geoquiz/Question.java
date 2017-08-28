package cn.fcffaf.android.geoquiz;

/**
 * Created by Administrator on 2017/8/25.
 */

@SuppressWarnings("ALL")
public class Question {

    private  int mTextResId;
    private Boolean mAnswerTrue;

    public Question(int textResId, Boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public Boolean getAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(Boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
