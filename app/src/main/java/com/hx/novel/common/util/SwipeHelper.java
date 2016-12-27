/*
package com.hx.novel.common.util;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.view.mvp.R;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemMovementListener;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemStateChangedListener;

import java.util.Collections;
import java.util.List;

*/
/**
 * Created by 李贺翔 on 2016/12/6.
 * Description:SwipeRecyclerView的一些事件监听
 *//*

public class SwipeHelper {

    */
/*
    *
        mSwipeMenuRecyclerView.setSwipeMenuCreator(swipeMenuCreator); // 为SwipeRecyclerView的Item创建菜单
        mSwipeMenuRecyclerView.setLongPressDragEnabled(true);// 开启拖拽。
        mSwipeMenuRecyclerView.setOnItemMoveListener(onItemMoveListener);// 监听拖拽，更新UI。
        mSwipeMenuRecyclerView.setItemViewSwipeEnabled(true);// 开启滑动删除。
        mSwipeMenuRecyclerView.setOnItemMoveListener(onItemMoveListener);// 监听拖拽，更新UI。
        mSwipeMenuRecyclerView.setOnItemMovementListener(onItemMovementListener);//当Item被移动之前相关监听设置。
        mSwipeMenuRecyclerView.setOnItemStateChangedListener(mOnItemStateChangedListener);//Item的滑动状态发生变化监听。
    *
    * *//*



    */
/**
     * 当Item被移动之前。
     *//*

    public static OnItemMovementListener onItemMovementListener = new OnItemMovementListener() {
        */
/**
         * 当Item在移动之前，获取拖拽的方向。
         * @param recyclerView     {@link RecyclerView}.
         * @param targetViewHolder target ViewHolder.
         * @return
         *//*

        @Override
        public int onDragFlags(RecyclerView recyclerView, RecyclerView.ViewHolder targetViewHolder) {
            // 我们让第一个不能拖拽。
            if (targetViewHolder.getAdapterPosition() == 0) {
                return OnItemMovementListener.INVALID;// 返回无效的方向。
            }

            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {// 如果是LinearLayoutManager。
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                if (linearLayoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL) {// 横向的List。
                    return (OnItemMovementListener.LEFT | OnItemMovementListener.RIGHT); // 只能左右拖拽。
                } else {// 竖向的List。
                    return OnItemMovementListener.UP | OnItemMovementListener.DOWN; // 只能上下拖拽。
                }
            } else if (layoutManager instanceof GridLayoutManager) {// 如果是Grid。
                return OnItemMovementListener.LEFT | OnItemMovementListener.RIGHT | OnItemMovementListener.UP | OnItemMovementListener.DOWN; // 可以上下左右拖拽。
            }
            return OnItemMovementListener.INVALID;// 返回无效的方向。
        }

        @Override
        public int onSwipeFlags(RecyclerView recyclerView, RecyclerView.ViewHolder targetViewHolder) {
            // 我们让第一个不能滑动删除。
            if (targetViewHolder.getAdapterPosition() == 0) {
                return OnItemMovementListener.INVALID;// 返回无效的方向。
            }

            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {// 如果是LinearLayoutManager
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                if (linearLayoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL) {// 横向的List。
                    return OnItemMovementListener.UP | OnItemMovementListener.DOWN; // 只能上下滑动删除。
                } else {// 竖向的List。
                    return OnItemMovementListener.LEFT | OnItemMovementListener.RIGHT; // 只能左右滑动删除。
                }
            }
            return OnItemMovementListener.INVALID;// 其它均返回无效的方向。
        }
    };
    */
/**
     * Item的滑动状态发生变化监听。
     *//*

    public OnItemStateChangedListener mOnItemStateChangedListener = new OnItemStateChangedListener() {
        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            if (actionState == ACTION_STATE_DRAG) {
                //状态：拖拽";
            } else if (actionState == ACTION_STATE_SWIPE) {
                //状态：滑动删除;
            } else if (actionState == ACTION_STATE_IDLE) {
                //状态：手指松开;
            }
        }
    };
    private Context mContext;
    */
/**
     * 菜单创建器。
     *//*

    public SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = mContext.getResources().getDimensionPixelSize(R.dimen.dimen_150dp);
            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            SwipeMenuItem deleteItem = new SwipeMenuItem(mContext)
                    .setImage(R.mipmap.ic_action_delete) // 图标。
                    .setBackgroundColor(mContext.getResources().getColor(R.color.red))
                    .setText("删除") // 文字。
                    .setTextColor(Color.WHITE) // 文字颜色。
                    .setTextSize(16) // 文字大小。
                    .setWidth(width)
                    .setHeight(height);
            swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。.
        }
    };
    */
/**
     * 菜单点击监听。
     *//*

    public OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                Toast.makeText(mContext, "list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(mContext, "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            }
        }
    };
    private List<String> mList;
    private SwipeMenuAdapter mAdapter;
    */
/**
     * 当Item移动的时候。（拖拽）
     *//*

    public OnItemMoveListener onItemMoveListeners = new OnItemMoveListener() {
        @Override
        public boolean onItemMove(int fromPosition, int toPosition) {
            if (mList != null) {
                // 当Item被拖拽的时候。
                Collections.swap(mList, fromPosition, toPosition);
                mAdapter.notifyItemMoved(fromPosition, toPosition);
                return true;// 返回true表示处理了，返回false表示你没有处理。
            } else {
                return false;
            }

        }

        @Override
        public void onItemDismiss(int position) {
            // 当Item被滑动删除掉的时候，在这里是无效的，因为这里没有启用这个功能。
            // 使用Menu时就不用使用这个侧滑删除啦，两个是冲突的。
        }
    };


    */
/**
     * 当Item移动的时候。(滑动)
     *//*

    public OnItemMoveListener onItemMoveListener = new OnItemMoveListener() {
        @Override
        public boolean onItemMove(int fromPosition, int toPosition) {
            if (mList != null) {
                Collections.swap(mList, fromPosition, toPosition);
                mAdapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void onItemDismiss(int position) {
            mList.remove(position);
            mAdapter.notifyItemRemoved(position);
            Toast.makeText(mContext, "现在的第" + position + "条被删除。", Toast.LENGTH_SHORT).show();
        }

    };

    public SwipeHelper(Context context) {
        mContext = context;
    }

    //滑动删除后刷新数据
    public void refreshList(SwipeMenuAdapter adapter, List<String> list) {
        mList = list;
        mAdapter = adapter;
    }
}
*/
