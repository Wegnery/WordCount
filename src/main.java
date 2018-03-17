import java.io.File;
import java.io.IOException;
import java.util.Vector;
public class main {
    public static Vector<String> parameters;
    public static String outpath;
    public static File source;
    public static void main(String args[]){
        getparameters(args);
        try {
            wc wordc = new wc(source, outpath);
            wordc.count(parameters);
        }catch (Exception e){
            System.out.println("有错误");
            return;
        }
        return;




    }
    public static void getparameters(String[] args){
        outpath="result.txt";//默认路径
        parameters=new Vector<String>();
        for(int i=0;i<args.length;i++){
            if(args[i].equals("-o")){//-o是给输出路径名
                outpath=args[i];

            }
            else if(args[i].equals("-c")||args[i].equals("-w")||args[i].equals("-l"))
                parameters.addElement(args[i]);
            source=new File(args[i]);
        }
        return;
    }
}
