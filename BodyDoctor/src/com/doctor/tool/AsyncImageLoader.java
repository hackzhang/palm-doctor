package com.doctor.tool;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

public class AsyncImageLoader {
    private Map<String, SoftReference<Drawable>> imageCache = new HashMap<String, SoftReference<Drawable>>();

    public Drawable loadDrawable(final String imageUrl,final ImageCallback callback) {
    	//����, ���ǿ���ͨ��ʹ�� Map ��containsKey() ����������Ƿ������Ƿ����, ���key����, ������Ѿ���ȡ��һ������, 
    	//��ôֱ�ӷ��ظ� key �� Map �е�ֵ. �����Ƿ�Ϊ null ��ֱ�ӷ���; ��� key ������, ��ȥ���ɻ��߻�ȡ����, �����뵽 Map ��, �����ظ�����.
    	// ����ʹ�� containsKey() ��������Ӧ����: 1. �������Է���ȡ�����ݿ���Ϊ��, ���Ҳ����б仯; 2. ��ȡ���ݱȽϺ�ʱ. ���������, ʹ�ø÷������Դ�󽵵�����, �ر�����ͬ�������.

    	if (imageCache.containsKey(imageUrl)) {
            SoftReference<Drawable> softReference = imageCache.get(imageUrl);
            if (softReference.get() != null) {
                return softReference.get();
            }
        }
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                callback.imageLoaded((Drawable) msg.obj, imageUrl);
            }
        };
        //load data
        new Thread() {
            @Override
			public void run() {
                Drawable drawable = loadImageFromUrl(imageUrl);
                imageCache.put(imageUrl, new SoftReference<Drawable>(drawable));
                handler.sendMessage(handler.obtainMessage(0, drawable));
            };
        }.start();
        
        return null;
    }

    protected Drawable loadImageFromUrl(String imageUrl) {
        try {
            return Drawable.createFromStream(new URL(imageUrl).openStream(),
                    "src");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //call back interface
    public interface ImageCallback {
        public void imageLoaded(Drawable imageDrawable, String imageUrl);
    }
    
}
