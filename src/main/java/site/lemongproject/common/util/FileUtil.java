package site.lemongproject.common.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import site.lemongproject.web.photo.model.vo.Photo;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Component
public class FileUtil {
    final private String SAVE_PATH;

    public FileUtil() {
        this.SAVE_PATH =System.getProperty("catalina.home")+"/webapps";;
    }

    public Photo saveFile(MultipartFile m, Photo p){
        String creatPath=createFilePath();
        String realPath = SAVE_PATH+ creatPath;
            String changeName = rename(m.getOriginalFilename());
            String filePath = realPath + "/" + changeName;

            try (InputStream fis = m.getInputStream();
                 OutputStream fos = new FileOutputStream(filePath);) {
                byte[] buf = new byte[1024];
                int len = 0;
                while ((len = fis.read(buf, 0, 1024)) != -1) {
                    fos.write(buf, 0, len);
                }
                p = new Photo();
                p.setOriginName(m.getOriginalFilename()); // ex) 원본파일명 ("스프링.jpg")
                p.setChangeName(changeName); // ex) 2022101719265511111.jpg
                p.setFilePath(creatPath); // ex) /resources/images/userProfile/스프링로고.jpg
            } catch (IOException e) {
                e.printStackTrace();
            }
        return p;
    }

    public boolean deleteFile(Photo p){
        String filePath=SAVE_PATH+"/"+p.getFilePath()+"/"+p.getChangeName();
        File file=new File(filePath);
        if(file.exists()){
            return file.delete();
        }else{
            return false;
        }
    }
    private String createFilePath() {
        Calendar date=Calendar.getInstance();
        DateFormat spf =new SimpleDateFormat("yyyy/MM/dd");
        String[] paths = spf.format(date.getTime()).split("/");
        String result ="/file/"+paths[0]+"/"+ paths[1]+"/"+paths[2]+"/";
        createFolders(result);
        return result;
    }
    public String rename(String originName){
        String ext=originName.substring(originName.indexOf("."));
        String ranNum=String.valueOf((int)((Math.random()*90000)+10000));
        return ranNum+ext;
    }
    private void createFolders(String paths) {
        File folders = new File(SAVE_PATH, paths);
        if(!folders.exists())
            folders.mkdirs();
    }
}
