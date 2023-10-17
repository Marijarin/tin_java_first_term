package edu.hw_2.task3;

public class FaultyConnectionManager implements ConnectionManager{
    @Override
    public Connection getConnection() {
        return new FaultyConnection();
    }
}
