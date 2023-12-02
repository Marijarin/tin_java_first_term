package edu.hw_8.Task3;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Decodes password which consist of 4 lower case latin alphabet letters
 * Such pattern of password does not make sense, but allows to ensure that program is correct
 **/
@SuppressWarnings("MagicNumber")
public class MD5Decoder {
    public int[] numberOfThreads = new int[] {1, 2, 5, 8, 16, 32, 64};
    long[] timeInS = new long[numberOfThreads.length];
    MD5HashMaker md5HashMaker = new MD5HashMaker();
    public Map<String, String> stolen = md5HashMaker.generateStolenData(md5HashMaker.passwords, md5HashMaker.people);
    public Map<String, String> deciphered = new HashMap<>();

    public void nextPassword() {
        while (!stolen.isEmpty()) {
            String nextPw = generateNext();
            String nextPwMD5 = md5HashMaker.makeMD5(nextPw);
            if (stolen.containsKey(nextPwMD5)) {
                deciphered.put(stolen.get(nextPwMD5), nextPw);
                stolen.remove(nextPwMD5, stolen.get(nextPwMD5));
            }
        }
    }

    public String generateNext() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 4;
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();

    }

    public void nextPasswordManyThreads(int threadsNumber) {
        try (ExecutorService service = Executors.newFixedThreadPool(threadsNumber)) {
            for (int i = 0; i < stolen.size(); i++) {
                service.execute(this::nextPassword);
            }
            service.shutdown();
        }
    }
}
