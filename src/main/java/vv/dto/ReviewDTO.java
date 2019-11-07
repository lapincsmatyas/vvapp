package vv.dto;

import java.util.Date;

public class ReviewDTO {
    private Long reviewId;
    private Date date;
    private SeniorDTO senior;
    private String text;

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SeniorDTO getSenior() {
        return senior;
    }

    public void setSenior(SeniorDTO senior) {
        this.senior = senior;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}