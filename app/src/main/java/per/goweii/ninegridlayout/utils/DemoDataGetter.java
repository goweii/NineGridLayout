package per.goweii.ninegridlayout.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 描述：测试数据
 *
 * @author Cuizhen
 * @date 18/9/11
 */
public class DemoDataGetter {

    private static final String[] IMAGES = new String[]{
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534143556631&di=cc87aaa9904614a2130be96d7ad766bb&imgtype=0&src=http%3A%2F%2Fpic14.nipic.com%2F20110603%2F2707401_231350824000_2.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=372363427,3129902920&fm=200&gp=0.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534143577030&di=85c26c81b4f720f5d69af2ad55066110&imgtype=0&src=http%3A%2F%2Fim6.leaderhero.com%2Fwallpaper%2Fuser%2F196%2F62f9ff2881f244c4b9da788334ed6440.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534143577030&di=5494fac993e9bd7425a5202dd53c70cc&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0177005965c8d0a8012193a3873505.jpeg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534143577030&di=41c41f3335cdea5586697dfb4f17159b&imgtype=0&src=http%3A%2F%2Fimg.25pp.com%2Fuploadfile%2Fbizhi%2Fiphone5%2F2014%2F0211%2F20140211032705478_ios.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534143577030&di=8976770117c190416bed257747828b78&imgtype=0&src=http%3A%2F%2Fim6.leaderhero.com%2Fwallpaper%2Fuser%2F200%2F91ad2cf9eb70411c985b8a72ad66bd81.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534143577029&di=6d2259ee6a7fdbe350441a4d6a4572c1&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fbf096b63f6246b60d904ee50e1f81a4c510fa288.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534143577029&di=2dd223f2db3cad96800712c2df0ec794&imgtype=0&src=http%3A%2F%2Ffile29.mafengwo.net%2FM00%2F03%2F04%2FwKgBpVYfryKAC_IXAAG0YEdvwnU54.jpeg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534143577028&di=fd7f412439713d8e5a3717c57bd9768a&imgtype=0&src=http%3A%2F%2Fdimg09.c-ctrip.com%2Fimages%2Ffd%2Ftg%2Fg3%2FM09%2F9A%2F08%2FCggYGlYhxeqANuj9AAJguSwVAQg261_R_1024_10000_Q90.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534143577028&di=6fe0b1b1335cae2ad1945a2d8adb7ce2&imgtype=0&src=http%3A%2F%2Fim6.leaderhero.com%2Fwallpaper%2F20150203%2Fc850e42b2b.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534143577028&di=4efd8e2779701528e87aa7c7d111f419&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fcb8065380cd79123d508bf61a7345982b2b7803e.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534143672640&di=f089c21e9bfb0f00cd9baffafdc41c34&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fa044ad345982b2b7cbbd46cc3badcbef76099b93.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534143672639&di=057814a684c7227146b8526758cee345&imgtype=0&src=http%3A%2F%2Ffile107.mafengwo.net%2Fs9%2FM00%2F2B%2F4E%2FwKgBs1aN_wuAE4hUAAaseLrBf2o97.jpeg%3FimageView2%2F2%2Fw%2F680%2Fq%2F90",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534143672639&di=a20e3db8db4f3477f63c34042ccb31f0&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F6d81800a19d8bc3edb6c0ad8888ba61ea8d345bf.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534143672639&di=6ee2da50264525f66931b9614ab317cc&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fsheying%2F20151014%2Fzhangjiajiefengjing_5089677.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534143672638&di=e21611e4e207eaac29c8d900d5c0acc3&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fcb8065380cd791235de73750a7345982b2b7808b.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534143672638&di=82a1d04f8498556be67387028f5ba1a5&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F32fa828ba61ea8d3ec57d6209d0a304e251f5898.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534143672638&di=ce8878a305f03e635b4fcbd4c0654846&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F242dd42a2834349bb3edc18dc3ea15ce37d3becb.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534143672638&di=0c2a70780567666ab17953c063688dd3&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fb3fb43166d224f4aeef0278d03f790529922d1c7.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534143672638&di=7fa307c4a5e6ce05f262c1af538338ec&imgtype=0&src=http%3A%2F%2Ffile27.mafengwo.net%2FM00%2F1E%2F70%2FwKgB6lPJ9KmAdNbLACdTxUJQiUM42.jpeg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534143672638&di=28704cee013ced38f3dc9fb8979097b6&imgtype=0&src=http%3A%2F%2Fn2-q.mafengwo.net%2Fs7%2FM00%2FFF%2F8B%2FwKgB6lTXcPqALlx8AAm-g1hzQzg63.jpeg%3FimageView2%2F2%2Fw%2F680%2Fq%2F90",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534143672638&di=8c2f084c82a16a03c38aa6b14168fea0&imgtype=0&src=http%3A%2F%2Fdimg03.c-ctrip.com%2Fimages%2Ffd%2Ftg%2Fg4%2FM07%2F43%2F70%2FCggYHFbMO9CALPhrAAHXzcut23g082.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534143845029&di=0ded85707cea8ef7374b00975ad795de&imgtype=0&src=http%3A%2F%2Fpic40.nipic.com%2F20140420%2F8676974_214219473154_2.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534143845028&di=17195add2f6ddf581b9084ee4ca35861&imgtype=0&src=http%3A%2F%2Fpic39.nipic.com%2F20140228%2F5571398_212748655172_2.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534143845028&di=704c061e57fe43676b40fb696df41ca3&imgtype=0&src=http%3A%2F%2Fimage13-c.poco.cn%2Fmypoco%2Fmyphoto%2F20120724%2F13%2F5669012220120724132930057.jpg"
    };

    public static List<String> getImgUrlList(int count) {
        List<String> urls = new ArrayList<>();
        if (count < 0) {
            count = 0;
        }
        for (int i = 0; i < count; i++) {
            urls.add(getRandomImgUrl());
        }
        return urls;
    }

    private static String getRandomImgUrl() {
        int index = new Random().nextInt(IMAGES.length);
        return getImgUrl(index);
    }

    private static int getIndex(int index){
        if (index < 0){
            return 0;
        }
        if (index >= IMAGES.length){
            return IMAGES.length;
        }
        return index;
    }

    private static String getImgUrl(int index){
        return IMAGES[getIndex(index)];
    }
}
