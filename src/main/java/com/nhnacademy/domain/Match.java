package com.nhnacademy.domain;

public class Match {
    private int count;
    private int win;
    public Match(){}
    public Match(int count, int win) {
        this.count = count;
        this.win = win;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public int getWin() {
        return win;
    }
    public void setWin(int win) {
        this.win = win;
    }
    @Override
    public String toString() {
        return "Match [count=" + count + ", win=" + win + "]";
    }
    
    
}
