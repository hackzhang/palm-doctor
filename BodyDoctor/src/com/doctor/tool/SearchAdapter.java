package com.doctor.tool;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class SearchAdapter extends SimpleAdapter {
    
    private AsyncImageLoader imageLoader = new AsyncImageLoader();
    private Map<Integer, View> viewMap = new HashMap<Integer, View>();
    private ViewBinder mViewBinder;
    private List<? extends Map<String, ?>> mData;		//List列表存放的数据
    private int mResource;							//绑定的页面 ,例如：R.layout.search_item, 
    private LayoutInflater mInflater;
    private String[] mFrom;							//绑定控件对应的数组里面的值名称
    private int[] mTo;								//绑定控件的ID	
    
    //构造器
    public SearchAdapter(Context context, List<? extends Map<String, ?>> data,int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        mData = data;
        mResource = resource;
        mFrom = from;
        mTo = to;
        // 布局泵(LayoutInflater)根据XML布局文件来绘制视图(View)对象。这个类无法直接创建实例，要通过context对象的getLayoutInflater()或getSystemService(String)方法来获得实例，这样获得的布局泵实例符合设备的环境配置。
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    	/*
     	SimpleAdapter基类显示每个Item都是通过这个方法生成的
    	在getView(int position, View convertView, ViewGroup parent)中又调用了SimpleAdapter的私有方法createViewFromResource
    	来组装View，在createViewFromResource中对SimpleAdapter的参数String[] from 和int[] to进行了组装   
    	 */
    @Override
	public View getView(int position, View convertView, ViewGroup parent) {
        return createViewFromResource(position, convertView, parent, mResource);	//调用下面方法
    }

 
    //在createViewFromResource方法中又有一个bindView(position, v)方法对item中的各个View进行了组装，bindView(position, v)
    private View createViewFromResource(int position, View convertView, ViewGroup parent, int resource) {
        View rowView = this.viewMap.get(position);
        
        if (rowView == null) {
            rowView = mInflater.inflate(resource, null);

            final int[] to = mTo;
            final int count = to.length;
            final View[] holder = new View[count];

            for (int i = 0; i < count; i++) {


                holder[i] = rowView.findViewById(to[i]);
            }
            rowView.setTag(holder);
            bindView(position, rowView);	//调用下面方法对Item中的
            viewMap.put(position, rowView);
        }
        return rowView;
    }
    
    //对ViewImage进行组装的代码了“else if (v instanceof ImageView)”
    @SuppressWarnings("rawtypes")
	private void bindView(int position, View view) {
        final Map dataSet = mData.get(position);
        if (dataSet == null) {
            return;
        }

        final ViewBinder binder = mViewBinder;
        final View[] holder = (View[]) view.getTag();
        final String[] from = mFrom;
        final int[] to = mTo;
        final int count = to.length;

        for (int i = 0; i < count; i++) {
            final View v = holder[i];
            if (v != null) {
                final Object data = dataSet.get(from[i]);
                String urlText = null;

                if (data == null) {
                    urlText = "";
                } else {
                    urlText = data.toString();
                }

                boolean bound = false;
                if (binder != null) {
                    bound = binder.setViewValue(v, data, urlText);
                }

                if (!bound) {
                    if (v instanceof Checkable) {
                        if (data instanceof Boolean) {
                            ((Checkable) v).setChecked((Boolean) data);
                        } else {
                            throw new IllegalStateException(v.getClass()
                                    .getName()
                                    + " should be bound to a Boolean, not a "
                                    + data.getClass());
                        }
                    } else if (v instanceof TextView) {
                        setViewText((TextView) v, urlText);
                    } else if (v instanceof ImageView) {
                        if (data instanceof Integer) {
                            setViewImage((ImageView) v, (Integer) data);
                        } else {
                            setViewImage((ImageView) v, urlText);
                        }
                    } else {
                        throw new IllegalStateException(
                                v.getClass().getName()
                                        + " is not a "
                                        + " view that can be bounds by this SimpleAdapter");
                    }
                }
            }
        }
    }


     @Override
	public void setViewImage(ImageView v, int value) {
         v.setImageResource(value);
     }

     @Override
	public void setViewImage(final ImageView v, String url) {
    	 //如果只是单纯的把图片显示，而不进行缓存。直接用下面的方法拿到URL的Bitmap就行显示就OK
//       Bitmap bitmap = WebImageBuilder.returnBitMap(url);
//       ((ImageView) v).setImageBitmap(bitmap);

         imageLoader.loadDrawable(url, new AsyncImageLoader.ImageCallback() {
             public void imageLoaded(Drawable imageDrawable, String imageUrl) {
                 if(imageDrawable!=null && imageDrawable.getIntrinsicWidth()>0 ) {
                     v.setImageDrawable(imageDrawable);
                 }
             }
         });
     }

 } 
