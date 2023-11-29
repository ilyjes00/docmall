<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

  <%@include file="/WEB-INF/views/admin/include/plugin1.jsp" %>
<tr class="tr_detail_info">
      <td colspan="9">
        <table class="table table-sm" id="order_info_tbl">
          <caption style="display :table-caption; text-align: center;color: red;font-weight: bold;"> [주문상세정보] </caption>
          <thead>
            <tr>
              <th scope="col">주문번호</th>
              <th scope="col">상품코드</th>
              <th scope="col">상품이미지</th>
              <th scope="col">상품명</th>
              <th scope="col">주문수량</th>
              <th scope="col">주문금액</th>
              <th scope="col">비고</th>
            </tr>
          </thead>
          <tbody>
          	<c:forEach items="${orderProductList }" var="orderProductVO">
            <tr>
              <th scope="row">${orderProductVO.orderDetailVO.ord_code }</th>
              <td>${orderProductVO.productVO.pro_num }</td>
              <td><img src="/admin/order/imageDisplay?dateFolderName=${orderProductVO.productVO.pro_up_folder}&fileName=${orderProductVO.productVO.pro_img}"></td>
              <td>${orderProductVO.productVO.pro_name }</td>
              <td>${orderProductVO.orderDetailVO.dt_amount }</td>
              <td>${orderProductVO.productVO.pro_price * orderProductVO.orderDetailVO.dt_amount }</td>
              <td><button type="button" name="btn_order_delete" class="btn btn-danger" data-ord_code="${orderProductVO.orderDetailVO.ord_code }" data-pro_num="${orderProductVO.orderDetailVO.pro_num }">delete</button></td>
            </tr>
            </c:forEach>
          </tbody>
        </table>
      </td>
    </tr>
    
    <%@include file="/WEB-INF/views/admin/include/plugin2.jsp" %>
<script>
  $(document).ready(function() {

    let actionForm = $("#actionForm");

    // [이전] 1 2 3 4 5 [다음] 클릭 이벤트 설정. <a>태그
    $(".movepage").on("click", function(e) {
      e.preventDefault(); // a태그의 href 링크기능을 제거. href속성에 페이지번호를 숨겨둠.

      actionForm.attr("action", "/admin/order/order_list");
      actionForm.find("input[name='pageNum']").val($(this).attr("href"));

       actionForm.submit();
    });
    
  //주문상세에서 개별삭제
  $("table#order_info_tbl").on("click", "button[name='btn_order_delete']", function() {

    //console.log("삭제");

    //주문상세테이블은 primary key가 2개컬럼을 대상으로 복합키 설정이 되어있다.
    let ord_code = $(this).data("ord_code");
    let pro_num  = $(this).data("pro_num");

    if(!confirm("상품코드" + pro_num + "번을 삭제하시겠습니까?")) return;

    //<input type='hidden' name='ord_code' value=''>

    actionForm.append("<input type='hidden' name='ord_code' value='" + ord_code + "'>");
    actionForm.append("<input type='hidden' name='pro_num' value='" + pro_num + "'>");

    actionForm.attr("action", "/admin/order/order_product_delete");
    actionForm.submit();

  });
  


}); // ready 이벤트
</script>
    
</body>
</html>