package htmltocsv;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Question {

    private String mainQuestion;
    private String sideQuestion;
    private String subQuestion;

    public String niceString() {
        return String.format("%s, %s, %s",mainQuestion,sideQuestion,sideQuestion);
    }
}
