package io.albot.example.kafka.dto;

public class Message<ID, V> {
    private String topic;
    private Integer partition;
    private ID key;
    private V message;

    public Message() {
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public ID getKey() {
        return key;
    }

    public void setKey(ID key) {
        this.key = key;
    }

    public Integer getPartition() {
        return partition;
    }

    public void setPartition(Integer partition) {
        this.partition = partition;
    }

    public V getMessage() {
        return message;
    }

    public void setMessage(V message) {
        this.message = message;
    }
}