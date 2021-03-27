import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONObject;

public class JSONFileHandler{
    private static JSONFileHandler jfh;
    private String folderPath;

    private JSONFileHandler(String folderPath){
        this.folderPath = folderPath;
    }

    public static JSONFileHandler getJFH(){
        if(jfh == null){
            jfh = new JSONFileHandler("data/");
        }
        return jfh;
    }

    public void saveToJSON(JSONObject root, String fileName){
        saveFileContents(fileName, root.toString(2));
    }

    public JSONObject getJSONFromFile(String fileName){
        return new JSONObject(getFileContents(fileName));
    }

    private String getFileContents(String fileName){
        try{
            Scanner scan = new Scanner(new File(getFilePath(fileName)));
            StringBuilder sb = new StringBuilder();
            while(scan.hasNext()){
                sb.append(scan.nextLine());
            }

            return sb.toString();
        }
        catch(IOException e){
            System.out.println("Error: Unable to read from JSON file with name \"" + fileName + "\"");
            e.printStackTrace();
            return null;
        }
    }

    private void saveFileContents(String fileName, String fileContents){
        try{
            FileWriter fw = new FileWriter(getFilePath(fileName));
            fw.write(fileContents);
            fw.close();
        }
        catch(IOException e){
            System.out.println("Error: Unable to write to JSON file with name \"" + fileName + "\"");
            e.printStackTrace();
        }
    }

    private String getFilePath(String fileName){
        return folderPath + (folderPath.endsWith("/") ? ("") : ("/")) + fileName + (fileName.endsWith(".json") ? ("") : (".json"));
    }
}