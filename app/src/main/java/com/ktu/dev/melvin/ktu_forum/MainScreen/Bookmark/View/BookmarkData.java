package com.ktu.dev.melvin.ktu_forum.MainScreen.Bookmark.View;

/**
 * Created by Melvin on 12/25/2017.
 */

public class BookmarkData {
    private String question;
    private String answer;
    private String qn_id;
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public String getQn_id() {
        return qn_id;
    }
    public String getAns_id() {
        return ans_id;
    }
    public BookmarkData(String question, String answer, String qn_id, String ans_id) {
        this.question = question;
        this.answer = answer;
        this.qn_id = qn_id;
        this.ans_id = ans_id;
    }
    private String ans_id;
}
