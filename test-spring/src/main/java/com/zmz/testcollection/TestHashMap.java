package com.zmz.testcollection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

public class TestHashMap {

    private static HashMap<String, String> hashMap = new HashMap<>();

    private TreeMap<String, String> treeMap;

    public TestHashMap() {
        hashMap = new HashMap<>(16);
    }

    public static void main(String[] args) {

        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");
        // hashmap中的modCount++
        // 主要是为了解决迭代器遍历的问题
        Iterator<String> iterator = hashMap.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            String s = hashMap.get(key);
            System.err.println(s);
            // 这种remove 会导致modCount不等于expectCount
            // 为什么要抛这种异常
            // 是为了fast-fail这种机制
            // 因为hashmap不是线程安全的 当他发现有一个线程在修改，另一个线程
            // 在遍历 hashmap会直接失败
            hashMap.remove(key);
            // 这种remove不会抛异常
            iterator.remove();
        }

    }
    /**
     * jdk8种红黑树 平衡操作 与左旋右旋源码解析
     *
     * 阅读时 手动取消注释
     */
//
//    static <K,V> HashMap.TreeNode<K,V> balanceInsertion(HashMap.TreeNode<K,V> root,
//                                                        HashMap.TreeNode<K,V> x) {
//        // root 表示当前红黑树的根节点  x为当前插入的节点 默认新插入的就是红节点
//        x.red = true;
//        // xp表示x的父节点 xpp表示x的祖父节点  xppl表示xpp的左子节点 xppr表示右子节点
//        for (HashMap.TreeNode<K,V> xp, xpp, xppl, xppr;;) {
//            if ((xp = x.parent) == null) {
//                // 递归的终止条件 x已经递归平衡旋转至最上层了 直接终止返回
//                x.red = false;
//                return x;
//            }
//            else if (!xp.red || (xpp = xp.parent) == null) {
//                // 当父节点为黑色节点时 不需调整
//                // 或者当祖父节点为null时也不需调整 此时即使新插入x 也是只有两层 仍满足红黑树的定义
//                return root;
//            }
//            // 以下所有前提都是 父节点为红色
//            if (xp == (xppl = xpp.left)) {
//                // 当要插入的节点要放在xpp左侧时 也就是xp==xppl
//                if ((xppr = xpp.right) != null && xppr.red) {
//                    // 这种为红黑树的第一种调整策略
//                    // 如果叔叔节点为红色(因为此时xppl==xp，因此xppr就是叔叔节点)
//                    // 那么只需 父节点 叔叔节点 变黑 祖父节点变红
//                    xppr.red = false;
//                    xp.red = false;
//                    xpp.red = true;
//                    // 这一步是因为这个操作是递归的 由于xpp的颜色发生了变化 现有操作只能保证 xpp xp x这部分子树满足红黑树架构
//                    // 因此需要令x=xpp 表明继续通过for循环继续调整红黑树的平衡性
//                    x = xpp;
//                }
//                else {
//                    // 这个else表明没有叔叔节点 或者是叔叔节点为黑色 此时需要旋转加变色
//                    if (x == xp.right) {
//                        // 如果是插入到父节点的右侧 需要先左旋一次 再接着下面的if判断右旋一次
//                        // 左旋逻辑
//                        root = rotateLeft(root, x = xp);
//                        xpp = (xp = x.parent) == null ? null : xp.parent;
//                    }
//                    if (xp != null) {
//                        // 无论插入左侧右侧 最终都需要右旋一次
//                        xp.red = false;
//                        if (xpp != null) {
//                            xpp.red = true;
//                            // 右旋逻辑
//                            root = rotateRight(root, xpp);
//                        }
//                    }
//                }
//            }
//            else {
//                // 当要插入的节点要放在xpp右侧时 也就是xp==xppr
//                if (xppl != null && xppl.red) {
//                    xppl.red = false;
//                    xp.red = false;
//                    xpp.red = true;
//                    x = xpp;
//                }
//                else {
//                    if (x == xp.left) {
//                        root = rotateRight(root, x = xp);
//                        xpp = (xp = x.parent) == null ? null : xp.parent;
//                    }
//                    if (xp != null) {
//                        xp.red = false;
//                        if (xpp != null) {
//                            xpp.red = true;
//                            root = rotateLeft(root, xpp);
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    static <K,V> HashMap.TreeNode<K,V> rotateLeft(HashMap.TreeNode<K,V> root,
//                                                  HashMap.TreeNode<K,V> p) {
//        HashMap.TreeNode<K,V> r, pp, rl;
//        if (p != null && (r = p.right) != null) {
//            // 左旋时 r为圆心 p为绕圆心旋转的节点
//            if ((rl = p.right = r.left) != null)
//                // 如果r的左子节点本身有值 那么就无法以r为圆心左旋 需要先改值
//                // 把r.left赋值给p.right
//                // p.right赋值给 其实rl只是一个赋值 rl还是之前r的left节点 但是r.left已经赋值给p.right了
//                // 因此代码体现上是rl=p.right
//                // 最后的rl.parent=p 完成左旋前的前置条件
//                rl.parent = p;
//            if ((pp = r.parent = p.parent) == null)
//                // r的parent 修改为 p原来的parent 因为以r为圆心 p是待旋转节点
//                // pp其实还是个赋值 就是代表了原有的p节点的parent
//                // 如果赋值完发现pp为空 也就是pp是根节点
//                // 那么根据红黑树的定义 根节点就是原来的圆心r 且要颜色变为黑色
//                (root = r).red = false;
//            else if (pp.left == p)
//                // 这就是两种赋值情况 如果之前p是pp的左节点 那么r也应该是pp的左节点
//                pp.left = r;
//            else
//                // 如果之前p是pp的右节点 那么r也应该是pp的右节点
//                pp.right = r;
//            // 这两步是公共步骤
//            r.left = p;
//            p.parent = r;
//        }
//        return root;
//    }
//
//    static <K,V> HashMap.TreeNode<K,V> rotateRight(HashMap.TreeNode<K,V> root,
//                                                   HashMap.TreeNode<K,V> p) {
//        HashMap.TreeNode<K,V> l, pp, lr;
//        if (p != null && (l = p.left) != null) {
//            if ((lr = p.left = l.right) != null)
//                lr.parent = p;
//            if ((pp = l.parent = p.parent) == null)
//                (root = l).red = false;
//            else if (pp.right == p)
//                pp.right = l;
//            else
//                pp.left = l;
//            l.right = p;
//            p.parent = l;
//        }
//        return root;
//    }


}
