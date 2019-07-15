<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%--引入jstl标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--引入fn标签库--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="col-sm-3 col-md-2 sidebar">
    <div class="tree">
        <ul style="padding-left:0px;" class="list-group">
            <c:forEach items="${systemMenus}" var="TMenu">
                <c:if test="${fn:length(TMenu.tMenuList) == 0}">
                    <li class="list-group-item tree-closed">
                        <a href="${appPath}/${TMenu.url}">
                            <i class="${TMenu.icon}"></i> ${TMenu.name}
                        </a>
                    </li>
                </c:if>
                <c:if test="${fn:length(TMenu.tMenuList) != 0}">
                    <li class="list-group-item tree-closed">
                    <span>
                        <i class="${TMenu.icon}"></i> ${TMenu.name}
                        <span class="badge" style="float:right">
                                ${fn:length(TMenu.tMenuList)}
                        </span>
                    </span>
                        <ul style="margin-top:10px;display:none;">
                            <c:forEach items="${TMenu.tMenuList}" var="TMenuChildren">
                                <li style="height:30px;">
                                    <a href="${appPath}/${TMenuChildren.url}"><i class="${TMenuChildren.icon}"></i> ${TMenuChildren.name}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                </c:if>
            </c:forEach>
        </ul>
    </div>
</div>