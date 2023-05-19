//package com.playGG.play.ggprojectboard.config.auth;
//
//import com.playGG.play.ggprojectboard.web.dto.UsersDto;
//import jakarta.servlet.http.HttpSession;
//import lombok.RequiredArgsConstructor;
//import org.springframework.core.MethodParameter;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.support.WebDataBinderFactory;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.method.support.ModelAndViewContainer;
//
//@RequiredArgsConstructor
//@Component
//public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
//    private final HttpSession session;
//
//
//    /* @LoginUser 어노테이션이 붙어 있고, 파라미터 클래스 타입이 UserDto.Response인가 판단 후 true반환 */
//    @Override
//    public boolean supportsParameter(MethodParameter parameter) {
//        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
//
//        boolean isUserClass = UsersDto.Response.class.equals(parameter.getParameterType());
//
//        return isLoginUserAnnotation && isUserClass;
//    }
//
//    /* 파라미터에 전달할 객체 생성 */
//    @Override
//    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
//                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//
//        return session.getAttribute("users");
//    }
//
//
//}
