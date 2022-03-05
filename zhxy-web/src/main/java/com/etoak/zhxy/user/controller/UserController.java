package com.etoak.zhxy.user.controller;
import com.etoak.zhxy.listener.ETlistener;
import com.etoak.zhxy.user.entity.Menus;
import com.etoak.zhxy.user.entity.Users;

import com.etoak.zhxy.user.service.Impl.UserServiceImpl;
import com.etoak.zhxy.user.service.UserService;
import com.etoak.zhxy.user.vo.RoleMenuVo;
import com.etoak.zhxy.user.vo.UserRoleVo;
import com.etoak.zhxy.utils.CheckcodeUtils;
import com.etoak.zhxy.utils.JsonResponse;
import com.etoak.zhxy.utils.RedisUtil;
import com.etoak.zhxy.utils.ResultStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

//    @Qualifier("userServiceImpl")
//    @Resource
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/query1")
    public JsonResponse query1() {
        List<Map<String, Object>> data = userService.query1();
        return ResultStatus.suc(data);
    }

    @PostMapping("/queryAllURM")
    public JsonResponse queryAll() {
        Map<String, Object> data = userService.queryAll();
        return ResultStatus.suc(data);
    }

    @PostMapping("/updateUserRole")
    public JsonResponse updateUserRole(@RequestBody UserRoleVo vos) {
        userService.updateRole(vos);
        return ResultStatus.suc("success");

    }

    @PostMapping("/updateRoleMenus")
    public JsonResponse updateRoleMenus(@RequestBody RoleMenuVo vos) {
        userService.updateMenus(vos);
        return ResultStatus.suc("success");

    }

    public static final int WIDTH = 120;//生成的图片的宽度
    public static final int HEIGHT = 30;//生成的图片的高度

    @GetMapping("/checkcode")
    public void getCheckCode(HttpServletRequest request, HttpServletResponse response)
            throws  IOException {
        CheckcodeUtils ccu = new CheckcodeUtils();
        //1.在内存中创建一张图片
        BufferedImage bi = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
        //2.得到图片
        Graphics g = bi.getGraphics();
        //3.设置图片的背影色
        ccu.setBackGround(g);
        //4.设置图片的边框
        ccu.setBorder(g);
        //5.在图片上画干扰线
        ccu.drawRandomLine(g);
        //6.写在图片上随机数
        //String random = drawRandomNum((Graphics2D) g,"ch");//生成中文验证码图片
        //String random = drawRandomNum((Graphics2D) g,"nl");//生成数字和字母组合的验证码图片
        String random = ccu.drawRandomNum((Graphics2D) g,"n");//生成纯数字的验证码图片
        //String random = drawRandomNum((Graphics2D) g,"l");//生成纯字母的验证码图片
//        String random = ccu.drawRandomNum((Graphics2D) g,"n");//根据客户端传递的createTypeFlag标识生成验证码图片
        //7.将随机数存在session中

        HttpSession session = request.getSession();

        session.setAttribute("checkcode", random);

        response.setHeader("sessionid",session.getId());


        //8.设置响应头通知浏览器以图片的形式打开
        response.setContentType("image/jpeg");//等同于response.setHeader("Content-Type", "image/jpeg");
        //9.设置响应头控制浏览器不要缓存
        response.setDateHeader("expries", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        //10.将图片写给浏览器
        ImageIO.write(bi, "jpg", response.getOutputStream());
    }
    @PostMapping("/login")
    public JsonResponse login(@RequestParam("username") String uname,
                               String password,
                              String code, @RequestParam("sessionid") String sid) {

            JsonResponse response = null;
            //判断验证码，是否正确 code：验证码 2 从session中获得存放的验证码
        HttpSession oldsession = ETlistener.getSessionById(sid);
        Object c1 = oldsession.getAttribute("checkcode");
        if (c1==null){
            response  = ResultStatus.INVALID_REQUEST("非法请求");

        }else if(StringUtils.isNullOrEmpty(code) || !code.equalsIgnoreCase(c1+"")){
            response = ResultStatus.checkCodeError("验证码错误");
        }
        //判断用户名和密码
        else  {
           Users u =userService.login(uname,password);
            if (u==null || StringUtils.isNullOrEmpty(u.getUname())){
            response=     ResultStatus.errer("用户名错误");
            }else {
                //登陆成功 给一个token

                //获的令牌
              String token = UUID.randomUUID().toString().replaceAll("-","");
                u.setToken(token);

                //把用户信息放到redis数据库中
                RedisUtil ru = RedisUtil.getRedisUtil();
                JsonMapper mapper = new JsonMapper();
                String userinfo = null;
                try {
                    userinfo = mapper.writeValueAsString(u);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                //把用户令牌：用户信息放到redis数据库中
                ru.setex(token,1800,userinfo);
                //把用户信息返回到客户端
                response= ResultStatus.suc(u);
            }
        }



        return response;
    }


    @PostMapping("/queryMenus")
    public JsonResponse queryMenus(@RequestHeader("token") String token) throws  Exception{
                                        //通过注解拿到请求头里面的token
        //根据用户，查询redis数据库获得用户，
        RedisUtil re = RedisUtil.getRedisUtil();
        String useinfo = re.get(token);
        JsonMapper mapper = new JsonMapper();
        Users u = mapper.readValue(useinfo, Users.class);
        if (u==null){
            System.out.println("没登录或已过时");
        }
        //根据用户id查询 role_menu
          List<Menus> menus = userService.queryMenuByUserId(u.getId());

        //组装一颗树
        List<Menus> tops = new ArrayList<>();
        for (Menus m :menus){
            if (m.getPid()==-1){
                tops.add(m);
            }
        }

        //寻找子节点
        for (Menus t:tops){
            //t是要寻找子菜单的菜单， menus是子菜单所在的地方
            userService.setChildren(t,menus);
        }






        return ResultStatus.suc(tops);


}
}

