package com.ktu.dev.melvin.ktu_forum.MainScreen.View_Answer.View;

/**
 * Created by Melvin on 12/20/2017.
 */

public class public_data {
    private String question;
    private String answer;
    private String qn_id;
    private String verified;
    private String ans_id;
    public_data(String question, String answer, String qn_id, String ans_id, String verified) {
        this.question = question;
        this.answer = answer;
        this.qn_id = qn_id;
        this.verified = verified;
        this.ans_id = ans_id;
    }
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
    public String getVerified() {
        return verified;
    }
}
