package ling.sport.originalSources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;
import org.omg.CORBA.portable.InputStream;

/**
 * 播放音乐资源类
 *
 */
public class Resource {
	public void getResource() throws IOException{  

        InputStream is=(InputStream) this.getClass().getResourceAsStream("/ima/warning_sound.mp3");
        BufferedReader br=new BufferedReader(new InputStreamReader(is));  
        
    }  

}
