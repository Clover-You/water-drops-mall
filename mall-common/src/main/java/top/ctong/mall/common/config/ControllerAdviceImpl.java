package top.ctong.mall.common.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ctong.mall.common.exception.BaseException;
import top.ctong.mall.common.utils.R;
import top.ctong.mall.common.utils.RespStatus;

/**
 * <p>
 * 统一错误处理器
 * </p>
 *
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-04-21 00:19
 */
@ResponseBody
@ControllerAdvice
public class ControllerAdviceImpl {

    /**
     * 默认错误处理器
     *
     * @return R<Void>
     * @author Clover You
     * @date 2023/4/21 00:19
     */
    @ExceptionHandler(Exception.class)
    public R<Void> defaultHandler(Exception e) {
        return R.fail(RespStatus.FAIL.getCode(), RespStatus.FAIL.getMessage(), null);
    }

    /**
     * 服务层错误处理
     * @return R<Void>
     * @author Clover You
     * @date 2023/4/21 00:49
     */
    @ExceptionHandler(BaseException.class)
    public R<Void> serviceHandler(BaseException e) {
        return R.fail(e.getRs().getCode(), e.getMessage(), null);
    }

}
