<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="vi">

   <%@ include file="/common/head.jsp" %>
	<body>
			<%@ include file="/common/header.jsp" %>

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			
				<%@ include file="/common/sidebar.jsp" %>


			<sitemesh:write property='body'/>
		
		</div><!-- /.main-container -->

	</body>
</html>
