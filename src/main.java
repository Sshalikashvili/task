import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("შემოიტანე საძიებო სიტყვა :");
        Finder finder = new Finder(scanner.nextLine(), "C:src");
    }
}

class Finder{

    String word;
    String dir;

    public Finder(String name, String dir)
    {
        this.word = name;
        this.dir = dir;
        this.Find();
    }

    public void Find()
    {
        File dir_path = new File(this.dir);
        String[] contents = dir_path.list();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        String log = formatter.format(date) + " INFO Main:40 - შეტანილი სიტყვა : " + this.word;
        Log(log);

        for(int i=0; i < contents.length; i++) {
            if(contents[i].toLowerCase().startsWith(this.word.toLowerCase())){
                log = formatter.format(date) + " INFO Main:45 - მოიძებნა : " + contents[i];
                Log(log);
            }
        }
    }

    void Log(String log) {
        System.out.println(log);
        File file = new File("logging.log");

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(log+"\n");
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
