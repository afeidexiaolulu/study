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
    <link rel="stylesheet" href="${appPath}/static/css/doc.min.css">
    <style>
        .tree li {
            list-style-type: none;
            cursor:pointer;
        }
    </style>
</head>

<body>
<% pageContext.setAttribute("pageTitle", "用户维护"); %>;


<%@include file="/WEB-INF/include/navbar.jsp"%>

<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include/sidebar.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">分配角色</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form role="form" class="form-inline">
                        <div class="form-group">
                            <label for="leftToRight1">未分配角色列表</label><br>
                            <select  id="leftToRight1" class="form-control"  multiple size="10" style="width:250px;overflow-y:auto;">
                                <c:forEach items="${unassignRoles}" var="unassignRole">
                                    <option value="${unassignRole.id}">${unassignRole.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <ul>
                                <li class="btn btn-default glyphicon glyphicon-chevron-right" id="leftToRight" style="margin-top:20px;"></li>
                                <br>
                                <li class="btn btn-default glyphicon glyphicon-chevron-left" id="rightToLeft" style="margin-top:20px;"></li>
                            </ul>
                        </div>
                        <div class="form-group" style="margin-left:40px;">
                            <label for="rightToLeft1">已分配角色列表</label><br>
                            <select  id="rightToLeft1" class="form-control"  multiple size="10" style="width:250px;overflow-y:auto;">
                                <c:forEach items="${assignRoles}" var="assignRole">
                                    <option value="${assignRole.id}">${assignRole.name}</option>
                                </c:forEach>
                            </select>
                        </div>
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



    $("#leftToRight").click(function () {
        //获取已被选中的
       let idstr = '';
       $("#leftToRight1 option:selected").each(function (i,e) {
          idstr += e.value + ',';
       });
        //发送请求
        window.location.href = "${appPath}/admin/assignRole?ids="+idstr+"&ops=add";
    });


    $("#rightToLeft").click(function () {
        //获取已被选中的
        let idstr = '';
        $("#rightToLeft1 option:selected").each(function (i,e) {
            idstr += e.value + ',';
        });
        //发送请求
        window.location.href = "${appPath}/admin/assignRole?ids="+idstr +"&ops=delete";
    });

</script>
</body>
</html>

