package top.ctong.mall.common.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import top.ctong.mall.common.exception.BaseException;
import top.ctong.mall.common.utils.R;
import top.ctong.mall.common.utils.RespStatus;

import javax.validation.constraints.NotNull;
import java.util.Objects;

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
        e.printStackTrace();
        return R.fail(RespStatus.FAIL.getCode(), RespStatus.FAIL.getMessage(), null);
    }

    /**
     * 服务层错误处理
     *
     * @return R<Void>
     * @author Clover You
     * @date 2023/4/21 00:49
     */
    @ExceptionHandler(BaseException.class)
    public R<Void> serviceHandler(BaseException e) {
        return R.fail(e.getRs().getCode(), e.getMessage(), null);
    }

    /**
     * 请求方法参数异常 C: a ----> S: int
     *
     * @return R<Void>
     * @author Clover You
     * @date 2023/4/22 21:27
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<Void> argumentTypeException() {
        return R.fail(RespStatus.BAD_REQUEST.getCode(), RespStatus.BAD_REQUEST.getMessage(), null);
    }

    /**
     * 参数校验异常
     *
     * @author Clover You
     * @date 2023/4/22 22:12
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object methodArgumentException(MethodArgumentNotValidException e) throws MethodArgumentNotValidException {
        return R.fail(RespStatus.BAD_REQUEST.getCode(), Objects.requireNonNull(e.getFieldError()).getDefaultMessage(), null);
    }

}
