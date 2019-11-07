package za;

import com.de.Utils.MyFileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @编写人:de
 * @时间:2019/10/14
 * @描述:
 */
public class FIleTest {
    public static void main(String[] args) throws IOException {
//        File file = new File("d:\\text.txt");
//        InputStream is = new FileInputStream(file);
////        File tmpFile = file;
//        File rollFile = new File(MyFileUtils.UPLOAD_PATH + "/" + file.getName() );
////        if (!file.exists())
////            file.createNewFile();
////        else{
////            file.delete();
////            FileUtils.copyFile(tmpFile,rollFile);
////            FileUtils.copyToFile(is,rollFile);
//        rollFile.createNewFile();
            cte();
        }

        public static void cte() throws IOException {
            File file = new File("d:\\text1.txt");
            String path = MyFileUtils.WIN_PATH;

            File rollFile = new File(path,file.getName());
//        if (!file.exists())
//            file.createNewFile();
//        else{
//            file.delete();
//            FileUtils.copyFile(tmpFile,rollFile);
//            FileUtils.copyToFile(is,rollFile);
            rollFile.mkdir();
            rollFile.createNewFile();
        }


//


//        String path = file.getPath();
//        String absolutePath = file.getAbsolutePath();
//        String canonicalPath = file.getCanonicalPath();
//        System.out.println("absolutePath = " + absolutePath);
//        System.out.println("canonicalPath = " + canonicalPath);
    }
//    public void createFile(File file){



