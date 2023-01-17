<#-- 公共顶部 -->
<#macro header title="OneBlog开源博客" keywords="" description="" canonical="" hasEditor=false>
<#include "/common/annotation.ftl">
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>${title}</title>
    <meta name="author" content="${config.authorName}(${config.authorEmail})">
    <meta name="keywords" content="${keywords}"/>
    <meta name="description" content="${description}" id="meta_description">
    <link rel="canonical" href="${config.siteUrl}${canonical}" />
    <#include "/layout/quote.ftl">
    <#--黑白界面
    <style>
        html {
            filter: grayscale(100%);
            -webkit-filter: grayscale(100%);
            -moz-filter: grayscale(100%);
            -ms-filter: grayscale(100%);
            -o-filter: grayscale(100%);
            filter: url("data:image/svg+xml;utf8,<svg xmlns="\'http://www.w3.org/2000/svg\'"><filter id="\'grayscale\'"><feColorMatrix type="\'matrix\'" values="\'0.3333" 0.3333="" 0="" 1="" 0\'=""></fecolormatrix></filter></svg>#grayscale");filter:progid:DXImageTransform.Microsoft.BasicImage(grayscale=1);-webkit-filter:grayscale(1);}
    </style>-->
    <#if hasEditor>
        <link href="https://cdn.jsdelivr.net/npm/simplemde@1.11.2/dist/simplemde.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/github-markdown-css@2.10.0/github-markdown.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/highlight.js@9.12.0/styles/github.min.css" rel="stylesheet">
    </#if>
    <#nested>

    <style type="text/css">
        ${config.customCss}
    </style>
</head>
<body>
    <#include "/layout/header.ftl"/>
</#macro>

<#-- 公共底部 -->
<#macro footer>
    <#include "/layout/footer.ftl"/>

    <#nested>

    </body>
</html>
</#macro>

<#-- 分页组件 -->
<#macro pageBar>
    <#if page?? && (page.pages > 1)>
    <nav class="pagination"
         data-url="${config.siteUrl}/${url!}"
         data-search="${(model.keywords == null || model.keywords == '')?string('false', 'true')}"
         data-total-page="${page.pages?c}"
         data-current-page="${page.pageNum?c}"
         data-pre="${page.prePage}"
         data-next="${page.nextPage}"></nav>
    </#if>
</#macro>


<#-- 发布文章填写文章详情的弹窗模板 -->
<#macro publishModal>
    <div class="modal fade" id="publishModal" tabindex="-1" role="dialog" aria-labelledby="publishLabel" data-backdrop="static">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="" role="tabpanel" data-example-id="togglable-tabs">
                        <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                            <li role="presentation" class="active">
                                <a href="#article" id="article-tab" role="tab" data-toggle="tab" aria-expanded="true">文章属性</a>
                            </li>
                            <li role="presentation" class="">
                                <a href="#seo" role="tab" id="seo-tab" data-toggle="tab" aria-expanded="false">SEO</a>
                            </li>
                        </ul>
                        <div id="" class="tab-content">
                            <div role="tabpanel" class="tab-pane fade active in" id="article" aria-labelledby="article-tab">
                                <div class="row">
                                    <div class="col col-md-3">
                                        <div class="item form-group">
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <div class="choose-local-img" style="text-align: center;">
                                                    <input id="cover-img-file" type="file" name="file" style="display: none">
                                                    <input id="cover-img-input" type="hidden" name="coverImage">
                                                    <div class="preview fa-2x" style="width: 100%;height: 186.98px;background: #f8fafc;border-radius: 5px;text-align: center;">
                                                        <img class="coverImage" src="" alt="">
                                                    </div>

                                                    <button type="button" class="btn btn-round btn-info" id="file-upload-btn" style="margin-top: 10px;">上传封面图片</button>
                                                    <div class="tip" style="margin-top: 10px;color: #c3c3c3;">
                                                        <div class="clearfix"></div>
                                                        <small style="font-size: 12px;">图片不宜过大</small>
                                                        <div class="clearfix"></div>
                                                        <small style="font-size: 12px;">建议尺寸 1190*300</small>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col col-md-9">
                                        <div class="item form-group">
                                            <label class="control-label col-md-2 col-sm-2 col-xs-12" for="nickname">分类 <span class="required">*</span></label>
                                            <div class="col-md-8 col-sm-8 col-xs-12">
                                                <div class="input-group">
                                                    <select class="form-control" name="typeId" id="typeId"  target="combox" data-url="/type/listAll" data-method="post" required="required"></select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="item form-group">
                                            <label class="control-label col-md-2 col-sm-2 col-xs-12" for="mobile">标签(*3) <span class="required">*</span></label>
                                            <div class="col-md-9 col-sm-9 col-xs-12">
                                                <input type="text" name="tags" target="tagsinput" data-bind-box="#tags-list">
                                                <ul class="list-unstyled list-inline tags-list" id="tags-list" target="combox" data-url="/tag/listAll" data-method="post" style="max-height: 150px;overflow-y: scroll;"></ul>
                                            </div>
                                        </div>
                                        <div class="item form-group">
                                            <label class="control-label col-md-2 col-sm-2 col-xs-12" for="mobile">状态 <span class="required">*</span></label>
                                            <div class="col-md-10 col-sm-10 col-xs-12 fixed-radio-checkbox">
                                                <ul class="list-unstyled list-inline">
                                                    <li><input type="radio" class="square" checked name="status" value="1"> 发布</li>
                                                    <li><input type="radio" class="square" name="status" value="0"> 草稿</li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="item form-group">
                                            <label class="control-label col-md-2 col-sm-2 col-xs-12" for="comment">开启评论 </label>
                                            <div class="col-md-10 col-sm-10 col-xs-12 fixed-radio-checkbox">
                                                <input type="checkbox" class="square" name="comment" id="comment">
                                            </div>
                                        </div>
                                        <div class="item form-group">
                                            <label class="control-label col-md-2 col-sm-2 col-xs-12" for="comment">私密文章 </label>
                                            <div class="col-md-10 col-sm-10 col-xs-12 fixed-radio-checkbox">
                                                <input type="text" class="square form-control" name="password" placeholder="请输入文章密码">
                                            </div>
                                        </div>
                                        <div class="item form-group">
                                            <label class="control-label col-md-2 col-sm-2 col-xs-12" for="comment">登录后可见？ </label>
                                            <div class="col-md-10 col-sm-10 col-xs-12 fixed-radio-checkbox">
                                                <ul class="list-unstyled list-inline">
                                                    <li><input type="radio" class="square" name="requiredAuth" value="1"> 是</li>
                                                    <li><input type="radio" class="square" checked name="requiredAuth" value="0"> 否</li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="seo" aria-labelledby="seo-tab">
                                <div class="item form-group">
                                    <label class="control-label col-md-2 col-sm-2 col-xs-12" for="password">摘要 <span class="required">*</span></label>
                                    <div class="col-md-8 col-sm-8 col-xs-12">
                                        <textarea class="form-control col-md-7 col-xs-12" id="description" name="description" required="required" maxlength="200"></textarea>
                                    </div>
                                </div>
                                <div class="item form-group">
                                    <label class="control-label col-md-2 col-sm-2 col-xs-12" for="password">关键词 <span class="required">*</span></label>
                                    <div class="col-md-8 col-sm-8 col-xs-12">
                                        <textarea class="form-control col-md-7 col-xs-12" id="keywords" name="keywords" required="required" maxlength="50"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-angle-left"> 返回修改</i></button>
                    <button type="button" class="btn btn-success publishBtn"><i class="fa fa-paper-plane-o"> 确定发布</i></button>
                </div>
            </div>
        </div>
    </div>
</#macro>


<#-- 发布文章时选择图片的弹窗模板 -->
<#macro chooseImgModal>
    <div class="modal fade chooseImgModal" id="chooseImgModal" tabindex="-1" role="dialog" aria-labelledby="chooseImgLabelledby" aria-hidden="true" data-backdrop="static"
         data-keyboard="false">
        <div class="modal-dialog <#--modal-lg-->" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="chooseImgLabelledby"><i class="fa fa-image fa-fw"></i>素材库</h4>
                </div>
                <div class="modal-body material-body">
                    <div class="btn-group" style="width: 100%;margin: 0 5px 5px 5px;padding: 0 0 10px 0;border-bottom: 1px solid #e7e7eb;">
                        <form action="" id="materialForm">
                            <input id="input-material-upload" type="file" name="file" multiple="multiple" accept="image/bmp,image/png,image/jpeg,image/jpg,image/gif" style="display: none;">
                            <button id="btn-material-upload" type="button" class="btn btn-success btn-md" title="本地上传">
                                <i class="fa fa-cloud-upload fa-fw"></i> 本地上传
                            </button>
                        </form>
                    </div>
                    <div class="fade active in material-box">
                        <ul class="list-unstyled list-file">
                        </ul>
                    </div>
                </div>
                <div class="modal-footer">
                    <span class="material-status pull-left">已选<span id="selected">0</span>个，可选<span id="selectable">1</span>个</span>
                    <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-close"> 关闭</i></button>
                    <button type="button" class="btn btn-success btn-confirm" data-dismiss="modal"><i class="fa fa-hand-o-up"> 确定</i></button>
                </div>
            </div>
        </div>
    </div>
</#macro>


<#-- blog-header -->
<#macro blogHeader title="Header" weiboName="@我的微博">
    <div class="col-sm-12 blog-main">
        <div class="blog-header">
            <h4>${title}</h4>
            <#if (config.enableHitokoto == 1 || config.enableHitokoto == "1")>
                <p class="blog-description hitokoto"></p>
            </#if>
            <div>
                <a href="javascript:void(0);" target="_blank" title="点击QQ联系我" onclick="window.open('tencent://message/?uin=${config.qq}&amp;Site=www.${config.domain}&amp;Menu=yes')" rel="external nofollow"><i class="fa fa fa-qq fa-fw"></i>QQ联系</a>
                |
                <a href="mailto:${config.authorEmail}" target="_blank" title="点击给我发邮件" rel="external nofollow"><i class="fa fa fa-envelope fa-fw"></i>邮箱联系</a>
                |
                <a href="${config.weibo}" target="_blank" title="点击查看我的微博" rel="external nofollow"><i class="fa fa fa-weibo fa-fw"></i>${weiboName}</a>
            </div>
        </div>
    </div>
</#macro>

<#-- 页面顶部、菜单下方提示栏 -->
<#macro prompt>
    <!--[if lt IE 9]><div class="alert alert-danger topframe" role="alert">Oh My God！你的浏览器实在<strong>太太太太太太旧了</strong>，赶紧升级浏览器 <a target="_blank" class="alert-link" href="http://browsehappy.com">立即升级</a></div><![endif]-->
    <#if config.maintenance! && config.maintenance == 1>
    <div class="alert alert-warning fade-in" role="alert">
        <a href="#" class="close" data-dismiss="alert">&times;</a>
        系统预计将在<strong>${config.maintenanceDate}</strong>左右进行更新维护，届时网站将无法使用，更新大约持续${config.maintenanceTime!(30)}分钟，敬请悉知。
    </div>
    </#if>
</#macro>


<#macro praise>
    <#if config.wxPraiseCode! || config.zfbPraiseCode!>

    </#if>
</#macro>
