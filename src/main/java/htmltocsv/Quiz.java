package htmltocsv;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Quiz {

    private Question question;
    private int goodAnswerIndex;
    private List<String> answers;
}
