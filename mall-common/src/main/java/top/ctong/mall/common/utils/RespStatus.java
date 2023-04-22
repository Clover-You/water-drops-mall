package top.ctong.mall.common.utils;

/**
 * <p>
 * 业务响应状态
 * <p>
 * 格式：服务名_模块名_功能名称_具体错误
 * 1010000: 商品服务
 * </p>
 *
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-04-20 22:23
 */
public enum RespStatus {

    OK("20000", "successful"),

    FAIL("50000", "system fail"),

    BAD_REQUEST("10400", "bad request"),

    // ================== 商品服务 ================== //
    PRODUCT_CATEGORY_SAVE_NOT_EXIST("1010001", "父分类不存在"),

    PRODUCT_CATEGORY_SAVE_NAME_ALREADY_EXISTS("1010002", "分类名称不能重复"),

    PRODUCT_CATEGORY_QUERY_CATEGORY_NOT_EXIST("1010003", "分类不存在"),

    PRODUCT_CATEGORY_UPDATE_PARENT_NO_FOUND("1010004", "父级分类不存在"),

    PRODUCT_CATEGORY_UPDATE_ALREADY_EXIST_NAME("1010005", "分类名称不能重复");

    private final String code;

    private final String message;

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return this.code;
    }

    RespStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
