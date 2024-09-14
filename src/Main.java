
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws RuntimeException {
       MathOperator operator = new MathOperator();
       Num num1 = Num.NIHIL;
       Num num2 = Num.NIHIL;
        int a = 0;
        int b = 0;
        System.out.println("Введите выражение:");
        String text = scanner.nextLine();
        text = text.replaceAll("\\s+","");
        operator.setOper(text);
        String[] num = new String[2];
        num = text.split("(\\+|-|\\*|/)");
        num1=MarhScan.Scan(num[0]);
        num2=MarhScan.Scan(num[1]);
        int result = 0;
        if ((num1.getStatus() == num2.getStatus()) & (num1.getStatus() != StatNum.ERROR) & (num2.getStatus() != StatNum.ERROR)) {
            switch (operator.getOper()) {
                case "+":
                    result = num1.getArab() + num2.getArab();
                    break;
                case "-":
                    if ((num2.getStatus()==StatNum.RIMSKIE) & (num1.getArab() > num2.getArab())) {
                        result = num1.getArab() - num2.getArab();
                    } else {
                            throw new RuntimeException("т.к. в римской системе нет отрицательных чисел");
                    }
                    break;
                case "*":
                    result = num1.getArab() * num2.getArab();
                    break;
                case "/":
                    if (num2.getArab() != 0) {
                        result = num1.getArab() / num2.getArab();
                    } else {
                        System.out.println("ИСКЛЮЧЕНИЕ делить на ноль запрещено.");
                    }
                    break;
            }
        } else {
            throw new RuntimeException("т.к. используются одновременно разные системы счисления");
        }
        if (num1.getStatus() == StatNum.ARABSKIE ){
            System.out.println("Результат: " + result);
        }else {
            for (int i = 0; i < Num.values().length ; i++)
                if (result == Num.values()[i].getArab()){
                    System.out.println(Num.values()[i].getRim());

                        break;
                }
        }

    }
}


class MathOperator  {
    String c;
    public String getOper(){
        return c;
    }
    public void setOper (String text){
        int count1 = text.split("\\+",-1).length-1;
        int count2 = text.split("-",-1).length-1;
        int count3 = text.split("\\*",-1).length-1;
        int count4 = text.split("/",-1).length-1;
        if ((count1==1) & (count2==0) & (count3==0) & (count4==0)){
            this.c = "+";
        } else if ((count1==0) & (count2==1) & (count3==0) & (count4==0)) {
            this.c = "-";
        } else if ((count1==0) & (count2==0) & (count3==1) & (count4==0)) {
            this.c = "*";
        } else if ((count1==0) & (count2==0) & (count3==0) & (count4==1)) {
            this.c = "/";
        } else if ((count1==0) & (count2==0) & (count3==0) & (count4==0)){
            throw new RuntimeException("т.к. строка не является математической операцией");
        } else {
            throw new RuntimeException("т.к. формат математической операции не удовлетворяет\n заданию - два операнда и один оператор (+, -, /, *)");
        }
    }
}
class MarhScan {
    public static Num Scan (String num){
        Num numCl = Num.NIHIL;
        int symbRim = (num.split("(I|V|X)",-1).length-1);
        if (symbRim>0) {
            for (int i = 0; i < Num.values().length ; i++)
                if (num.equals(Num.values()[i].getRim())){
                     if ((Num.values()[i].getStatus()==StatNum.NONE) || (Num.values()[i].getStatus()==StatNum.RIMSKIE)){
                        numCl = Num.values()[i];
                        numCl.setStatus(StatNum.RIMSKIE);
                        break;
                    }else {Num.values()[i].setStatus(StatNum.ERROR);}
                }    //КОНЕЦ Проверка наличия римских знаков
        } else if (symbRim==0) {
            for (int i = 0; i < Num.values().length ; i++)
                if (num.equals(Integer.toString(Num.values()[i].getArab()))) {
                    if ((Num.values()[i].getStatus()==StatNum.NONE) || (Num.values()[i].getStatus()==StatNum.ARABSKIE)) {
                        numCl = Num.values()[i];
                        numCl.setStatus(StatNum.ARABSKIE);
                        break;
                    } else {Num.values()[i].setStatus(StatNum.ERROR);}
                }
        }
    return numCl;
    }
}