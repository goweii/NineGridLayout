package per.goweii.ninegridlayout.ninegridlayout.manager;

import per.goweii.ninegridlayout.ninegridlayout.Place;
import per.goweii.ninegridlayout.ninegridlayout.base.LayoutManager;

/**
 * 描述：以田字格从左到右从上到下依次排列
 *
 * @author Cuizhen
 * @date 2018/9/12
 */
public class LinearLayoutManager implements LayoutManager {

    @Override
    public int[] getParentSize(int childCount, int gridSpace) {
        return new int[0];
    }

    @Override
    public int getMaxChildCount() {
        return 0;
    }

    @Override
    public Place getChildPosition(int position, int columnCount, int gridWidth, int gridHeight, int gridSpacing) {
        return null;
    }
}
