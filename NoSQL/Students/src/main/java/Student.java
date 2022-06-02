import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@Data
public class Student {
    private String name;
    private int age;
    private ArrayList<String> courses = new ArrayList<>();

    public Student parser(String initialString){
        String[] args = initialString.split(",");
        name = args[0];
        age = Integer.parseInt(args[1]);
        for (int i = 2; i < args.length; i++){
            courses.add(args[i].replace("\"",""));
        }
        return this;
    }
}
