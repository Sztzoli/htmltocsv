package htmltocsv;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConvertToQuiz {

    public List<Quiz> convertTo(String html) {
        List<Quiz> quizzes = new ArrayList<>();
        int numberOfQuiz = numberOfQuiz(html);

        List<String> quizzesString = splitHtmlString(html, numberOfQuiz);
        quizzes = convertToQuiz(quizzesString);
        return quizzes;
    }

    private int numberOfQuiz(String html) {
        int count = 0;
        Pattern h1 = Pattern.compile("</h3>");
        Matcher m = h1.matcher(html);
        while (m.find()) {
            count++;
        }
        return count;
    }

    private String splitToQuestion(String htmlPart) {
        String part = htmlPart.substring(htmlPart.indexOf("</h3>") + 5, htmlPart.indexOf("<ul>"));
        return part;
    }

    private String splitToComment(String htmlPart) {
        System.out.println(htmlPart.substring(htmlPart.indexOf("</ul>") + 5));
        String part = htmlPart.substring(htmlPart.indexOf("</ul>") + 5);
        return part;
    }




    private List<String> splitHtmlString(String html, int size) {
        List<String> partHtml = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int index = html.indexOf("</p>", html.indexOf("</ul>"))+5;
            String newHtml = html.substring(html.indexOf("<h3"),index);
            System.out.println(newHtml);

            html = html.substring(html.indexOf("</ul>") + 5);
            partHtml.add(newHtml);
        }
        return partHtml;
    }

    private List<Quiz> convertToQuiz(List<String> quizzesString) {
        List<Quiz> quizzes = new ArrayList<>();
        for (String s : quizzesString) {
            Document doc = Jsoup.parse(s);
            String question = splitToQuestion(s);
            String comment = splitToComment(s);
            Quiz quiz = getQuiz(doc, question, comment);
            quizzes.add(quiz);
        }
        return quizzes;
    }


    private Quiz getQuiz(Document doc, String question, String comment) {
        Quiz quiz = new Quiz();
     //   quiz.setTitle(doc.getElementsByTag("h1").text());
        quiz.setQuestion(question);
        quiz.setComment(comment);
        Elements listItem = doc.select("li");
        List<String> answers = new ArrayList<>();
        for (int i = 0; i < listItem.size(); i++) {
            if (!listItem.get(i).getElementsByTag("input").isEmpty()) {
                quiz.setGoodAnswerIndex(i + 1);
            }
            //van input tag
            answers.add(listItem.get(i).toString().replace("<li>","").replace("</li>",""));
//            nincs input tag
//            answers.add(listItem.get(i).toString().replace("<li>","").replace("</li>","")
//                    .replaceAll("<input(.*?)>","").trim());
        }
        quiz.setAnswers(answers);
        return quiz;
    }


}
