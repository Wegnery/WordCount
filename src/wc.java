
import java.io.*;
import java.util.Vector;


public class wc {
    File source;
    String outpath;
    public static int flag;
    private File outfile;
    private static OutputStream output;


    public wc(File source,String outpath) throws IOException{
        this.source=source;
        this.outpath=outpath;
        this.outfile=new File(outpath);
        this.output=new FileOutputStream(outfile);
    }
    public void count(Vector<String> parameters) throws IOException{
        if(parameters.contains("-c"))
        {flag=1;countall(source);}
        if(parameters.contains("-w"))
        {flag=2;countall(source);}
        if(parameters.contains("-l"))
        {flag=3;countall(source);}
//        countall(source);
    }
    public static void countall(File filename) throws IOException{
        InputStreamReader reader=null;
        int countChar=0;
        int countword=0;
        int countline=0;
        try{

            reader=new InputStreamReader(new FileInputStream(filename));
            BufferedReader br=new BufferedReader(reader);
            while(br.read()!=-1){
                String s=br.readLine();
                countChar+=s.length();
                countword+=s.split(" ").length;
                countline++;
            }
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        switch (flag){
            case 1:
                countChar=countChar-1;//这里-1的原因是会把最后一行的\n算进去
                String result=filename.getName()+",字符数："+countChar+"\r\n";
                output.write(result.getBytes());
                break;
            case 2:
                String result1=filename.getName()+",单词数："+countword+"\r\n";
                output.write(result1.getBytes());
                break;
            case 3:
                String result2=filename.getName()+",行数："+countline+"\r\n";
                output.write(result2.getBytes());
                break;
        }
    }
}
