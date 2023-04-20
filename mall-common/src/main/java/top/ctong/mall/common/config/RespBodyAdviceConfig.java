package top.ctong.mall.common.config;

import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import top.ctong.mall.common.itfs.IgnoreWrapper;
import top.ctong.mall.common.utils.R;

import java.util.Objects;

/**
 * <p>
 * 统一响应拦截
 * </p>
 *
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-04-19 21:16
 */
@RestControllerAdvice
public class RespBodyAdviceConfig implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !returnType.hasMethodAnnotation(IgnoreWrapper.class);
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (!MediaType.APPLICATION_JSON.equals(selectedContentType) || body instanceof R) {
            return body;
        }

        Object resp = R.ok(20000, "successful", body);

        if (body instanceof String) {
            // 注意如果是字符串的话，那么返回值的MessageConverter 是字符串的处理器，需要将JSON 转为String
            JsonMapper jsonMapper = new JsonMapper();
            resp = jsonMapper.writeValueAsString(resp);
        }
        return resp;
    }

}
