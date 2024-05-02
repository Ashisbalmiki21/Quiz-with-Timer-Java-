import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizQuestion {
    private String question;
    private String[] options;
    private int correctOption;

    public QuizQuestion(String question, String[] options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public boolean isCorrect(int selectedOption) {
        return selectedOption == correctOption;
    }
}

public class QuizApplication {
    private static int score = 0;
    private static int questionIndex = 0;
    private static Timer timer;

    private static QuizQuestion[] questions = {
        new QuizQuestion("What is the capital of France?", new String[]{"A. London", "B. Paris", "C. Rome", "D. Berlin"}, 2),
        new QuizQuestion("Which planet is known as the Red Planet?", new String[]{"A. Mars", "B. Venus", "C. Jupiter", "D. Saturn"}, 0),
        new QuizQuestion("Who wrote 'To Kill a Mockingbird'?", new String[]{"A. J.K. Rowling", "B. Harper Lee", "C. George Orwell", "D. Mark Twain"}, 1)
    };
    public int getCorrectOption() {
        return correctOption;
    }
    public static void main(String[] args) {
        startQuiz();
    }

    public static void startQuiz() {
        System.out.println("Welcome to the Quiz Application!");
        System.out.println("You will have 10 seconds to answer each question.");
        System.out.println("Let's begin!\n");

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                displayNextQuestion();
            }
        }, 0, 10000); // Set the timer to 10 seconds
    }

    public static void displayNextQuestion() {
        if (questionIndex < questions.length) {
            QuizQuestion currentQuestion = questions[questionIndex];

            System.out.println("Question " + (questionIndex + 1) + ": " + currentQuestion.getQuestion());
            String[] options = currentQuestion.getOptions();
            for (String option : options) {
                System.out.println(option);
            }

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your answer (A/B/C/D): ");
            String answer = scanner.nextLine().toUpperCase();

            if (answer.equals("A") || answer.equals("B") || answer.equals("C") || answer.equals("D")) {
                int selectedOption = answer.charAt(0) - 'A';
                if (currentQuestion.isCorrect(selectedOption)) {
                    score++;
                }
                questionIndex++;
            } else {
                System.out.println("Invalid input. Please enter A/B/C/D.");
            }
        } else {
            endQuiz();
        }
    }

    public static void endQuiz() {
        timer.cancel();
        System.out.println("\nQuiz Completed!");
        System.out.println("Your final score is: " + score + "/" + questions.length);
        System.out.println("Here are the correct answers:");

        for (int i = 0; i < questions.length; i++) {
            System.out.println("Question " + (i + 1) + ": " + questions[i].getOptions()[questions[i].getCorrectOption()]);
        }
    }
}
