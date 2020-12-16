import java.io.File;

public class PersonalFolder {
    public static void NewFolder(String login){
        File file = new File(AbsPath.path + login);
        if (file.mkdir())
            System.out.println("Created folder \"" + AbsPath.path + login + "\" on new user registration");
        else
            System.out.println("Failed to add folder \"" + AbsPath.path + login + "\"");
    }
}
