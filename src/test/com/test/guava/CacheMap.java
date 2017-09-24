package com.test.guava;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Weigher;
import org.junit.Test;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * guava 提供了k,v缓存功能，适用于以下场景 1 能接受一定内存消耗，换取速度 2 某些 k 会被查询一次以上 3 缓存中的数据量不会超过内存容量
 *
 */
public class CacheMap {

    /**
     * 构建一个10个容量的缓存map,cacheLoader实现 v load(k) 用于缓存map加载key不存在时，进行的
     * reload操作，由于load过程可能会出错。当抛异常时getUnchecked会出错，而get会处理异常
     */
    @Test
    public void CacheLoader() {
        LoadingCache<String, Integer> cacheMap =
                CacheBuilder.newBuilder().maximumSize(10).build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String key) {
                        return getValueIfNull(key);
                    }

                });
        System.out.println(cacheMap.getUnchecked("1"));
        System.out.println(cacheMap.getUnchecked("test1"));
        System.out.println(cacheMap.getUnchecked("test"));
    }

    /**
     * 构建一个10个容量的缓存map,cacheLoader实现 v load(k) 用于缓存map加载key不存在时，进行的
     * reload操作，由于load过程可能会出错。get会处理异常
     */
    @Test
    public void cacheloaderWithExecption() {
        LoadingCache<String, Integer> cacheMap =
                CacheBuilder.newBuilder().maximumSize(10).build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String key) throws Exception {
                        return getValueIfNull(key);
                    }
                });
        try {
            System.out.println(cacheMap.get("1"));
        } catch (Exception e) {// ExecutionException 是hold不住所有的异常的！
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(cacheMap.get("2"));
        } catch (Exception e) {// ExecutionException 是hold不住所有的异常的！
            System.out.println(e.getMessage());
        }
    }

    /**
     * 与cacheload 类似，当k不存在时，调用callable。
     * 实现了有缓存则返回，否则运算，缓存，返回
     */
    @Test
    public void callAble(){
        Cache<String, Integer> cacheMap =
                CacheBuilder.newBuilder().maximumSize(10).build();
        try {
            Integer res = cacheMap.get("111", new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return getValueIfNull("111");
                }
            });
            System.out.println(res);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 缓存回收：1 基于容量的回收，2 定时回收 3 基于引用的回收
     * A：基于容量的回收 size-based evication
     * 当缓存项目达到限定之前，只需要使用maximumsize 就能回收最近没用或少用的缓存项目
     * 另外可以通过权重来回收空间
     */
    @Test
    public void gcOfSizeBase(){
        LoadingCache<String, Integer> cacheMap =
                CacheBuilder.newBuilder()
//                        .maximumSize(3)
                        .maximumWeight(Integer.MAX_VALUE)
                        .weigher(new Weigher<String, Integer>() {
                            @Override
                            public int weigh(String key, Integer value) {
                                return value;
                            }
                        })
                        .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String key) throws Exception {
                        return getValueIfNull(key);
                    }
                });
    }

    /**
     * 缓存回收：1 基于容量的回收，2 定时回收 3 基于引用的回收
     * B：定时回收
     * expireAfterAccess 缓存项在给定时间被 读/写访问后，则回收
     * expireAfterWrite 缓存项在给定时间被 写 后，则回收
     * 多数场景是expireAfterWrite
     */
    @Test
    public void gcTimed() throws InterruptedException {
        LoadingCache<String, Integer> cacheMap =
                CacheBuilder.newBuilder().maximumSize(10)
                        .expireAfterAccess(5, TimeUnit.SECONDS)//读/写 10秒后过期
//                        .expireAfterWrite(10,TimeUnit.SECONDS) //写后10秒过期
                        .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String key) {
                        return getValueIfNull(key);
                    }
                });
        System.out.println(cacheMap.getUnchecked("123"));
        System.out.println(cacheMap.getUnchecked("123"));
        Thread.sleep(6000);
        System.out.println("wakeup after 6 seconds.. it will be evication");
        System.out.println(cacheMap.getUnchecked("123"));
    }

    /**
     * 缓存回收：1 基于容量的回收，2 定时回收 3 基于引用的回收
     * C：基于引用的回收
     * 通过使用弱引用的键，值，软引用的值 Guava Cache可以把缓存设置为允许垃圾回收
     *
     */
    @Test
    public void gcRef(){
        //TODO
    }

    private Integer getValueIfNull(String key) {
        Integer v = new Random().nextInt();
        if (key.equals("1")) {
            v = 1 / 0;// 模拟异常
        }
        System.out.println("getvalueif cache is null K:" + key + " V:" + v);
        return v;
    }
}
