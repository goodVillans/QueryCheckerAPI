package com.openbox.querychecker.model.dto;

public class QueryResultDTO<T> {
    private T data;
    private Long executionTimeMs;
    private String query;

    public QueryResultDTO() {}

    public QueryResultDTO(T data, Long executionTimeMs, String query) {
        this.data = data;
        this.executionTimeMs = executionTimeMs;
        this.query = query;
    }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
    public Long getExecutionTimeMs() { return executionTimeMs; }
    public void setExecutionTimeMs(Long executionTimeMs) { this.executionTimeMs = executionTimeMs; }
    public String getQuery() { return query; }
    public void setQuery(String query) { this.query = query; }
}
