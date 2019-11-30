import java.security.PrivilegedAction;
import java.util.*;

public class Arithmetic {

    public static void main(String[] args) {

        Arithmetic call = new Arithmetic();
        Scanner input = new Scanner(System.in);
        input.useLocale( Locale.US );
        String seq;
        double result1;
        String result2;

        ArrayList<Char> probTable;
        probTable = call.takeProbabilityInput();
        call.print(probTable);
        System.out.println("Enter sequence pls : ");
        seq = input.next();
        result1 = call.compress(seq,probTable);
        System.out.println(result1);
        result2 = call.decompress(probTable,result1);
        System.out.println(result2);
    }

    private ArrayList<Char> takeProbabilityInput(){
        Scanner input = new Scanner(System.in);
        input.useLocale( Locale.US );
        ArrayList<Char> chars = new ArrayList<>();
        double d;
        String ch = "";
        boolean flag = true;

        System.out.println("Enter number of probabilities : ");
        int n = input.nextInt();
        System.out.println("Enter probabilities :");

        for(int i=0;i<n;i++){
            Char c = new Char();
            ch = input.next();
            d = input.nextDouble();

            chars.add(c);

            c.setCha(ch);

            if(flag){
                c.setLow(0);
                c.setHigh(d);
                c.setRange(c.getHigh()-c.getLow());
                flag = false;
            }
            else {
                c.setCha(ch);
                c.setLow(chars.get(i-1).getHigh());
                c.setHigh(chars.get(i-1).getHigh()+d);
                c.setRange(c.getHigh()-c.getLow());
            }
        }
        return chars;
    }
    private double compress(String seq,ArrayList<Char> probTable){
        double low = 0,high = 1,range = 1,result,alias = 0;
        String tmp;
        Char tm;
        for (int i=0;i<seq.length();i++){
            tmp = String.valueOf(seq.charAt(i));
            tm = getC(probTable,tmp);
            assert tm != null;
            alias   = low +(range*tm.getLow());
            high  = low +(range*tm.getHigh());
            low = alias;
            range = high-low;
        }
        System.out.println(low+"    "+high);
        return getRandom(low,high);
    }
    private String decompress(ArrayList<Char> probTable,double code){
        String output = "";
        while (output.length()!=4){
        for (int i=0;i<probTable.size();i++){
            if((code<probTable.get(i).getHigh())&&(code>probTable.get(i).getLow())){
                output+=probTable.get(i).getCha();
                code = (code-probTable.get(i).getLow())/(probTable.get(i).getRange());
            }
        }
        }
        return output;
    }
    private Char getC(ArrayList<Char> probTable,String s){
        for (int i=0;i<probTable.size();i++){
            if(s.equals(probTable.get(i).getCha()))return probTable.get(i);
        }
        return null;
    }
    private void print(ArrayList<Char> p){
        for (int i=0;i<p.size();i++){
            System.out.println(p.get(i).toString());
        }


    }
    public static double getRandom(double min, double max){
        return (Math.random()*((max-min))) +min;
    }


}
