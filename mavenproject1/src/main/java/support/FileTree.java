package support;
import java.io.File;

public class FileTree {
    public static String GetFiles(String login){
        char separator = File.separatorChar;
        String absPath = "D:\\dev\\users\\" + login;
        File fileTree = new File(absPath);
        StringBuilder list = new StringBuilder("");
        if (fileTree.isDirectory())
            RecursiveFileSearch(fileTree, list);
        return list.toString();
    }
    private static StringBuilder RecursiveFileSearch(File fileTree, StringBuilder builder){
        if (fileTree.list().length != 0) builder.append("<ul>").append(System.lineSeparator());
        for (File file : fileTree.listFiles())
        {
            builder.append("<li>").append(file.getAbsolutePath());
            if (file.isDirectory() ){
                builder = RecursiveFileSearch(file, builder);
            }
            builder.append("</li>");
        }
        if (fileTree.list().length != 0) builder.append("</ul>").append(System.lineSeparator());
        return builder;
    }
}