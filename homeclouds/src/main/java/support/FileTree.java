package support;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public abstract class FileTree {
    public static String GetFiles(String login){
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
            builder.append("<li>").append(file.getName());
            if (file.isDirectory() ){
                builder = RecursiveFileSearch(file, builder);
            }
            builder.append("</li>");
        }
        if (Objects.requireNonNull(fileTree.list()).length != 0) builder.append("</ul>").append(System.lineSeparator());
        return builder;
    }
    
    public static String ExploreDirectory(String login){
        String absPath = AbsPath.path + login;
        File fileTree = new File(absPath);
        String strDiv = System.lineSeparator();
        StringBuilder builder = new StringBuilder("");
        builder.append("<div class = \"explorer\">").append(strDiv).append("<form method=\"post\" action=\"\"> ")
                .append(strDiv);

        for (File file : Objects.requireNonNull(fileTree.listFiles())){
            builder.append("<div class = \"file\">").append(strDiv).append("<button type = \"submit\" value=\"");
            builder.append(file.getName()).append("\" name=\"path\">").append(strDiv);
            String fileExtension = "";
            int i = file.getName().lastIndexOf(".");
            if (i > 0)
                fileExtension = file.getName().substring(i + 1);
            if (file.isDirectory()) fileExtension = "folder";
            builder.append("<img src=\"").append(".." + File.separator + ".." + File.separator + "imgs" + File.separator
                    + fileExtension + ".png").append("\">");
            builder.append("<p>").append(file.getName()).append("</p>");
            builder.append("</button>").append(strDiv).append("</div>").append(strDiv);
        }
        builder.append("</form>").append(strDiv).append("</div>").append(strDiv);
        return builder.toString();
    }

    public static String CreateFolder(String path){
        File folder = new File(path);
        try {
            if (folder.createNewFile())
                return "Папка успешно создана";
            else return "Такая папка уже существует";
        }
        catch (IOException e){
            return "Не удалось создать файл. Ошибка: " + e.getMessage();
        }
    }
}