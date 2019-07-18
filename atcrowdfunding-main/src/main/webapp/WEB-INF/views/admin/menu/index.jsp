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

    <%--导入ZTreejs，css--%>
    <link rel="stylesheet" href="${appPath}/static/ztree/zTreeStyle.css">
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
<% pageContext.setAttribute("pageTitle", "菜单维护"); %>;

<%@include file="../../../include/navbar.jsp"%>

<div class="container-fluid">
    <div class="row">
        <%@include file="../../../include/sidebar.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 菜单维护 </h3>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <tbody>
                                <tr>
                                    <td>
                                        <ul id="treeDemo" class="ztree"></ul>
                                    </td>
                                </tr>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="../../../include/common-js.jsp"%>
<script src="${appPath}/static/ztree/jquery.ztree.all-3.5.min.js"></script>
<%@include file="/WEB-INF/include/show-highlight.jsp"%>
<script type="text/javascript">

    //既然来到用户维护这个界面就应该把用户维护给显示出来
    //高亮
    $("ul a:contains('菜单维护')").css("color","red");
    //将菜单展开
    $("ul a:contains('菜单维护')").parents("ul").show();
    //将菜单的"tree-closed"移除
    $("ul a:contains('菜单维护')").parents("li.tree-closed").removeClass("tree-closed");


    //ready事件
    $(function(){

        InitTree();

    });

    let ztree;

    //定义函数  初始化Ztree
    function InitTree() {
        //Ztrer设置项
        let setting = {
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pid",
                    rootPId: 0
                },
                key:{
                    url:"xxx"
                }
            },
            view:{
                addDiyDom: addDiyDom,
                addHoverDom: addHoverDom,
                removeHoverDom: removeHoverDom
            }
        };
        //获取数据
        //zNodes节点
        let ztree = null;
        $.ajax({
            url:"${appPath}/menu/getList",
            type:"get",
            success:function(data) {
                data.push({id:0, name:"系统菜单"});
                //初始化
                ztree = $.fn.zTree.init($("#treeDemo"), setting, data);
                //展开
                ztree.expandAll(true);
            }
        });
    }

    //自定义图标
    function addDiyDom(treeId, treeNode) {
        //移除原来的class
        $("#"+treeNode.tId+"_ico").removeClass();
        //在span文字前加标签
        $("#"+treeNode.tId+"_span").before("<span class='"+treeNode.icon+"'></span>");
    }

    //鼠标移动到
    function addHoverDom(treeId, treeNode) {
        let btnGroup=$("<span class='btnGroup'></span>");
        //添加按钮
        btnGroup.append("<button >+</button>")
                .append("<button>-</button>")
                .append("<button>*</button>");
        if($("#"+treeNode.tId+"_a").nextAll("span.btnGroup").length<=0){
            $("#"+treeNode.tId+"_a").after(btnGroup);
        }
    }

    //鼠标移出
    function removeHoverDom(treeId, treeNode) {
        //移除
        $("#"+treeNode.tId+"_a").nextAll("span.btnGroup").remove();
    }
</script>
</body>
</html>
