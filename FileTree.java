import java.io.File;
import java.util.Objects;

public class FileTree {
    public static String GetFiles(String login){
        char separator = File.separatorChar;
        String absPath = AbsPath.path + login;
        File fileTree = new File(absPath);
        StringBuilder list = new StringBuilder("");
        if (fileTree.isDirectory())
            RecursiveFileSearch(fileTree, list);
        return list.toString();
    }
    private static StringBuilder RecursiveFileSearch(File fileTree, StringBuilder builder){
        if (Objects.requireNonNull(fileTree.list()).length != 0) builder.append("<ul>").append(System.lineSeparator());
        for (File file : Objects.requireNonNull(fileTree.listFiles()))
        {
            builder.append("<li>").append(file.getAbsolutePath());
            if (file.isDirectory() ){
                builder = RecursiveFileSearch(file, builder);
            }
            builder.append("</li>");
        }
        if (Objects.requireNonNull(fileTree.list()).length != 0) builder.append("</ul>").append(System.lineSeparator());
        return builder;
    }
}