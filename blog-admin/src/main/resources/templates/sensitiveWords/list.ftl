<#include "/include/macros.ftl">
<@header></@header>
<div class="clearfix"></div>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <@breadcrumb>
            <ol class="breadcrumb">
                <li><a href="/">首页</a></li>
                <li class="active">敏感词管理</li>
            </ol>
        </@breadcrumb>
        <div class="x_panel">
            <div class="x_content">
                <div class="<#--table-responsive-->">
                    <div class="btn-group hidden-xs" id="toolbar">
                        <@shiro.hasPermission name="user:add">
                        <button id="btn_add" type="button" class="btn btn-info" title="新增用户">
                            <i class="fa fa-plus fa-fw"></i>
                        </button>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="user:batchDelete">
                            <button id="btn_delete_ids" type="button" class="btn btn-danger" title="删除选中">
                                <i class="fa fa-trash-o fa-fw"></i>
                            </button>
                        </@shiro.hasPermission>
                    </div>
                    <table id="tablelist">
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<@addOrUpdateMOdal defaultTitle="添加敏感词">
    <input type="hidden" name="id">
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="value">敏感词 <span class="required">*</span></label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <input type="text" class="form-control col-md-7 col-xs-12" name="value" id="value" required="required" placeholder="请输入敏感词"/>
        </div>
    </div>
</@addOrUpdateMOdal>
<@footer>
    <script>
        /**
         * 操作按钮
         * @param code
         * @param row
         * @param index
         * @returns {string}
         */
        function operateFormatter(code, row, index) {
            var currentUserId = '${user.id}';
            var trUserId = row.id;
            var operateBtn = [
                '<@shiro.hasPermission name="user:edit"><a class="btn btn-sm btn-success btn-update" data-id="' + trUserId + '"title="编辑"><i class="fa fa-edit fa-fw"></i></a></@shiro.hasPermission>',
            ];
            if (currentUserId != trUserId) {
                operateBtn.push('<@shiro.hasPermission name="user:delete"><a class="btn btn-sm btn-danger btn-remove" data-id="' + trUserId + '"title="删除"><i class="fa fa-trash-o fa-fw"></i></a></@shiro.hasPermission>');
            }
            return operateBtn.join('');
        }

        $(function () {
            var options = {
                url: "/sensitiveWords/list",
                getInfoUrl: "/sensitiveWords/get/{id}",
                updateUrl: "/sensitiveWords/edit",
                removeUrl: "/sensitiveWords/remove",
                createUrl: "/sensitiveWords/add",
                columns: [
                    {
                        checkbox: true
                    }, {
                        field: 'id',
                        title: 'ID',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    }, {
                        field: 'value',
                        title: '敏感词',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    },{
                        field: 'operate',
                        title: '操作',
                        align: "center",
                        width: '150px',
                        formatter: operateFormatter //自定义方法，添加操作按钮
                    }
                ],
                modalName: "敏感词"
            };
            // 初始化table组件
            var table = new Table(options);
            table.init();

        });
    </script>
</@footer>
