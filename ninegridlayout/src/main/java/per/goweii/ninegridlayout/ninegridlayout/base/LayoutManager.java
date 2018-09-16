package per.goweii.ninegridlayout.ninegridlayout.base;

import per.goweii.ninegridlayout.ninegridlayout.Place;

/**
 * 描述：
 *
 * @author Cuizhen
 * @date 2018/9/12
 */
public interface LayoutManager {

    int[] getParentSize(int childCount, int gridSpace);

    int getMaxChildCount();

    Place getChildPosition(int position, int columnCount, int gridWidth, int gridHeight, int gridSpacing);
}
