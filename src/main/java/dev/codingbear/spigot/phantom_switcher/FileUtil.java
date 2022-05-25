package dev.codingbear.spigot.phantom_switcher;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
public class FileUtil {
    public boolean check_path_exist(String path){
        File target = new File(path);
        return target.exists();
    }
    public boolean new_file(String path){
        File target = new File(path);
        try{
            target.createNewFile();
            return true;
        }catch(Exception ignored){
            return false;
        }
    }
    public boolean new_folder(String path){
        File target = new File(path);
        try{
            return target.mkdir();
        }catch(Exception ignored){
            return false;
        }
    }
    public String read_file(String path){
        try{
            return new String(Files.readAllBytes(Paths.get(path)));
        }catch(Exception ignored){
            return "";
        }
    }
    public boolean delete_path(String path){
        File target = new File(path);
        return target.delete();
    }
    public boolean write_file(String path,String content){
        if (check_path_exist(path)){
            delete_path(path);
        }
        if (!new_file(path)){
            return false;
        }
        try{
            Files.write(Paths.get(path), content.getBytes());
            return true;
        }catch(Exception ignored){
            return false;
        }
    }
}
