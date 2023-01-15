<!DOCTYPE html>
<html lang="en">
<head>
    <title>登陆页面</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="/js/signin.js"async></script>
    <script src="css/font_1554827_uixfzwgne2o/iconfont.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/sign.css">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">



    <style>
        .icon {
            width: 1.5em;
            height: 1.5em;
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
            <form class="login100-form validate-form"  action="/user/signin" method="POST" id="login-form">
					<span class="login100-form-title p-b-26">
						登陆
					</span>

                <span class="login100-form-title p-b-48">
						<svg class="icon" aria-hidden="true">
							<use xlink:href="#icon-zhuce"></use>
						</svg>
					</span>

                <div class="wrap-input100 validate-input" data-validate = "用户名格式错误">
                    <input class="input100" type="text" id="tel" name="username" required>
                    <span class="focus-input100" data-placeholder="用户名"></span>
                </div>



                <div class="wrap-input100 validate-input" data-validate="密码格式错误">
                    <input class="input100" type="password" id="pass" name="password" required>
                    <span class="focus-input100" data-placeholder="密码"></span>
                </div>

                <div class="container-login100-form-btn">
                    <div class="wrap-login100-form-btn">
                        <div class="login100-form-bgbtn"></div>
                        <button class="login100-form-btn btn-login" type='button'  >
                            登陆
                        </button>
                    </div>
                </div>

                <!--bootstrap用-->
                <div class="text-center p-t-115">
						<span class="txt1">
							没有账号|
						</span>
                    <a class="txt2" href="/user/register">
                        注册
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
<script src="/js/zhyd.tool.js"></script>

<script>
    $(".btn-login").click(function () {
        $.ajax({
            type: "POST",
            url: "/user/signin",
            data: $("#login-form").serialize(),
            dataType: "json",
            success: function (json) {
                if (json.status == 200) {
                    var historyUrl = json.data || "/";
                    window.location.href = "/";
                }else{
                    alert(json.message)
                    $.alert.error(json.message);
                    // $("#img-kaptcha").attr("src", '/getKaptcha?time=' + new Date().getTime());
                }
            }
        });
    });
</script>

</html>
