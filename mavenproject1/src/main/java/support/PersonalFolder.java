import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemException;
import java.nio.file.FileSystemNotFoundException;

public class PersonalFolder {
    public static void NewFolder(String login) throws FileSystemException {
        File file = new File(AbsPath.path + login);
        if (file.exists()) throw new FileAlreadyExistsException(file.getAbsolutePath());
        if (file.mkdir())
            System.out.println("Created folder \"" + AbsPath.path + login + "\" on new user registration");
        else
            throw new FileSystemException("Failed to create file \"" + file.getAbsolutePath() + "\"");
    }
    public static void DeleteFolder(String login) throws FileSystemException {
        File file = new File(AbsPath.path + login);
        if (file.exists()){
            if(file.delete()) System.out.println("Deleted folder \"" + AbsPath.path + login + "\"");
            else throw new FileSystemException("Failed to delete file \"" + file.getAbsolutePath() + "\"");
        }
        else throw new FileSystemNotFoundException(file.getAbsolutePath());
    }
}
