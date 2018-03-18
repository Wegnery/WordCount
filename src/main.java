import java.io.*;
import java.util.Vector;
public class main {
    public static Vector<String> parameters;
    public static String outpath;
    public static String stopword;
    public static boolean m=true;
    public static String nowpath;
    //public static File source;
    public static Vector<File> source;
    public static Vector<String> stoplists;
    public static void main(String args[]){
        getparameters(args);
        File f=new File("");
        nowpath=f.getAbsolutePath();//获取当前路径
        try {
            wc wordc = new wc(source, outpath,stoplists);
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
        source=new Vector<File>();//记得初始化，不然有空指针错误
        stoplists=new Vector<String>();
        for(int i=0;i<args.length;i++){
            if(args[i].equals("-o")){//-o是给输出路径名
                if(++i==args.length){
                    System.out.println("没有文件名字");
                    return;
                }
                outpath=args[i];

            }
            else if(args[i].equals("-c")||args[i].equals("-w")||args[i].equals("-l")||args[i].equals("-a"))
                parameters.addElement(args[i]);
            else if(args[i].equals("-e")){
                if(++i==args.length){
                    System.out.println("没有文件名字");
                    return;
                }
                stopword=args[i];
                try{findstop(stopword);}
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            else if(args[i].equals("-s")){
                m=false;
                i++;
                nowpath=args[i].substring(0,args[i].indexOf("*")-1);
                String mark=args[i].substring(args[i].indexOf("."));//什么类型的文件
                find(nowpath,mark);//在当前目录找
            }
            else if(m!=false)
                source.addElement(new File(args[i]));
        }
        return;
    }

    public static void find(String nowpath,String mark){
        File f=new File(nowpath+"\\");
        File nowf=new File("");
        File filelist[]=f.listFiles();
        if(filelist==null) {System.out.println("目录为空");return;}
        for(int i=0;i<filelist.length;i++) {
            nowf = filelist[i];
            if (nowf.isFile() && nowf.getName().endsWith(mark)) {
                source.addElement(nowf);
            } else if (nowf.isDirectory()) {
                find(nowf.getAbsolutePath(), mark);//递归
            }
        }
        }

        public static void findstop(String stopword) throws IOException{

            File stopf=new File(stopword);
            InputStreamReader reader=null;
            if(stopf.isFile()){
                reader=new InputStreamReader(new FileInputStream(stopf));
                BufferedReader br=new BufferedReader(reader);
                do{
                    String s=br.readLine();
                    String stoplist[]=s.split(" ");
                    for(String i:stoplist){
                        stoplists.addElement(i.toLowerCase());
                    }
                }while(br.read()!=-1);
                reader.close();
            }
            else{
                System.out.println("停用词不是个文件");
            }
        }
    }

