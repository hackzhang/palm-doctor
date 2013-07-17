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
    private List<? extends Map<String, ?>> mData;		//List�б��ŵ�����
    private int mResource;							//�󶨵�ҳ�� ,���磺R.layout.search_item, 
    private LayoutInflater mInflater;
    private String[] mFrom;							//�󶨿ؼ���Ӧ�����������ֵ����
    private int[] mTo;								//�󶨿ؼ���ID	
    
    //������
    public SearchAdapter(Context context, List<? extends Map<String, ?>> data,int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        mData = data;
        mResource = resource;
        mFrom = from;
        mTo = to;
        // ���ֱ�(LayoutInflater)����XML�����ļ���������ͼ(View)����������޷�ֱ�Ӵ���ʵ����Ҫͨ��context�����getLayoutInflater()��getSystemService(String)���������ʵ����������õĲ��ֱ�ʵ�������豸�Ļ������á�
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    	/*
     	SimpleAdapter������ʾÿ��Item����ͨ������������ɵ�
    	��getView(int position, View convertView, ViewGroup parent)���ֵ�����SimpleAdapter��˽�з���createViewFromResource
    	����װView����createViewFromResource�ж�SimpleAdapter�Ĳ���String[] from ��int[] to��������װ   
    	 */
    @Override
	public View getView(int position, View convertView, ViewGroup parent) {
        return createViewFromResource(position, convertView, parent, mResource);	//�������淽��
    }

 
    //��createViewFromResource����������һ��bindView(position, v)������item�еĸ���View��������װ��bindView(position, v)
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
            bindView(position, rowView);	//�������淽����Item�е�
            viewMap.put(position, rowView);
        }
        return rowView;
    }
    
    //��ViewImage������װ�Ĵ����ˡ�else if (v instanceof ImageView)��
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
    	 //���ֻ�ǵ����İ�ͼƬ��ʾ���������л��档ֱ��������ķ����õ�URL��Bitmap������ʾ��OK
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
