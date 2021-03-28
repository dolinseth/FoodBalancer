import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONObject;

public class JSONFileHandler{
    //singleton instance of the JSONFileHandler class, to be used by all classes that need to import/export via JSON
    private static JSONFileHandler jfh;
    //path to the folder where JSON files will be stored
    private String folderPath;

    /**
     * default constructor, simply sets the folder path. Private because this is a singleton class so only one instance is created and is shared by all classes in the program
     * @param folderPath - string containing the path to the folder that holds all the JSON files
     */
    private JSONFileHandler(String folderPath){
        this.folderPath = folderPath;
    }

    /**
     * gets the singleton instance of the class, and initializes it if it is not already initialized
     * @return - the singleton instance of the JSONFileHandler class
     */
    public static JSONFileHandler getJFH(){
        if(jfh == null){
            jfh = new JSONFileHandler("data/");
        }
        return jfh;
    }

    /**
     * helper function to save a given JSONObject to a JSON file with the specified name
     * @param root - the JSONObject to write to the file
     * @param fileName - the name of the file to write the object to, file extension optional, .json is appended if not already present
     */
    public void saveToJSON(JSONObject root, String fileName){
        saveFileContents(fileName, root.toString(2));
    }

    /**
     * reads in a JSONObject from a file
     * @param fileName - the name of the file containing the JSON, file extension optional, .json is appended if not already present
     * @return - the JSONObject described by the specified file
     */
    public JSONObject getJSONFromFile(String fileName){
        return new JSONObject(getFileContents(fileName));
    }

    /**
     * helper function to get the contents of the specified file as a string
     * @param fileName - the name of the file to fetch the contents of
     * @return - a single string containing the contents of the file, to be used by the JSONObject constructor
     */
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

    /**
     * helper function to save a string to a file, for writing JSON files to disk
     * @param fileName - the name of the file to write to
     * @param fileContents - the contents of the file to be written
     */
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

    /**
     * helper function to convert a filename to an actual usable file path, handles missing slashes and file extensions
     * @param fileName - the name of the file to get the path for
     * @return - a string containing the full path to the specified file
     */
    private String getFilePath(String fileName){
        return folderPath + (folderPath.endsWith("/") ? ("") : ("/")) + fileName + (fileName.endsWith(".json") ? ("") : (".json"));
    }
}