//package com.example.health.interceptor;
//
//import com.alibaba.fastjson.JSONObject;
//import com.example.health.wrapper.BodyReaderHttpServletRequestWrapper;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import com.alibaba.fastjson.JSON;
//
//public class UserLoginInterceptor implements HandlerInterceptor {
//    /**
//     * 在请求被处理之前调用
//     */
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
////        response.setHeader("Content-type", "application/json;charset=UTF-8");
//        BodyReaderHttpServletRequestWrapper brhsrw=
//                new BodyReaderHttpServletRequestWrapper(request);
////        String name="{\"username\":\"1\",\"password\":\"1\"}";
////        JSONObject parameterMap = JSON.parseObject(brhsrw.getProperty());
////        System.out.print(parameterMap.toString());
////        String dataFrom = String.valueOf(parameterMap.get("username"));
//        request.setAttribute("username","1");
//        request.setAttribute("password","1");
//        return true;
//    }
//
//    /**
//     * 在请求被处理后，视图渲染之前调用
//     * @throws Exception
//     */
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//                           ModelAndView modelAndView) throws Exception {
//
//    }
//
//    /**
//     * 在整个请求结束后调用
//     */
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }
//
//}
