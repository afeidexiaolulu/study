<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="UTF-8">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <%@include file="/WEB-INF/include/common-css.jsp"%>
    <link rel="stylesheet" href="${appPath}/static/css/main.css">
    <style>
        .tree li {
            list-style-type: none;
            cursor:pointer;
        }
    </style>
</head>

<body>
<% pageContext.setAttribute("pageTitle", "用户维护"); %>

<%@include file="/WEB-INF/include/navbar.jsp"%>

<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include/sidebar.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">修改</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
                <div class="panel-body">
                    <form role="form" action="${appPath}/admin/userUpdate" method="post">
                        <input name="id" value="${admin.id}" type="hidden">
                        <div class="form-group">
                            <label for="loginacct">登陆账号</label>
                            <input type="text" class="form-control" id="loginacct" name="loginacct" value="${admin.loginacct}" placeholder="请输入登陆账号">
                            <c:if test="${! empty addMsg.loginacctMsg}">
                                <p class="help-block label label-warning">${addMsg.loginacctMsg}</p>
                            </c:if>
                        </div>
                        <div class="form-group">
                            <label for="username">用户名称</label>
                            <input type="text" class="form-control" id="username" name="username" value="${admin.username}" placeholder="请输入用户名称">
                        </div>
                        <div class="form-group">
                            <label for="email">邮箱地址</label>
                            <input type="email" class="form-control" id="email" name="email" value="${admin.email}"  placeholder="请输入邮箱地址">
                            <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
                            <c:if test="${! empty addMsg.emailMsg}">
                                <p class="help-block label label-warning">${addMsg.emailMsg}</p>
                            </c:if>
                        </div>
                        <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 修改 </button>
                        <button type="button" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置 </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/include/common-js.jsp"%>
<%@include file="/WEB-INF/include/show-highlight.jsp"%>
<script type="text/javascript">
    //既然来到用户维护这个界面就应该把用户维护给显示出来
    //高亮
    $("ul a:contains('用户维护')").css("color","red");
    //将菜单展开
    $("ul a:contains('用户维护')").parents("ul").show();
    //将菜单的"tree-closed"移除
    $("ul a:contains('用户维护')").parents("li.tree-closed").removeClass("tree-closed");
</script>
/body>
</html>
