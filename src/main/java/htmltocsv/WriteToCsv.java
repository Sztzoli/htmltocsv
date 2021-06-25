package htmltocsv;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteToCsv {

    public void write(String pathFile, List<Quiz> quizzes) {
        File file = new File(pathFile);
        try (FileWriter outputFile = new FileWriter(file);
             CSVWriter writer = new CSVWriter(outputFile)) {

            String[] header = {"title","Kérdés", "helyes sorszáma", "1", "2", "3", "4"};
            writer.writeNext(header);

            for (Quiz quiz : quizzes) {
                String[] data = getData(quiz);
                writer.writeNext(data);
            }

        } catch (IOException ioe) {
            throw new IllegalStateException("File cannot write", ioe);
        }
    }

    private String[] getData(Quiz quiz) {
        return new String[]{
                quiz.getTitle(),
                quiz.getQuestion(),
                String.valueOf(quiz.getGoodAnswerIndex()),
                quiz.getAnswers().get(0),
                quiz.getAnswers().get(1),
                quiz.getAnswers().get(2),
                quiz.getAnswers().get(3)
        };
    }
}
