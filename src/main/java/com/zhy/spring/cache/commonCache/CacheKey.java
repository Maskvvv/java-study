package com.zhy.spring.cache.commonCache;

/**
 * 缓存 Key 接口，用于统一管理缓存键的命名规范
 *
 * 功能说明：
 * - 通过实现该接口（通常由枚举实现）统一维护缓存命名空间与静态键，避免魔法值
 * - 与 {@link CacheKeyUtils} 组合使用，可根据业务参数生成最终字符串键（namespace:key:biz1:biz2...）
 *
 * 使用建议：
 * - namespace 建议使用模块或业务域名，确保全局唯一且稳定
 * - key 建议使用具备业务含义的静态标识，便于后续维护与排查
 *
 */
public interface CacheKey {

    /**
     * 返回缓存命名空间
     *
     * 说明：命名空间用于隔离不同业务模块的缓存键，建议保持稳定且唯一
     *
     * @return 命名空间字符串（不可为空）
     */
    String namespace();

    /**
     * 返回静态缓存键名
     *
     * 说明：静态键表示某一类数据的固定标识，最终键由 {@link CacheKeyUtils}
     * 结合命名空间与可选业务参数拼接得到
     *
     * @return 静态键名（不可为空）
     */
    String key();
}