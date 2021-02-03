import com.github.javafaker.Faker;

import java.util.Locale;

public class PersonGenerator {
    private PersonGenerator() {}

    public static class Generator {
        private Generator() {
        }

        public static Person personGenerator(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new  Person(
                    faker.name().fullName(),
                    faker.phoneNumber().cellPhone(),
                    faker.address().cityName()
            );


        }
    }
}

class Person {
    private final String name;
    private final String city;
    private final String phone;

    public Person(String name, String phone, String city) {
        this.city = city;
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }
}
