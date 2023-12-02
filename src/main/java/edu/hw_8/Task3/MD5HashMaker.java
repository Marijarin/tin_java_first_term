package edu.hw_8.Task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("MagicNumber")
public class MD5HashMaker {
    public final List<String> passwords = List.of(
        "dssc",
        "vvan",
        "dmlv",
        "oopz",
        "erty",
        "bgmn",
        "azxs",
        "xddw"
    );
    public final List<String> people = List.of(
        "r.r.ivanov",
        "v.v.vikin",
        "f.g.vetrov",
        "a.a.mashkov",
        "m.m.maslow",
        "c.c.xrenov",
        "b.b.jijin",
        "i.o.input"
    );

    private List<String> makeMD5ForList(List<String> pws) {
        List<String> md5 = new ArrayList<>(pws.size());
        for (String pw : pws) {
            md5.add(makeMD5(pw));
        }
        return md5;
    }

    public String makeMD5(String pw) {
        try {
            var md = MessageDigest.getInstance("MD5");
            md.update(pw.getBytes());
            byte[] digest = md.digest();
            return bytesToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private String bytesToHex(byte[] bytes) {
        var builder = new StringBuilder();
        for (var b : bytes) {
            builder.append(String.format("%02x", b & 0xff));
        }
        return builder.toString();
    }

    public Map<String, String> generateStolenData(List<String> pws, List<String> names) {
        var md5 = makeMD5ForList(pws);
        Map<String, String> stolen = new ConcurrentHashMap<>();
        for (int i = 0; i < names.size(); i++) {
            stolen.put(md5.get(i), names.get(i));
        }
        return stolen;
    }

}
