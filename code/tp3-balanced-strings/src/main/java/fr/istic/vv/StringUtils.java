package fr.istic.vv;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;

public class StringUtils {

    private StringUtils() {}

    public static boolean isBalanced(String str) {
        boolean isBalanced = true;
        CharacterIterator c = new StringCharacterIterator(str);
        ArrayList<Character> list_char = new ArrayList<>();
        if(str.length()%2 == 0){
            while (c.current() != CharacterIterator.DONE) {
                if(c.current() == '(' || c.current() == '[' || c.current() =='{'){
                    list_char.add(c.current());
                }else {
                    //prendre le dernier élément de la liste
                    if(list_char.size() > 0){
                        char last_c = list_char.get(list_char.size()-1);
                        if(
                                (last_c =='(' && c.current() != ')')
                                        ||(last_c =='[' && c.current() != ']')
                                        ||(last_c =='{' && c.current() != '}')
                        ){
                            isBalanced = false;
                            break;
                        }else{
                            list_char.remove(list_char.size()-1);
                        }
                    } else{
                       //la liste est vide et le char est un char fermant
                       isBalanced = false;
                       break;
                    }

                }
                System.out.print(c.current());
                c.next();
            }
        }
        else{
            isBalanced = false;
        }
        return isBalanced;

    }

}
