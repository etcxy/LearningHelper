package com.lh.learninghelper.bean;

import java.io.Serializable;

/**
 * Created by etcxy@live.cn on 12/12/2017.
 */

public class AnalysisQuestion implements Serializable {
    private String Title;
    private String Content;
    private String QuestionCode;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getQuestionCode() {
        return QuestionCode;
    }

    public void setQuestionCode(String questionCode) {
        QuestionCode = questionCode;
    }
}
