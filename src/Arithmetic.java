import java.io.*;
import javax.swing.*;
import java.util.*;


//BufferedWriter writer = new BufferedWriter(new FileWriter("answer.txt",true));
//BufferedReader br = new BufferedReader(new FileReader(file));

public class Arithmetic {
    BufferedWriter writer = new BufferedWriter(new FileWriter("answer.txt"));
    double lower;
    double higher;


    public Arithmetic() throws IOException {

    }

    public static void main(String[] args) throws IOException {

        Arithmetic call = new Arithmetic();
        String path = JOptionPane.showInputDialog("Enter File Path of The Sequence :");
        File file = new File(path);
        Scanner input = new Scanner(file);
        input.useLocale( Locale.US );
        String seq;
        double result1;
        String result2;
        double result3;
        ArrayList<Char> probTable;

        probTable = call.takeProbabilityInput();
        call.print(probTable);
        seq = input.next();
        result1 = call.compress(seq,probTable);
        call.writer.write(result1+" ");
        call.writer.newLine();
        result3 = call.generateCode(call.lower,call.higher);
        result2 = call.decompress(probTable,result3);


        call.writer.write(result2+" ");
        call.writer.newLine();
        call.writer.close();

        input.close();
        JOptionPane.showMessageDialog(null,"Done!","Output",JOptionPane.PLAIN_MESSAGE);

    }

    private ArrayList<Char> takeProbabilityInput() throws FileNotFoundException {
        String path = JOptionPane.showInputDialog("Enter File Path of The probability table :");
        File file = new File(path);
        Scanner input = new Scanner(file);
        input.useLocale( Locale.US );
        ArrayList<Char> chars = new ArrayList<>();
        double d;
        String ch = "";
        boolean flag = true;
        int count = 0;


        while(input.hasNext()){
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
                c.setLow(chars.get(count-1).getHigh());
                c.setHigh(chars.get(count-1).getHigh()+d);
                c.setRange(c.getHigh()-c.getLow());
            }
            count++;
        }
        input.close();
        return chars;
    }
    private double compress(String seq,ArrayList<Char> probTable) throws IOException {
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
        writer.write(low+"    "+high);
        writer.newLine();

        lower = low;
        higher = high;
        return getRandom(low,high);
    }
    private String decompress(ArrayList<Char> probTable,double code){
        String output = "";
        while (output.length()!=4){
        for (int i=0;i<probTable.size();i++){
            if((code<probTable.get(i).getHigh())&&(code>probTable.get(i).getLow())){
                output+=probTable.get(i).getCha();
                code = (code-probTable.get(i).getLow())/(probTable.get(i).getRange());
                break;
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
    private void print(ArrayList<Char> p) throws IOException {

        for (int i=0;i<p.size();i++){
            writer.write(p.get(i).toString());
            writer.newLine();
        }
        writer.write("______________");
        writer.newLine();

    }
    public static double getRandom(double min, double max){
        return (Math.random()*((max-min))) +min;
    }
    public double generateCode(double low, double high) throws IOException {
        String code = "1";
        double result = 0;
        for(int i=0;i<code.length();i++){
            for(int j=0;j<code.length();j++) {
                if(j==0) result = 0;
                if (String.valueOf(code.charAt(j)).equals("1")) {
                    result += getPow(0.5, j + 1);
                }
            }
            if(result<low){
                code+="1";
            }
            else if((result>low)&&(result>high)){
                code=code.substring(0,code.length()-1)+"01";
                i--;
            }
            else break;

        }

        code ="0."+code;
        writer.write(code);
        writer.newLine();
        writer.write(result+" ");
        writer.newLine();
        return result;
    }
    public double getPow(double base,int pow){
        double result = 1;
        for (int i=0;i<pow;i++){
            result *= base;
        }
        return result;

    }


}
