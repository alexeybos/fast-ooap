package org.skillsmart.data;

public class StatObj {

    private Long score;
    private Integer turnCount;

    public StatObj() {
        this.score = 0L;
        this.turnCount = 0;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Integer getTurnCount() {
        return turnCount;
    }

    public void setTurnCount(Integer turnCount) {
        this.turnCount = turnCount;
    }
}
