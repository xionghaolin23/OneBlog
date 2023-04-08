package com.zyd.blog.controller;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.annotation.BussinessLog;
import com.zyd.blog.business.entity.Article;
import com.zyd.blog.business.entity.FolloUser;
import com.zyd.blog.business.entity.SensitiveWords;
import com.zyd.blog.business.entity.User;
import com.zyd.blog.business.enums.ArticleStatusEnum;
import com.zyd.blog.business.enums.PlatformEnum;
import com.zyd.blog.business.service.BizArticleService;
import com.zyd.blog.business.service.FollowUserService;
import com.zyd.blog.business.service.SysSensitiveWordsService;
import com.zyd.blog.business.vo.ArticleConditionVO;
import com.zyd.blog.business.vo.FollowUserVO;
import com.zyd.blog.framework.holder.RequestHolder;
import com.zyd.blog.framework.object.ResponseVO;
import com.zyd.blog.persistence.beans.BizFollowUser;
import com.zyd.blog.persistence.beans.SysSensitiveWords;
import com.zyd.blog.util.ResultUtil;
import com.zyd.blog.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 我关注的
 * @Author:xionghl
 * @Date:2023/1/3 20:53
 */
@Slf4j
@Controller
@RequestMapping(value = "/follow")
public class FollowUserController {

    @Autowired
    private FollowUserService followUserService;

    @Autowired
    private BizArticleService bizArticleService;

    private static final int SIDEBAR_ARTICLE_SIZE = 8;



    @BussinessLog("进入我关注的人页面")
    @GetMapping("/followUser")
    public ModelAndView login(Model model) {
        List<FollowUserVO> followUserList = followUserService.getAllFollowers();
        model.addAttribute("followUserList", followUserList);
        return ResultUtil.view("/followUser");
    }




    @PostMapping(value = "/add/{followUserId}")
    @BussinessLog("添加关注的人")
    @ResponseBody
    public ResponseVO add(@PathVariable(name = "followUserId") String followUserId) {
        try {
            //当前用户信息
            Long userId = (Long) SecurityUtils.getSubject().getPrincipal();
            BizFollowUser followUser = new BizFollowUser();
//            if(userId.toString().equals(followUserId)){
//                return ResultUtil.error("自己不能关注自己");
//            }
            List<BizFollowUser> followingList = followUserService.findPageByUserIdAndFollowingUserId(userId.toString(), followUserId);
            if(!CollectionUtils.isEmpty(followingList)){
                return ResultUtil.error("此作者已在你的关注列表");
            }
            followUser.setUserId(userId.toString());
            followUser.setFollowUserId(followUserId);
            followUserService.insert(followUser);
            return ResultUtil.success("成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("error");
        }
    }

    /**
     * 我关注的人的博客文章详情
     *
     * @param model
     * @return
     */
    @GetMapping("/followUserList")
    @BussinessLog(value = "我关注的人的博客文章详情")
    public ModelAndView article(Model model, ArticleConditionVO vo) {

        vo.setStatus(ArticleStatusEnum.PUBLISHED.getCode());
        PageInfo<Article> pageInfo = bizArticleService.findPageBreakByCondition(vo);
        if(ObjectUtil.isEmpty(pageInfo)) {
            return null;
        }
        List<Article> pageInfoList = pageInfo.getList();
        Long userId = (Long) SecurityUtils.getSubject().getPrincipal();
        if(userId == null){
            return null;
        }
        List<Article> articles = pageInfoList.stream().filter(i->i.getUserId()==userId).collect(Collectors.toList());

        model.addAttribute("page", articles);
        model.addAttribute("model", vo);


        return ResultUtil.view("followUserarticle");
    }


}
