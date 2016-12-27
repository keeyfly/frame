package com.hx.novel.common.util;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Environment;

import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.File;

/**
 * Created by Administrator on 2016/9/26.
 */
public class FrescoHelper {
    public static final int MAX_SMALL_DISK_VERYLOW_CACHE_SIZE = 5 * ByteConstants.MB;//小图极低磁盘空间缓存的最大值（特性：可将大量的小图放到额外放在另一个磁盘空间防止大图占用磁盘空间而删除了大量的小图）
    public static final int MAX_SMALL_DISK_LOW_CACHE_SIZE = 10 * ByteConstants.MB;//小图低磁盘空间缓存的最大值（特性：可将大量的小图放到额外放在另一个磁盘空间防止大图占用磁盘空间而删除了大量的小图）
    public static final int MAX_SMALL_DISK_CACHE_SIZE = 20 * ByteConstants.MB;//小图磁盘缓存的最大值（特性：可将大量的小图放到额外放在另一个磁盘空间防止大图占用磁盘空间而删除了大量的小图）
    public static final int MAX_DISK_CACHE_VERYLOW_SIZE = 10 * ByteConstants.MB;//默认图极低磁盘空间缓存的最大值
    public static final int MAX_DISK_CACHE_LOW_SIZE = 30 * ByteConstants.MB;//默认图低磁盘空间缓存的最大值
    public static final int MAX_DISK_CACHE_SIZE = 50 * ByteConstants.MB;//默认图磁盘缓存的最大值
    private static final int MAX_HEAP_SIZE = (int) Runtime.getRuntime().maxMemory();//分配的可用内存
    public static final int MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 4;//使用的缓存数量
    private static final String IMAGE_PIPELINE_SMALL_CACHE_DIR = "Mvp_Image_SmallCache";//小图所放路径的文件夹名
    private static final String IMAGE_PIPELINE_CACHE_DIR = "Mvp_Image_BigCache";//默认图所放路径的文件夹名

    private static final String TAG = "FrescoHelper";
    static ControllerListener listener = new BaseControllerListener() {
        @Override
        public void onSubmit(String id, Object callerContext) {

        }

        @Override
        public void onFinalImageSet(String id, Object imageInfo, Animatable animatable) {
            //图片下载成功时调用
        }

        @Override
        public void onIntermediateImageSet(String id, Object imageInfo) {
            //如果允许呈现渐进式JPEG，同时图片也是渐进式图片，onIntermediateImageSet会在每个扫描被解码后回调
            LoggerHelper.LLog().e(TAG + "Intermediate image received");
        }

        @Override
        public void onIntermediateImageFailed(String id, Throwable throwable) {

        }

        @Override
        public void onFailure(String id, Throwable throwable) {
            //图片下载失败时调用
            LoggerHelper.LLog().e(TAG + "----- Error loading  %s", throwable);
        }

        @Override
        public void onRelease(String id) {

        }
    };
    private static ImagePipelineConfig sImagePipelineConfig;


    /**
     * 图片统一监听下载事件
     */

    /**
     * 初始化配置，单例
     */
    public static ImagePipelineConfig getImagePipelineConfig(Context context) {
        if (sImagePipelineConfig == null) {
            sImagePipelineConfig = configureCaches(context);
        }
        return sImagePipelineConfig;
    }

    /**
     * 初始化配置
     */
    public static Supplier<MemoryCacheParams> mSupplierMemoryCacheParams() {
        //内存配置
        final MemoryCacheParams bitmapCacheParams = new MemoryCacheParams(
                MAX_MEMORY_CACHE_SIZE, // 内存缓存中总图片的最大大小,以字节为单位。
                Integer.MAX_VALUE, // 内存缓存中图片的最大数量。
                MAX_MEMORY_CACHE_SIZE, // 内存缓存中准备清除但尚未被删除的总图片的最大大小,以字节为单位。
                Integer.MAX_VALUE, // 内存缓存中准备清除的总图片的最大数量。
                Integer.MAX_VALUE); // 内存缓存中单个图片的最大大小。

        //修改内存图片缓存数量，空间策略（这个方式有点恶心）
        return new Supplier<MemoryCacheParams>() {
            @Override
            public MemoryCacheParams get() {
                return bitmapCacheParams;
            }

        };
    }


    //默认图片的磁盘配置

    public static ImagePipelineConfig configureCaches(Context context) {
        //缓存图片配置
        ImagePipelineConfig.Builder configBuilder = ImagePipelineConfig.newBuilder(context)
                // .setAnimatedImageFactory(AnimatedImageFactory animatedImageFactory)//图片加载动画
                .setBitmapMemoryCacheParamsSupplier(mSupplierMemoryCacheParams())//内存缓存配置（一级缓存，已解码的图片）
                // .setCacheKeyFactory(cacheKeyFactory)//缓存Key工厂
                // .setEncodedMemoryCacheParamsSupplier(encodedCacheParamsSupplier)//内存缓存和未解码的内存缓存的配置（二级缓存）
                // .setExecutorSupplier(executorSupplier)//线程池配置
                // .setImageCacheStatsTracker(imageCacheStatsTracker)//统计缓存的命中率
                // .setImageDecoder(ImageDecoder imageDecoder) //图片解码器配置
                // .setIsPrefetchEnabledSupplier(Supplier<Boolean> isPrefetchEnabledSupplier)//图片预览（缩略图，预加载图等）预加载到文件缓存
                .setMainDiskCacheConfig(diskCacheConfig(context))//磁盘缓存配置（总，三级缓存）
                // .setMemoryTrimmableRegistry(memoryTrimmableRegistry) //内存用量的缩减,有时我们可能会想缩小内存用量。比如应用中有其他数据需要占用内存，不得不把图片缓存清除或者减小 或者我们想检查看看手机是否已经内存不够了。
                // .setNetworkFetchProducer(networkFetchProducer)//自定的网络层配置：如OkHttp，Volley
                // .setPoolFactory(poolFactory)//线程池工厂配置
                // .setProgressiveJpegConfig(progressiveJpegConfig)//渐进式JPEG图
                // .setRequestListeners(requestListeners)//图片请求监听
                // .setResizeAndRotateEnabledForNetwork(boolean resizeAndRotateEnabledForNetwork)//调整和旋转是否支持网络图片
                .setSmallImageDiskCacheConfig(diskSmallCacheConfig(context));//磁盘缓存配置（小图片，可选～三级缓存的小图优化缓存）
        return configBuilder.build();
    }

    public static DiskCacheConfig diskCacheConfig(Context context) {
        DiskCacheConfig.Builder cacheConfig = DiskCacheConfig.newBuilder(context);
        cacheConfig.setBaseDirectoryPath(getFiles());//缓存图片基路径
        cacheConfig.setBaseDirectoryName(IMAGE_PIPELINE_CACHE_DIR);//文件夹名
        cacheConfig.setCacheErrorLogger(new CacheErrorLogger() {
            @Override
            public void logError(CacheErrorCategory category, Class<?> clazz, String message, Throwable throwable) {
                LoggerHelper.LLog().e("FrescoUtil:: diskCacheConfig ------->" + message, throwable);
            }
        });
        cacheConfig.setMaxCacheSize(MAX_DISK_CACHE_SIZE);//默认缓存的最大大小。
        cacheConfig.setMaxCacheSizeOnLowDiskSpace(MAX_DISK_CACHE_LOW_SIZE);//缓存的最大大小,使用设备时低磁盘空间。
        cacheConfig.setMaxCacheSizeOnVeryLowDiskSpace(MAX_DISK_CACHE_VERYLOW_SIZE);//缓存的最大大小,当设备极低磁盘空间
        return cacheConfig.build();
    }

    //小图片的磁盘配置
    public static DiskCacheConfig diskSmallCacheConfig(Context context) {
        DiskCacheConfig diskSmallCacheConfig = DiskCacheConfig.newBuilder(context)
                .setBaseDirectoryPath(context.getApplicationContext().getCacheDir())//缓存图片基路径
                .setBaseDirectoryName(IMAGE_PIPELINE_SMALL_CACHE_DIR)//文件夹名
                // .setCacheErrorLogger(cacheErrorLogger)//日志记录器用于日志错误的缓存。
                // .setCacheEventListener(cacheEventListener)//缓存事件侦听器。
                // .setDiskTrimmableRegistry(diskTrimmableRegistry)//类将包含一个注册表的缓存减少磁盘空间的环境。
                .setMaxCacheSize(MAX_SMALL_DISK_CACHE_SIZE)//默认缓存的最大大小。
                .setMaxCacheSizeOnLowDiskSpace(MAX_SMALL_DISK_LOW_CACHE_SIZE)//缓存的最大大小,使用设备时低磁盘空间。
                .setMaxCacheSizeOnVeryLowDiskSpace(MAX_SMALL_DISK_VERYLOW_CACHE_SIZE)//缓存的最大大小,当设备极低磁盘空间
                // .setVersion(version)
                .build();
        return diskSmallCacheConfig;
    }

    public static File getFiles() {
        File file;
        if (sdCardExist()) {
            file = Environment.getExternalStorageDirectory();
        } else {
            file = Environment.getDataDirectory();
        }
        File files = new File(file, "MVP");
        if (!files.exists()) {
            files.mkdirs();
        }
        return files;
    }

    public static boolean sdCardExist() {
        //判断sd卡是否存在
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    //清除缓存
    public static void clearCaches() {
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.clearCaches();
        // imagePipeline.clearDiskCaches();
        // imagePipeline.clearMemoryCaches();
    }

    //渐进式JPEG图,图会从模糊到清晰渐渐呈现。
    public static DraweeController controller(DraweeView view, Uri uri) {
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setTapToRetryEnabled(true)
                .setOldController(view.getController())
                .setControllerListener(listener)
                .build();
        return controller;
    }
}
