import java.util.*;
public class BasicCalculator {
    static double stringToInteger(String str){
        double ans = 0;
        for(int i=0;i<str.length();i++){
            ans+=(str.charAt(str.length()-i-1)-48)*Math.pow(10,i);
        }
        return ans;
    }
    public static double calculate(String s) {
        if(s.length()==0){
            return 0;
        }
        ArrayList<Double> num = new ArrayList<>();
        ArrayList<Character> oper = new ArrayList<>();
        for(int i=0;i<s.length();i++) {
            if (s.charAt(i) != '(') {
                if (s.charAt(i) >= 48) {
                    int j = i + 1;
                    while (j<s.length() && s.charAt(j) >= 48) {
                        j++;
                    }
                    num.add(stringToInteger(s.substring(i, j)));
                    i = j;
                }
                if (i<s.length() && s.charAt(i) != ' ') {
                    oper.add(s.charAt(i));
                }
            } else if (s.charAt(i)=='('){
                int j = i;
                int count = 1;
                while (count != 0) {
                    j++;
                    if (s.charAt(j) == '(') {
                        count++;
                    } else if (s.charAt(j) == ')') {
                        count--;
                    }
                }
                num.add(calculate(s.substring(i+1, j)));
                i = j;
            }
        }
        if(oper.size()==num.size() && oper.get(0)=='-'){
            num.set(0,num.get(0)*-1);
            oper.remove(0);
        }
        //Division
        for(int i=0;i<oper.size();){
            if(oper.get(i)=='/'){
                double temp = num.get(i)/num.get(i+1);
                oper.remove(i);
                num.remove(i);
                num.set(i,temp);
            }else {
                i++;
            }
        }
        //Multiplication
        for(int i=0;i<oper.size();){
            if(oper.get(i)=='*'){
                double temp = num.get(i)*num.get(i+1);
                oper.remove(i);
                num.remove(i);
                num.set(i,temp);
            }else {
                i++;
            }
        }
        //Subtraction
        for(int i=0;i<oper.size();){
            if(oper.get(i)=='-'){
                double temp = num.get(i)-num.get(i+1);
                oper.remove(i);
                num.remove(i);
                num.set(i,temp);
            }else {
                i++;
            }
        }
        //Addition
        for(int i=0;i<oper.size();){
            if(oper.get(i)=='+'){
                double temp = num.get(i)+num.get(i+1);
                oper.remove(i);
                num.remove(i);
                num.set(i,temp);
            }else {
                i++;
            }
        }
        return num.get(0);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        System.out.println(calculate(str));
    }
}
