package utils;

import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;

public class KJHttpUtil {

    /**
     * 下载apk
     */
    public static void upAPK(String path, String url, HttpCallBack callBack) {
        KJHttp kjh = new KJHttp();
        kjh.download(path, url, callBack);
    }

}
