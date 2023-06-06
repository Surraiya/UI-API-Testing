package Utilities;

import net.datafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtil {

    public static String generateAlphaNumericString(int length){
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static String generateSentence(){
        Faker faker = new Faker();
        return faker.lorem().sentence();
    }
}
