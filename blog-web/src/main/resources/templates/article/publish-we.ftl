<#include "/include/macros.ftl">
<#setting number_format="#">
<@header>

</@header>
<form id="publishForm" class="form-horizontal form-label-left" novalidate>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_content">
                    <input type="hidden" name="isMarkdown" value="0">
                    <input type="hidden" name="editorType" value="we">
                    <input type="hidden" name="id">
                    <div class="item form-group">
                        <label class="control-label col-md-1 col-sm-1 col-xs-12" for="title">标题 <span class="required">*</span></label>
                        <div class="col-md-8 col-sm-8 col-xs-12">
                            <input type="text" class="form-control col-md-7 col-xs-12" name="title" id="title" required="required" placeholder="请输入标题"/>
                        </div>
                        <div class="col-md-1 col-sm-1 col-xs-12">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" class="square" checked name="original"> 原创
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-1 col-sm-1 col-xs-12" for="password">内容 <span class="required">*</span></label>
                        <div class="col-md-11 col-sm-11 col-xs-12">
                            <div id="editor" ></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-1 col-sm-1 col-xs-12"></label>
                        <div class="col-md-10 col-sm-10 col-xs-12">
                            <button type="button" class="btn btn-success to-choose-info"><i class="fa fa-pencil"> 发布文章</i></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
   <@publishModal></@publishModal>
</form>
<@chooseImgModal></@chooseImgModal>
<@footer>
    <script>
        articleId = '${id}';
        editorType = 'we';
        $(function () {
            zhyd.wangEditor.init({
                container: "#editor",
                textareaName: "content",
                uploadUrl: "/api/uploadFile",
                uploadFileName: "file",
                uploadType: "article",
            })
        });
    </script>
    <script src="/js/zhyd.publish-article.js"></script>
    <script src="/js/zhyd.core-1.js"></script>
</@footer>
9.7 11.50
