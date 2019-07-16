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

    <%@include file="../../../include/common-css.jsp"%>
    <link rel="stylesheet" href="${appPath}/static/css/main.css">
    <style>
        .tree li {
            list-style-type: none;
            cursor:pointer;
        }
        table tbody tr:nth-child(odd){background:#F4F4F4;}
        table tbody td:nth-child(even){color:#C00;}
    </style>
</head>

<body>
<% pageContext.setAttribute("pageTitle", "用户维护"); %>;

<%@include file="../../../include/navbar.jsp"%>

<div class="container-fluid">
    <div class="row">
        <%@include file="../../../include/sidebar.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;" method="get" action="${appPath}/admin/index.html">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon" >查询条件</div>
                                <input name="queryCondition" value="${queryCondition}" class="form-control has-success" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;" onclick="deleteUsers()"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='${appPath}/admin/toUserAdd.html'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr >
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox" id="checkBoxId"></th>
                                <th>账号</th>
                                <th>名称</th>
                                <th>邮箱地址</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${pageInfo.list}" var="tAdmin">
                                <tr>
                                    <td>${tAdmin.id}</td>
                                    <td><input type="checkbox" class="check" id="${tAdmin.id}"></td>
                                    <td>${tAdmin.loginacct} </td>
                                    <td>${tAdmin.username} </td>
                                    <td>${tAdmin.email}</td>
                                    <td>
                                        <button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>
                                        <button type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil" onclick="window.location.href='${appPath}/admin/toUpdate.html?adminId='+${tAdmin.id}"></i></button>
                                        <button type="button" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove" onclick="deleteUser(${tAdmin.id})"></i></button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                            <tfoot>
                            <tr >
                                <td colspan="6" align="center">
                                    <ul class="pagination">
                                        <%--首页的控制--%>
                                        <c:if test="${pageInfo.isFirstPage}">
                                            <li class="disabled"><a href="#">首页</a></li>
                                        </c:if>
                                        <c:if test="${!pageInfo.isFirstPage}">
                                            <li><a href="${appPath}/admin/index.html?pageNo=1&queryCondition=${queryCondition}">首页</a></li>
                                        </c:if>

                                        <%--上一页的控制--%>
                                        <c:if test="${pageInfo.hasPreviousPage }">
                                            <li><a href="${appPath}/admin/index.html?pageNo=${pageInfo.prePage}&queryCondition=${queryCondition}">上一页</a></li>
                                        </c:if>
                                        <c:if test="${!pageInfo.hasPreviousPage }">
                                            <li class="disabled" ><a href="#">上一页</a></li>
                                        </c:if>
                                        <%--中间分页--%>
                                        <c:forEach items="${pageInfo.navigatepageNums}" var="navigatepageNum">
                                            <c:if test="${pageInfo.pageNum == navigatepageNum}">
                                                <li class="active"><a href="#">${navigatepageNum}</a></li>
                                            </c:if>
                                            <c:if test="${pageInfo.pageNum != navigatepageNum}">
                                                <li><a href="${appPath}/admin/index.html?pageNo=${navigatepageNum}&queryCondition=${queryCondition}">${navigatepageNum}</a></li>
                                            </c:if>
                                        </c:forEach>

                                        <%--下一页的控制--%>
                                        <c:if test="${pageInfo.hasNextPage}">
                                            <li><a href="${appPath}/admin/index.html?pageNo=${pageInfo.nextPage}&queryCondition=${queryCondition}">下一页</a></li>
                                        </c:if>
                                        <c:if test="${!pageInfo.hasNextPage}">
                                            <li class="disabled"><a href="#">下一页</a></li>
                                        </c:if>

                                        <%--尾页的控制--%>
                                        <c:if test="${pageInfo.isLastPage}">
                                            <li class="disabled"><a href="#">尾页</a></li>
                                        </c:if>
                                        <c:if test="${!pageInfo.isLastPage}">
                                            <li><a href="${appPath}/admin/index.html?pageNo=${pageInfo.pages}">尾页</a></li>
                                        </c:if>
                                    </ul>
                                </td>
                            </tr>

                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="../../../include/common-js.jsp"%>
<%@include file="/WEB-INF/include/show-highlight.jsp"%>
<script type="text/javascript">

    //既然来到用户维护这个界面就应该把用户维护给显示出来
    //高亮
    $("ul a:contains('用户维护')").css("color","red");
    //将菜单展开
    $("ul a:contains('用户维护')").parents("ul").show();
    //将菜单的"tree-closed"移除
    $("ul a:contains('用户维护')").parents("li.tree-closed").removeClass("tree-closed");


    $("tbody .btn-success").click(function(){
        window.location.href = "#";
    });

    //单个删除
    function deleteUser(sids){
        layer.confirm("确认要删除此用户么？",{btn:["确认","取消"]},function () {
            //删除
            window.location.href='${appPath}/admin/delete?ids='+sids;
        },function () {

        });
    }

    //删除多个
    function deleteUsers(){
        //所有被选择的checkbox
        let eles = $(".check:checked");
        //判断是否已选择
        if(eles.length == 0){
            return false;
        }
        ids = '';
        $.each(eles,function (i,e) {
            ids += e.id+',';
        });
        layer.confirm("确认要这些用户么？",{btn:["确认","取消"]},function () {
            //删除
            window.location.href='${appPath}/admin/delete?ids='+ids;
        },function () {

        });
    }

    //check框  全选 全不选
    $("#checkBoxId").click(function () {
        //给class为check的添加checked 属性   和被单击的checkbox状态相同
        $(".check").prop("checked", $(this).prop("checked"));
    });

    $(".check").click(function(){
        //总check不选  被选中的总check框个数相同
        $("#checkBoxId").prop("checked",$(".check:checked").length == $(".check").length);
    });






</script>
</body>
</html>
