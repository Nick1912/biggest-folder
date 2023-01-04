import java.io.File;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
    String folder = "D:/œ–¿ “» ¿/SKILLBOX";
        File file = new File(folder);
        long start = System.currentTimeMillis();
        FolderSizeCalculator calculator= new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();
        long size = (long) pool.invoke(calculator);
        System.out.println(size);
        long duration = System.currentTimeMillis()- start;
        System.out.println(duration + " ms");
        System.out.println(getHumanReadableSize(size));
        System.out.println(getSizeFromHumanReadable(getHumanReadableSize(size)));


//        System.out.println(getFolderSize(file));
    }
    public static long getFolderSize(File folder)
    {
        if(folder.isFile()){
            return folder.length();
        }
        File[] files= folder.listFiles();
        long sum=0;
        for (File file: files){
            sum+= getFolderSize(file);
        }
        return sum;
    }
    public static String getHumanReadableSize(long size){
        String [] name = {"B","K","M","G","T"};
        String s1="";
        for(int i = 0; i<5;i++){
            int s = (int) (Math.round(size/Math.pow(1024,i)));
            if (s<1000){
                s1=String.valueOf(s)+name[i];
                return s1;
            }
        }
      return "";
    }
     public static long getSizeFromHumanReadable(String size){
         String [] name = {"B","K","M","G","T"};
         String nameSize=size.replaceAll("[0-9]+","");
         String numberSize = size.replaceAll("[^0-9]+","");
         long ss= Long.parseLong(numberSize);
         long s=0;
         for (int i =0;i<5;i++){
             if (nameSize.equals(name[i])) {
                 s = (long) (ss * Math.pow(1024, i));
                 return s;
             }
         }
        return 0;
     }
}
