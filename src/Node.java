import java.util.ArrayList;
import java.util.List;

public class Node
{
    private String tableName;
    List<String> textFields;
    List<Boolean> boolFields;
    List<Integer> intFields;
    List<Float> floatFields;
    List<Double> doubleFields;

    public Node() {}

    public void addTextField(String field) {
        if(textFields.isEmpty())
            textFields = new ArrayList<>();

        textFields.add(field);
    }
}
