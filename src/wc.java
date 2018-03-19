
import java.io.*;
import java.util.Vector;
import java.util.regex.*;

public class wc {
    //File source;
    public static Vector<File> source;
    public static Vector<String> stoplists;
    String outpath;
    public static int flag;
    private File outfile;
    private static OutputStream output;


    public wc(Vector<File> source,String outpath,Vector<String> stoplists) throws IOException{
        this.source=source;
        this.outpath=outpath;
        this.outfile=new File(outpath);
        this.output=new FileOutputStream(outfile);
        this.stoplists=stoplists;
    }
    public void count(Vector<String> parameters) throws IOException{
        for(int i=0;i<source.size();i++) {

            if (parameters.contains("-c")) {
                flag = 1;
                countall(source.get(i));
            }
            if (parameters.contains("-w")) {
                flag = 2;
                countall(source.get(i));
            }
            if (parameters.contains("-l")) {
                flag = 3;
                countall(source.get(i));
            }
            if(parameters.contains("-a")){
                complex(source.get(i));
            }
//        countall(source);
        }
    }

    public static void complex(File filename) throws IOException{
        InputStreamReader reader=null;
        int codeline=0;
        int nullline=0;
        int zhushiline=0;//英文词汇不够，这里是汉语拼音
        try{
            reader=new InputStreamReader(new FileInputStream(filename));
            BufferedReader br=new BufferedReader(reader);
            while(br.read()!=-1){
                String s=br.readLine();
                //if(s=="\n") nullline+=1;//空,这里错了，没审题，还是换成正则表达式方便一些
                if(Pattern.matches("//.*",s)||Pattern.matches("\\s*.{0,1}\\s*//.*",s)||Pattern.matches("([\\S]?/[/*][\\S]*)|\\*/\n",s)){
                    zhushiline+=1;
                    continue;
                }
                else if(s==null||Pattern.matches("\\s*.{0,1}\\s*",s)){
                    nullline+=1;
                    continue;
                }
                else{
                    codeline+=1;
                    continue;
                }
            }
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        String result_complex=filename.getName()+",代码行/空行/注释行："+codeline+"/"+nullline+"/"+zhushiline+"\r\n";
        output.write(result_complex.getBytes());
    }

    public static void countall(File filename) throws IOException{

        InputStreamReader reader=null;
        int countChar=0;
        int countword=0;
        int countline=0;
        try{

            reader=new InputStreamReader(new FileInputStream(filename));
            BufferedReader br=new BufferedReader(reader);
            do{
                String s=br.readLine();
                //output.write(s.getBytes());
                //System.out.println(s.getBytes());
                countChar+=s.length();
                String list[]=s.split(" |,");
                for(int ff=0;ff<list.length;ff++){
                if(stoplists.isEmpty()) {
                    if(!Pattern.matches("\\s*",list[ff])){countword++;}
                }
                else{


                        if((!stoplists.contains(list[ff].toLowerCase()))&&!Pattern.matches("\\s*",list[ff])){
                            countword++;
                            //output.write(list[ff].getBytes());
                            //output.write(stoplists.get(0).getBytes());
                        }
                    }
                }
                countline++;
            }while(br.read()!=-1);
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        switch (flag){
            case 1:
                //countChar=countChar;//这里-1的原因是会把最后一行的\n算进去
                String result=filename.getName()+",字符数："+countChar+"\r\n";
                output.write(result.getBytes());
                break;
            case 2:
                //countword=countword-2;
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
