package edu.hw_9.task2;

import java.io.File;

@FunctionalInterface
public interface Conditioner {
   boolean isCondition(File file);
}
