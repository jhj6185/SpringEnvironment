<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                        <div class="panel-heading">
                            게시글 목록
                            <button id='regBtn' type="button" class="btn btn-xs pull-right">글쓰기</button>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>번호</th>
                                        <th>제목</th>
                                        <th>작성자</th>
                                        <th>작성일</th>
                                        <th>수정일</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="list" items="${list }">
                                    <tr class="odd gradeX">
                                        <td>${list.bno}</td>
                                        <td><a class="move" href="<c:out value="${list.bno }"/>"> <c:out value="${list.title}"></c:out></a></td>
                                        <td>${list.writer}</td>
                                        <td class="center">${list.regDate}</td>
                                        <td class="center">${list.updateDate}</td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <!-- /.table-responsive -->
                            <!-- pageNation 시작 -->
                            
                            <div class='pull-right'>
                            <ul class="pagination">
                            <c:if test="${pageMaker.prev }">
                            <li class="paginate_button previous"><a href="${pageMaker.startPage-1 }">Previous</a></li>
                            </c:if>
                            <c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
                            <li class="paginate_button ${pageMaker.cri.pageNum==num?"active":"" }"><a href="${num }">${num }</a></li>
                            </c:forEach>
                            <c:if test="${pageMaker.next }">
                            <li class="paginate_button next"><a href="${pageMaker.endPage+1 }">Next</a></li>
                            </c:if>
                            </ul>
                            </div>
                            <form id='actionForm' action="/board/list" method="get">
                            <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
                            <input type="hidden" name="amount" value="${pageMaker.cri.amount }">
                            </form>
                            
                            <!-- Pagination 끝 -->
                            <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
                            	aria-labelledby="myModallabel" aria-hidden="true">
                            	<div class="modal-dialog">
                            	<div class="modal-content">
                            	<div class="modal-header">
                            	<button type="button" class="close" data-dismiss="modal"
                            		aria-hidden="true">&times;</button>
                            		<h4 class="modal-title" id="myModalLabel">Modal title</h4>
                            	</div>
                            	<div class="modal-body">처리가 완료되었습니다.</div>
                            	<div class="modal-footer">
                            		<button type="button" class="btn btn-default" data-dismiss="modal">
                            		Close
                            		</button>
                            		<button type="button" class="btn btn-default">
                            		Save Changes
                            		</button>
                            	</div>
                            	</div>
                            	</div>
                            	</div>
                            	<!-- /.modal fade -->
                        </div>
                        <!-- /.panel-body -->
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
    	var result='<c:out value="${result}"/>';
    	checkModal(result); //무조건 checkModal이 실행되도록
    	history.replaceState({},null,null); //현재의 history entry 변경 함수
    	function checkModal(result){
    		if(result === ''  || history.state ){
    			return;
    		}
    		if(parseInt(result)>0){
    			$(".modal-body").html(
    					"게시글"+parseInt(result)+"번이 등록되었습니다.");
    		}
    		$("#myModal").modal("show");
    	}
     });
     
    	$("#regBtn").on("click",function(){
    		self.location = "/board/register";
    	}); //버튼 클릭시 등록창으로 이동
    
    	
    	var actionForm=$("#actionForm"); //page active 파란색으로 되는 부분
    	$(".paginate_button a").on("click", function(e){
    		e.preventDefault();
    		console.log('click');
    		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
    		actionForm.submit();
    	});
    	
    	$(".move").on("click",function(e){ //page detailview로 클릭할 때 bno와 page number,같은거 다 가져가게하는거
    		e.preventDefault();
    		//console.log('click');
    		actionForm.append("<input type='hidden' name='bno' value='"+$(this).attr("href")+"'>");
    		actionForm.attr("action","/board/get");
    		actionForm.submit();
    	});
    </script>
</body>

</html>
