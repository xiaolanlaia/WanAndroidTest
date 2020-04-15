package com.wjf.dev.common.cookie;

import androidx.annotation.NonNull;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import java.util.List;

/**
 * @author maoqitian
 * @Description cookie 管理类 继承
 * @Time 2018/12/5 0005 20:41
 */
public class CookieManager implements CookieJar {


    private static volatile CookieManager cookieManager;

    private final PersistentCookieStore persistentCookieStore;

    //双重效验锁实现单例
    public static CookieManager getInstance(){
        if(cookieManager  == null){
            synchronized (CookieManager.class){
                if(cookieManager  == null){
                    cookieManager=new CookieManager();
                }
            }
        }
        return cookieManager;
    }

    public CookieManager(){
        persistentCookieStore=new PersistentCookieStore();
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        persistentCookieStore.add(url,cookies);
    }

    @NonNull
    @Override
    public List<Cookie> loadForRequest(@NonNull HttpUrl url) {
        List<Cookie> cookies = persistentCookieStore.get(url);
        return persistentCookieStore.get(url);
    }


    public void clearAllCookie(){
        persistentCookieStore.removeAll();
    }
}
