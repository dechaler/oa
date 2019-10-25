package za;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @编写人:de
 * @时间:2019/10/14
 * @描述:
 */
public class FIleTest {
    public static void main(String[] args) throws IOException {
        File file = new File("d:\\a.txt");
        InputStream is = new FileInputStream(file);
//        File tmpFile = file;
        File rollFile = new File("src\\main\\webapp\\WEB-INF\\" + file.getName() );
        if (!file.exists())
            file.createNewFile();
        else{
            file.delete();
//            FileUtils.copyFile(tmpFile,rollFile);
            FileUtils.copyToFile(is,rollFile);
        }


//


//        String path = file.getPath();
//        String absolutePath = file.getAbsolutePath();
//        String canonicalPath = file.getCanonicalPath();
        System.out.println("path = " + rollFile.getPath());
//        System.out.println("absolutePath = " + absolutePath);
//        System.out.println("canonicalPath = " + canonicalPath);
    }
//    public void createFile(File file){
}


