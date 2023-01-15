<#include "include/macros.ftl">
<@compress single_line=false>
    <@header title="免责声明 | ${config.siteName}" keywords="" description="免责声明" canonical="/disclaimer"></@header>

    <div class="container custome-container">
        <nav class="breadcrumb">
            <a class="crumbs" title="返回首页" href="${config.siteUrl}" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-home"></i>首页</a> <i
                    class="fa fa-angle-right"></i>免责声明
        </nav>
        <div class="row disclaimer">
            <div class="col-sm-8 blog-main">
                <div class="blog-body overflow-initial fade-in">
                    <div class="blog-info overflow-initial">
                        <div class="bottom-line">
                            <h1 class="disclaimer-title">
                                <strong>我的关注作者博客列表</strong>
                            </h1>
                        </div>
                        <div class="disclaimer-body">
                            <#if config.disclaimerHtml?? && (config.disclaimerHtml?length > 0)>
                                ${config.disclaimerHtml!}
                            <#else >
                                <ul class="list-unstyled disclaimer-box">
                                    <#list page as item>
                                    <li><a href="/article/${item.id}">《${item.title}》</a> </li>
                                    </#list>
                                </ul>
                            </#if>
                        </div>
                        <#if (config.enableHitokoto == 1 || config.enableHitokoto == "1")>
                            <div class="article-footer overflow-initial">
                                <span class="blog-description hitokoto num"></span>
                            </div>
                        </#if>
                    </div>
                </div>
            </div>
            <#include "layout/sidebar.ftl"/>
        </div>
    </div>
    <@footer>
        <#if (config.enableHitokoto == 1 || config.enableHitokoto == "1")>
            <script src="https://v1.hitokoto.cn/?encode=js&c=i&select=.hitokoto" defer></script>
        </#if>
    </@footer>
</@compress>
