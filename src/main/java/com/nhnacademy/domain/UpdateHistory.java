package com.nhnacademy.domain;

import java.time.LocalDateTime;

public class UpdateHistory {
    private LocalDateTime history;
    private String updatedContext;
    public UpdateHistory(){}
    public UpdateHistory(LocalDateTime history, String updatedContext) {
        this.history = history;
        this.updatedContext = updatedContext;
    }

    public LocalDateTime getHistory() {
        return history;
    }
    public void setHistory(LocalDateTime history) {
        this.history = history;
    }
    public String getUpdatedContext() {
        return updatedContext;
    }
    public void setUpdatedContext(String updatedContext) {
        this.updatedContext = updatedContext;
    }
    @Override
    public String toString() {
        return "UpdateHistory [history=" + history + ", updatedContext=" + updatedContext + "]";
    }    
}
