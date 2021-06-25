package htmltocsv;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Quiz {

    private String title;
    private String question;
    private int goodAnswerIndex;
    private List<String> answers;
}
