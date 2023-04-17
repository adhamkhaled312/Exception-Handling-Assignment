import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Assignment {
    public static void main(String[] args) {
        try{
            String fileName=args[0];
            if(!fileName.endsWith(".arxml")){
                throw new NotValidAutosarFileException ("Invalid File Extension");
            }
            File input=new File(fileName);
            if(input.length()==0){
                throw new EmptyAutosarFileException("The File Is Empty");
            }
            FileInputStream inputStream = new FileInputStream(input);
            int x;
            StringBuilder s=new StringBuilder();
            while((x=inputStream.read())!=-1){
                s.append((char)x);
        }
            String data=s.toString();
            Scanner scan=new Scanner(data);
            ArrayList<Containers> containers=new ArrayList<>();
            while (scan.hasNextLine()) {
                String line = scan.nextLine();              
                if (line.contains("<CONTAINER")) {
                    String ID = line.substring(line.indexOf("ID="), line.indexOf(">"));
                    String shortNameTag = scan.nextLine();
                    String shortName = shortNameTag.substring(shortNameTag.indexOf(">") + 1, shortNameTag.indexOf("</"));
                    String longNameTag = scan.nextLine();
                    String longName = longNameTag.substring(longNameTag.indexOf(">") + 1, longNameTag.indexOf("</"));
                    Containers c = new Containers();
                    c.setID(ID);
                    c.setShortName(shortName);
                    c.setLongName(longName);
                    containers.add(c);
                }
            }
            Collections.sort(containers);
            String outName = fileName.substring(0,fileName.indexOf("."))+"_mod.arxml";
            FileOutputStream out = new FileOutputStream(outName);
            out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n".getBytes());
            out.write("<AutoSar>\n".getBytes());
            for (int i = 0; i < containers.size(); i++) {
                out.write(containers.get(i).toString().getBytes());
            }
            out.write("\n</AutoSar>".getBytes());

        } catch (NotValidAutosarFileException e) {
            e = new NotValidAutosarFileException("Not Valid Autosar File");
        } catch (FileNotFoundException e) {
            e = new FileNotFoundException("File Not Found");
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }
}