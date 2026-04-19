import java.util.Scanner;

public class PasswordValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String password = "";

        System.out.println("Welcome to SafeLog Password Validator");

        while (true) {
            System.out.print("Enter a password: ");
            password = scanner.nextLine();

            PasswordFeedback feedback = validatePassword(password);
            if (feedback.isValid()) {
                System.out.println("Password accepted.");
                break;
            }

            for (String message: feedback.getMessages()) {
                System.out.println(message);
            }
        }

        scanner.close();
    }

    private static PasswordFeedback validatePassword(String password) {
        boolean hasUppercase = false;
        boolean hasDigit = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            }
            if (Character.isDigit(ch)) {
                hasDigit = true;
            }
        }

        PasswordFeedback feedback = new PasswordFeedback();
        if (password.length() < 8) {
            feedback.addMessage("Too short.");
        }
        if (!hasUppercase) {
            feedback.addMessage("Missing an uppercase letter.");
        }
        if (!hasDigit) {
            feedback.addMessage("Missing a digit.");
        }

        return feedback;
    }

    private static class PasswordFeedback {
        private final java.util.List<String> messages = new java.util.ArrayList<>();

        void addMessage(String message) {
            messages.add(message);
        }

        boolean isValid() {
            return messages.isEmpty();
        }

        java.util.List<String> getMessages() {
            return messages;
        }
    }
}
