package swingnerdle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@SuppressWarnings("unchecked")
public class FileReadWrite<T> {
    // verilen objenin verilen dosya ismindeki dosyaya yazilmasini saglar
    public boolean writeData(String fileName, T object) {
        File file = new File(fileName);
        try{
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream objOut = new ObjectOutputStream(fos);
            objOut.writeObject(object);
            objOut.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;   // exception geldiyse
        }
        return true;    // yazilabildiyse
    }
    
       // verilen dosya ismindeki dosyadan obje okuyup geriye dondurmeyi saglar
    public T readData(String fileName){
        File file = new File(fileName);
        T t;
        try{
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream objIn = new ObjectInputStream(fis);
            t = (T) objIn.readObject();
            objIn.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch(ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
        return t;
    }
    
    // verilen isimdeki dosyanin olup olmadigini geriye dondurur.
    public boolean isFileExists(String fileName){
        File file = new File(fileName);
        return file.exists();
    }
    
    // verilen isimdeki dosyanin silinmesini saglar
    public boolean deleteFile(String fileName) {
    	File file = new File(fileName);
    	if(isFileExists(fileName))
    		return file.delete();  
    	
    	return true;    // dosya yoksa da true doner.
    }
    
}
