import java.io.Serializable;
import java.util.ArrayList;

public class Save implements Serializable{
    private static final long serialVersionUID = 5860787475073472801L;
    public String equation;
    public ArrayList<String> prevGuesses;

    public Save(String equation , ArrayList<String> prevGuesses){
        this.equation = equation;
        this.prevGuesses = prevGuesses;
    }

    @Override
    public String toString(){
        String a = "";
        for (String string : prevGuesses) {
            a += string;
            a += " ";
        }
        return "Save: " + equation + " " + a;
    }

}
