package site.lemongproject.common.util;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import site.lemongproject.web.photo.model.vo.Photo;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

@Component
public class FileUtil {
    final private String SAVE_PATH;

    public FileUtil() {
        this.SAVE_PATH =System.getProperty("catalina.home")+"/webapps";;
    }

    public Photo saveFile(MultipartFile m,Photo p){
        String creatPath=createFilePath();
        String changeName=rename(m.getOriginalFilename());
        String filePath = SAVE_PATH+ creatPath +"/"+ changeName;
        try{
            m.transferTo(new File(filePath));
            p=new Photo();
            p.setOriginName(m.getOriginalFilename());
            p.setChangeName(changeName);
            p.setFilePath(creatPath);
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
