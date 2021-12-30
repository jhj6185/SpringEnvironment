<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Tables</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <!-- /.panel-heading -->
                        <form method="POST" action="/board/modify?bno=${board.bno}">
                        <input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum }"/>'>
                        <input type="hidden" name="amount" value='<c:out value="${cri.amount }"/>'>
                        <input type="hidden" name="keyword" value='<c:out value="${cri.keyword }"/>'>
                        <input type="hidden" name="type" value='<c:out value="${cri.type }"/>'>
                        <div class="panel-body">
                                <div class="form-group">
                                    <label>Bno</label>
                                <input class="form-control" name="bno" value='<c:out value="${board.bno }"/>' readonly="readonly">
                                </div>
                                <div class="form-group">
                                <label>regDate</label>
                                <input class="form-control" name="regDate" value='<fmt:formatDate value="${board.regDate}" pattern="yyyy/MM/dd"/>' readonly="readonly">
                                </div>
                                <div class="form-group">
                                <label>updateDate</label>
                                <input class="form-control" name="updateDate" value='<fmt:formatDate value="${board.updateDate}" pattern="yyyy/MM/dd"/>'>
                                </div>
                                <div class="form-group">
                                <label>Title</label>
                                <input class="form-control" name="title" value='${board.title }'>
                                </div>
                                <div class="form-group">
                                <label>Content</label>
                                <textarea class="form-control" rows="3" name="content">${board.content }</textarea>
                                </div>
                                <div class="form-group">
                                <label>Writer</label>
                                <input class="form-control" name="writer" value='${board.writer }'>
                                </div>
                                <button data-oper='modify' class="btn btn-info" type="submit">Modify</button>
                                <button data-oper='remove' class="btn btn-danger" type="submit">Remove</button>
                                <button data-oper='list' class="btn btn-default" type="submit">List</button>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                        </form>
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
        </div>
        <!-- /#page-wrapper -->
    <!-- /#wrapper -->
    <%@include file="../include/footer.jsp" %>
	
    <script type="text/javascript">
    	$(document).ready(function(){
    		var formObj = $("form");
    		$('button').on("click",function(e){
    			e.preventDefault();
    			var operation = $(this).data("oper");
    			console.log(operation);
    			if(operation === 'remove'){
    				formObj.attr("action","/board/remove");
    			}else if(operation === 'list'){
    				//move to list
    				formObj.attr("action","/board/list").attr("method","get");
    				var pageNumTag = $("input[name='pageNum']").clone(); //잠시 보관용
    				var amountTag = $("input[name='amount']").clone();
    				var keywordTag = $("input[name='keyword']").clone();
    				var typeTag = $("input[name='type']").clone();
    				
    				formObj.empty(); //제거
    				formObj.append(pageNumTag);
    				formObj.append(amountTag); // 필요한 태그들만 추가
    				formObj.append(keywordTag);
    				formObj.append(typeTag);
    				
    				// 왜이렇게 길게 하냐면.. formOBJ는 post방식인데 우리는 get방식으로 보내고싶어서
    				// post안에있는 데이터 싹 비우고 필요한것만 복사(clone) 한다음에 append해서 보냄get방식으로
    			}
    			formObj.submit();
    		});
    	});
    </script>

</body>

</html>
