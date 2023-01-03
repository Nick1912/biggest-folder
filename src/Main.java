import java.io.File;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
    String folder = "D:/��������/SKILLBOX";
        File file = new File(folder);
        long start = System.currentTimeMillis();
        FolderSizeCalculator calculator= new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();
        long size = (long) pool.invoke(calculator);
        System.out.println(size);
        long duration = System.currentTimeMillis()- start;
        System.out.println(duration + " ms");


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

}
