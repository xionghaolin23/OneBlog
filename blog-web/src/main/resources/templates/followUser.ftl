<#include "include/macros.ftl">
<@compress single_line=false>
<@header title="我关注的人 | ${config.siteName}" canonical="/guestbook" hasEditor=true>
</@header>

<div class="container custome-container">
    <nav class="breadcrumb">
        <a class="crumbs" title="返回首页" href="${config.siteUrl}" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-home"></i>首页</a>
        <i class="fa fa-angle-right"></i>关注列表
    </nav>
    <div class="row guestbook-body">
        <#if config.guestbookHtml?? && (config.guestbookHtml?length > 0)>
            ${config.guestbookHtml!}
        <#else >
            <div class="col-sm-12 blog-main">
                <div class="blog-body expansion">
                    <div class="alert alert-default alert-dismissible" role="alert" style="padding: 0;">
                        <ul >
                            <li style="margin-bottom: 20px">我的关注列表</li>
                             <#list followUserList as item>
                                <li><i class="fa fa-lightbulb-o fa-fw"></i> <a href="/follow/followUserList">${item.nickname}</a> </li>
                             </#list>
                        </ul>
                    </div>

                </div>
            </div>
        </#if>
</div>

<@footer>
    <#if (config.enableHitokoto == 1 || config.enableHitokoto == "1")>
        <script src="https://v1.hitokoto.cn/?encode=js&c=i&select=.hitokoto" defer></script>
    </#if>

    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/highlight.js@9.12.0/lib/highlight.min.js"></script>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/simplemde@1.11.2/dist/simplemde.min.js"></script>
</@footer>
</@compress>
