<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%--引入jstl标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--引入fn标签库--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
    $(function () {
        $(".list-group-item").click(function(){
            if ( $(this).find("ul") ) {
                $(this).toggleClass("tree-closed");
                if ( $(this).hasClass("tree-closed") ) {
                    $("ul", this).hide("fast");
                } else {
                    $("ul", this).show("fast");
                }
            }
        });
    });

    //既然来到用户维护这个界面就应该把用户维护给显示出来
    //高亮
    $("ul a:contains('${pageTitle}')").css("color","red");
    //将菜单展开
    $("ul a:contains('${pageTitle})").parents("ul").show();
    //将菜单的"tree-closed"移除
    $("ul a:contains('${pageTitle}')").parents("li.tree-closed").removeClass("tree-closed");
</script>
