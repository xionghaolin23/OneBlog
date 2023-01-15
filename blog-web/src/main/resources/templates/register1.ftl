<#include "include/macros.ftl">








<input type="hidden" name="id">
<div class="item form-group">
  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="username">用户名 <span class="required">*</span></label>
  <div class="col-md-6 col-sm-6 col-xs-12">
    <input type="text" class="form-control col-md-7 col-xs-12" name="username" id="username" required="required" placeholder="请输入用户名"/>
  </div>
</div>
<div class="item form-group">
  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="password">密码 </label>
  <div class="col-md-6 col-sm-6 col-xs-12">
    <input type="password" class="form-control col-md-7 col-xs-12" id="password" name="password" placeholder="请输入密码 6位以上"/>
  </div>
</div>
<div class="item form-group">
  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="nickname">昵称 </label>
  <div class="col-md-6 col-sm-6 col-xs-12">
    <input type="text" class="form-control col-md-7 col-xs-12" name="nickname" id="nickname" placeholder="请输入昵称"/>
  </div>
</div>
<div class="item form-group">
  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="mobile">手机 </label>
  <div class="col-md-6 col-sm-6 col-xs-12">
    <input type="tel" class="form-control col-md-7 col-xs-12" name="mobile" id="mobile" data-validate-length-range="6,20" placeholder="请输入手机号"/>
  </div>
</div>
<div class="item form-group">
  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">邮箱 </label>
  <div class="col-md-6 col-sm-6 col-xs-12">
    <input type="email" class="form-control col-md-7 col-xs-12" name="email" id="email" placeholder="请输入邮箱"/>
  </div>
</div>
<div class="item form-group">
  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="qq">QQ </label>
  <div class="col-md-6 col-sm-6 col-xs-12">
    <input type="number" class="form-control col-md-7 col-xs-12" name="qq" id="qq" placeholder="请输入QQ"/>
  </div>
</div>

</html>
