<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/web-components/taglib.jsp" %>
<!DOCTYPE html>
<html lang="vi">

<%@ include file="/common/web-components/head.jsp" %>

<body>
	<%@ include file="/common/web-components/header.jsp" %>

	<div class="wrapper d-flex align-items-stretch">
		<%@ include file="/common/web-components/sidebar.jsp" %>
		<sitemesh:write property='body' />

	</div>
	<script>

		$('#sb-collapse').click(() => {
			$('#sidebar').toggleClass('active');
			const sb_class = $('#collapse-arrow').attr('class');
			if (sb_class.includes('right')) $('#collapse-arrow').attr('class', 'fa fa-arrow-left');
			else $('#collapse-arrow').attr('class', 'fa fa-arrow-right');
		});

		const current_url = window.location.href;

		if (current_url.includes('building')) {
			$('#act-building').attr('class', 'active');
		}
		else if (current_url.includes('customer')) $('#act-customer').attr('class', 'active');
		else if (current_url.includes('user')) $('#act-user').attr('class', 'active');
		else if (current_url.includes('account')) $('#act-account').attr('class', 'active');

	</script>

</body>

</html>