package like_lion.pangjam.utility;

import java.util.Random;

public class NicknameGenerator {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final int NICKNAME_LENGTH = 8;

    public static String generateNickname() {
        Random random = new Random();
        StringBuilder nickname = new StringBuilder();

        // 최소 1개의 알파벳으로 시작
        nickname.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));

        // 나머지 문자 생성
        for (int i = 1; i < NICKNAME_LENGTH; i++) {
            if (random.nextBoolean()) {
                nickname.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
            } else {
                nickname.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
            }
        }

        return nickname.toString();
    }
}