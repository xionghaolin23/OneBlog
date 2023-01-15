<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
  <meta http-equiv="Access-Control-Allow-Origin" content="*" />
  <meta http-equiv="Access-Control-Allow-Headers" content="X-Requested-With" />
  <meta http-equiv="Access-Control-Allow-Methods" content="PUT,POST,GET,DELETE,OPTIONS" />
  <title>注册页面</title>
  <script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
  <script src="/js/signup.js"async></script>
  <script src="css/font_1554827_uixfzwgne2o/iconfont.js"></script>
  <link rel="stylesheet" type="text/css" href="/css/sign.css">
  <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">

  <style>
    .icon {
      width: 1.8em;
      height: 1.8em;
      vertical-align: -0.15em;
      fill: currentColor;
      overflow: hidden;
    }
  </style>
</head>
<body>

<div class="limiter">
  <div class="container-login100">
    <div class="wrap-login100">
      <form class="login100-form validate-form"  action="/user/add" method="POST" id="register-form">
					<span class="login100-form-title p-b-26">
						注册
					</span>

        <span class="login100-form-title p-b-48">
						<svg class="icon" aria-hidden="true">
							<use xlink:href="#icon-zhuce"></use>
						</svg>
					</span>

        <div class="wrap-input100 validate-input" data-validate = "用户名">
          <input class="input100" type="tel" id="username" name="username" required>
          <span class="focus-input100" data-placeholder="用户名"></span>
        </div>

        <div class="wrap-input100 validate-input" data-validate="密码包含数字字母最短6位">
          <input class="input100" type="password" id="password" name="password" required>
          <span class="focus-input100" data-placeholder="密码"></span>
        </div>

        <div class="wrap-input100 validate-input" data-validate="手机">
          <input class="input100" type="text" id="mobile" name="mobile" required>
          <span class="focus-input100" data-placeholder="手机"></span>
        </div>


        <div class="wrap-input100 validate-input" data-validate = "">
          <input class="input100" type="email" id="email" name="email" required>
          <span class="focus-input100" data-placeholder="邮箱"></span>
        </div>


        <div class="container-login100-form-btn">
          <div class="wrap-login100-form-btn">
            <div class="login100-form-bgbtn"></div>
              <button class="login100-form-btn btn-register" type='button'  >
              注册
            </button>
          </div>
        </div>

        <!--bootstrap用-->
        <div class="text-center p-t-115">
						<span class="txt1">
							已有账号|
						</span>
          <a class="txt2" href="/user/login">
            登陆
          </a>
        </div>
      </form>
    </div>
  </div>
</div>

</body>

<script>
  $(".btn-register").click(function () {
    $.ajax({
      type: "POST",
      url: "/user/register",
      data: $("#register-form").serialize(),
      dataType: "json",
      success: function (json) {
        if (json.status == 200) {
          window.location.href = "/user/login/";
        }else{
          alert(json.message)
        }
      }
    });
  });
</script>
</html>
