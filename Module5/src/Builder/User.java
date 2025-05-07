package Builder;

/**
 * Класс, представляющий пользователя.
 */
public class User {
    private final String firstName;
    private final String lastName;
    private final int age;

    private User(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
    }

    /**
     * Билдер для создания пользователя.
     */
    public static class Builder {
        private String firstName;
        private String lastName;
        private int age;

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setAge(int age) {
            if (age < 0 || age > 150) {
                throw new IllegalArgumentException("Недопустимый возраст.");
            }
            this.age = age;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    @Override
    public String toString() {
        return String.format("Пользователь: %s %s, возраст %d", firstName, lastName, age);
    }
}

