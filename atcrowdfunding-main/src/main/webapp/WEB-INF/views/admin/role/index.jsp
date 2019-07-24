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
    <%--导入ZTreejs，css--%>
    <link rel="stylesheet" href="${appPath}/static/ztree/zTreeStyle.css">
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
<% pageContext.setAttribute("pageTitle", "角色维护"); %>;
<%@include file="/WEB-INF/include/navbar.jsp"%>

<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include/sidebar.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input class="form-control has-success"  id="queryConditionId" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="button" id="queryBtn" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button type="button" class="btn btn-primary" style="float:right;" id="addModelId"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table id="contentTable" class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                            <tfoot>
                            <tr >
                                <td colspan="6" align="center">
                                    <ul class="pagination">
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

    <%--添加模态框--%>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">角色新增</h4>
                </div>
                <div class="modal-body">
                    <form action="">
                        <div class="form-group">
                            <label for="roleName"></label>
                            <input type="text" class="form-control" id="roleName"  placeholder="请输入用户名">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="saveRoleId">保存</button>
                </div>
            </div>
        </div>
    </div>


    <%--修改模态框--%>
    <div class="modal fade" id="myModalUpdate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabelUpdate">角色修改</h4>
                </div>
                <div class="modal-body" >
                    <form id="formId">
                        <div class="form-group" >
                            <input type="hidden" class="form-control" name="id"  >
                            <input type="text" class="form-control" name="roleName"  >
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="updateRoleId">更新</button>
                </div>
            </div>
        </div>
    </div>

    <%--分配角色模态框--%>
    <div class="modal fade" id="myModalAssignPermission" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="ddd">分配修改</h4>
                </div>
                <div class="modal-body" >
                   <ul id="permissionTree" class="ztree"></ul>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="assignPermission">分配权限</button>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/include/common-js.jsp"%>
<%@include file="/WEB-INF/include/show-highlight.jsp"%>
<script src="${appPath}/static/ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">
    //既然来到角色维护这个界面就应该把用户维护给显示出来
    //高亮
    $("ul a:contains('角色维护')").css("color","red");
    //将菜单展开
    $("ul a:contains('角色维护')").parents("ul").show();
    //将菜单的"tree-closed"移除
    $("ul a:contains('角色维护')").parents("li.tree-closed").removeClass("tree-closed");


    $("tbody .btn-success").click(function(){
        window.location.href = "assignPermission.html";
    });

    //写一个ready事件
    $(function () {
        //ready  执行此函数
        initRoleData();
    });


    //创建查询的json对象
    let json = {
        pageNo:1,
        pageSize:4,
        queryCondition:"",
        pages:""
    }

    //页面初始化
    function initRoleData() {
        let index = -1;
        //发送ajax请求 
        $.ajax(
            {
            url:"${appPath}/role/listRole",
            data:json,
            type:"post",
            beforeSend:function(){
                index = layer.load(0, {time: 10*1000});
                return true;
            },
            success: function (data) {
                layer.close(index);
                //拿到数据后，将总页数赋值 给json对象  告诉json对象一共有多少页
                json.pages = data.pages;
                //构建显示内容
                buildTableContent(data.list);
                //构建分页条
                buildPageBar(data);
                }
            }
        );
    }

    //构建分页条
    function buildPageBar(nav) {
        //zhi空
        $("ul.pagination").empty();
        //将首页，尾页，上页，下页的字符串写出来
        let firstPage = "<li pageNo='"+nav.firstPage+"'><a href='#'>首页</a></li>";
        let lastPage = "<li pageNo='"+nav.lastPage+"'><a href='#'>尾页</a></li>";
        let prePage = "<li pageNo='"+nav.prePage+"'><a href='#'>上一页</a></li>";
        let nextPage = "<li pageNo='"+nav.nextPage+"'><a href='#'>下一页</a></li>";

        //添加首页和上一页
        if(nav.pageNum == nav.firstPage){
            $("ul.pagination").append("<li class='disabled'><a href='#'>首页</a></li>");
        }else {
            $("ul.pagination").append(firstPage);
        }
        if(nav.pageNum == nav.firstPage){
            $("ul.pagination").append("<li class='disabled'><a href='#'>上一页</a></li>");
        }else {
            $("ul.pagination").append(prePage);
        }

        //遍历中间的导航栏
        $.each(nav.navigatepageNums,function () {
            if(this == nav.pageNum){
                $("ul.pagination").append("<li class='active' pageNo='"+this+"'><a href='#'>"+this+"</a></li>");
            }else {
                $("ul.pagination").append("<li pageNo='"+this+"'><a href='#'>"+this+"</a></li>");
            }
        });

        //添加尾页和下一页
        if(nav.pageNum == nav.lastPage){
            $("ul.pagination").append("<li class='disabled'><a href='#'>下一页</a></li>");
        }else {
            $("ul.pagination").append(nextPage);
        }
        if(nav.pageNum == nav.lastPage){
            $("ul.pagination").append("<li class='disabled'><a href='#'>尾页</a></li>");
        }else {
            $("ul.pagination").append(lastPage);
        }
    }

    //构建显示内容
    function buildTableContent(content){
        //清空掉之前的数据
        $("#contentTable tbody").empty();

        //遍历
        $.each(content,function () {
            //添加btn按钮
            let btnGrout = $("<td></td>");
            btnGrout.append("<button type='button' rId='"+this.id+"' class='assignPermissionBtn  btn btn-success btn-xs'><i class='glyphicon glyphicon-check'></i></button> ");
            btnGrout.append("<button type='button' onclick='updateRole("+this.id+")' class='btn btn-primary btn-xs'><i class='glyphicon glyphicon-pencil'></i></button> ");
            btnGrout.append("<button type='button' onclick='deleteRole("+this.id+")' class='btn btn-danger btn-xs'><i class='glyphicon glyphicon-remove'></i></button> ");

            //添加内容
            let tr = $("<tr></tr>");
            tr.append("<td>"+this.id+"</td>");
            tr.append("<td><input type='checkbox' class='check'></td>");
            tr.append("<td>"+this.name+"</td>");
            tr.append(btnGrout);
            //将此tr放入到tbody中 每次循环放一个
            tr.appendTo("#contentTable tbody");
        });
    }

    //给导航栏添加单击事件
    //$(父/祖先元素选择器).on(事件名,"要绑定事件的元素选择器",回调函数)
    $("ul.pagination").on("click","li",function(){
        let pageNo = $(this).attr("pageNo");
        json.pageNo = pageNo;
        initRoleData();//初始化数据
    });


    //查询按钮
    $("#queryBtn").click(function () {
       let condition = $("#queryConditionId").val();
       //组装条件
       json.queryCondition = condition;
       //初始化数据
       initRoleData();
    });


    $("#addModelId").click(function () {
        $('#myModal').modal({
            backdrop:false
        });
    });

    //保存用户
    $("#saveRoleId").click(function(){
        let roleName = $("#roleName").val();
        $.ajax({
           type:"post",
           url:"${appPath}/role/addRole",
           data:{
               roleName:roleName
           },
           success:function (data) {
               if(data){
                   //隐藏模态框
                   $('#myModal').modal('hide');
                   $("#roleName").val("");
                   //重构表单页面  跳转页数+1
                   json.pageNo = json.pages+1;
                   initRoleData();
               }
           }
        });
    });


    //修改用户信息
    function updateRole(rId) {
        //弹出模态框
        $('#myModalUpdate').modal({
            backdrop:false
        });
        //回显内容
        $.ajax({
            url:"${appPath}/role/getRole ",
            type:"post",
            data:{
                id:rId
            },
            success:function (data) {
                $("#formId input[name ='id']").val(data.id);
                $("#formId input[name ='roleName']").val(data.name);
            }
        });

    }

    //点击修改按钮
    $("#updateRoleId").click(function () {
        let rid = $("#formId input[name ='id']").val();
        let rname = $("#formId input[name ='roleName']").val();
        $.ajax({
            type:"post",
            url:"${appPath}/role/updateRole",
            data:{
              id:rid,
              name:rname
            },
            success:function(data){
                if(data){
                    //关闭模态框
                    $('#myModalUpdate').modal('hide');
                    initRoleData();
                }
            }
        });
    });


    //删除
    function deleteRole(rid){

        $.ajax({
            type:"post",
            url:"${appPath}/role/delete",
            data:{
                ids:rid
            },
            success:function (data) {
                if(data){
                    //初始化
                    initRoleData();
                }
            }
        });
    }


    //定义全局变量  分配权限的id
    let assignPermissionId = null;

    //分配权限
    $("#contentTable tbody").on('click', '.assignPermissionBtn',function(){
        //给分配权限的角色id赋值
        assignPermissionId = $(this).attr("rid");

        //查出所有的权限 ,将Ztree回显
        initPermissionTree();

        //将角色已有的权限进行回显
        initPermissionChecked(assignPermissionId);

        //弹出模态框
        $('#myModalAssignPermission').modal({
            backdrop:false
        });
    });

    //将已有的权限在zTree中
    function initPermissionChecked(roleId){
        //发送请求，获取权限
        $.ajax({
            type:"post",
            url:"${appPath}/permission/getPermissionByRoleId",
            data:{
                roleId:roleId
            },
            success:function (data) {
                //回显
                let treeObj = $.fn.zTree.getZTreeObj("permissionTree");
                $.each(data,function (i,e) {
                    let node = treeObj.getNodeByParam("id", e.id, null);
                    treeObj.checkNode(node, true, false);
                });
            }
        });
    }

    //初始化权限树
    function initPermissionTree() {
        //设置树
        let setting = {
            data: {
                simpleData: {
                    enable: true,
                        idKey: "id",
                        pIdKey: "pid",
                        rootPId: 0
                },
                key:{
                    url:"xxx",
                    name:"title"
                }
            },
            check: {
                enable: true
            }
        };

        $.ajax({
            url:"${appPath}/permission/getAllPermission",
            success:function(data){
                //初始化
                ztree = $.fn.zTree.init($("#permissionTree"), setting, data);
                //展开
                ztree.expandAll(true);

            }
        });
    }

    //点击保存向中间表插入数据
    $("#assignPermission").click(function(){
        //获取选中的权限
        let treeObj = $.fn.zTree.getZTreeObj("permissionTree");
        let nodes = treeObj.getCheckedNodes(true);
        let ids = new Array();
        $.each(nodes,function (i,e) {
            ids.push(e.id);
        })
        let idsStr = ids.join();
        //发送ajax
        $.ajax({
            type:"post",
            url:"${appPath}/permission/assignPermission",
            data:{
                ids:idsStr,
                Rid:assignPermissionId
            },
            success:function (data) {
              //关闭模态框
             $('#myModalAssignPermission').modal('hide');
            }
        });
    });
</script>
</body>
</html>

