package edu.hw_2.task3;

public class DefaultConnectionManager implements ConnectionManager {
    @Override
    @SuppressWarnings("MagicNumber")
    public Connection getConnection() {
        int random = (int) (Math.random() * 10);
        if (random % 3 == 0) {
            return new FaultyConnection();
        } else {
            return new StableConnection();
        }
    }
}
