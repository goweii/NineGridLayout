package per.goweii.ninegridlayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 *
 * @author Cuizhen
 * @date 2018/9/12
 */
public class NineBean {

    private String title;
    private boolean isBlur;
    private List<ImageBean> images;

    NineBean(String title, List<String> urls, boolean isBlur) {
        this.title = title;
        this.isBlur = isBlur;
        if (urls != null && urls.size() > 0) {
            images = new ArrayList<>(urls.size());
            for (String url : urls) {
                images.add(new ImageBean(url));
            }
        }
    }

    public String getTitle() {
        return title;
    }

    public List<ImageBean> getImages() {
        return images;
    }

    public boolean isBlur() {
        return isBlur;
    }

    public static class ImageBean{
        private String url;
        private boolean isBurn;

        public ImageBean(String url) {
            this.url = url;
            this.isBurn = false;
        }

        public String getUrl() {
            return url;
        }

        public boolean isBurn() {
            return isBurn;
        }

        public void setBurn() {
            isBurn = true;
        }

    }
}
