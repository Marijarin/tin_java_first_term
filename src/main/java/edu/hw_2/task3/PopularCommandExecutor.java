package edu.hw_2.task3;

public class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    private final String[] commands = {"apt update && apt upgrade -y", "apt update", "apt upgrade -y"};

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        String command = commands[(int) (Math.random() * commands.length)];
        tryExecute(command);
    }

    void tryExecute(String command) {
        int attemptsDone = 0;
        Throwable cause = new Throwable();
        while (attemptsDone < maxAttempts) {
            try (Connection connection = this.manager.getConnection()) {
                connection.execute(command);
            } catch (Exception e) {
                cause = e.getCause();
            } finally {
                attemptsDone++;
            }
        }
        if (attemptsDone > maxAttempts) {
            throw new ConnectionException("Attempts limit exceeded", cause);
        }
    }
}
