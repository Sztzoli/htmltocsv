package htmltocsv;

import java.util.List;

public class Main {



    public static void main(String[] args) {
        ReadFromHtml read = new ReadFromHtml();
        ConvertToQuiz convert = new ConvertToQuiz();
        WriteToCsv write = new WriteToCsv();

        String html = read.readHtml("src/main/resources/quiz.html");
        List<Quiz> quizzes = convert.convertTo(html);
        write.write("src/main/resources/converted2.csv",quizzes);
    }
}
