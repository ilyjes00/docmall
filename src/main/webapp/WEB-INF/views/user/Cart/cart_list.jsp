<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.101.0">
    <title>Pricing example · Bootstrap v4.6</title>


    

    <!-- Bootstrap core CSS -->
<link href="https://getbootstrap.com/docs/4.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<link rel="stylesheet" href="https://getbootstrap.com/docs/4.6/examples/pricing/pricing.css">


    <!-- Favicons -->


    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>

    
    <!-- Custom styles for this template -->
    <link rel="stylesheet">
  </head>
  <body>
    

<%@include file="/WEB-INF/views/comm/header.jsp" %>



<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">

  <h1 class="display-4"></h1>
  <p>장바구니</p>
</div>

<div class="container">

  <table class="table table-striped">
    <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">상품</th>
        <th scope="col">상품명</th>
        <th scope="col">판매가</th>
        <th scope="col">수량</th>
        <th scope="col">할인</th>
        <th scope="col">합계</th>
        <th scope="col">비고</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${cart_list}" var="cartDTO"> 
      <tr>
        <td scope="row"><input type="checkbox" name="cart_code"  value="${cartDTO.cart_code}">체크박스</td>
        
        <td> 
          <img width="50%" height="35" src="/user/Cart/cart_list/imageDisplay?dateFolderName=${cartDTO.pro_up_folder }&fileName=${cartDTO.pro_img }">
        </td>
        <td>${cartDTO.pro_name }</td>
        <td><span id="unitprice">${cartDTO.pro_price }</span></td>
        <td><input type="number" name="cart_amount" value="${cartDTO.cart_amount }" style="width: 35px;"><button type="button" name="btn_cart_amount_change" class="btn btn-danger">변경</button></td>
        <td><span id="unitDiscount">${cartDTO.pro_discount * 1/100 }</span></td>
        <td><span class ="unitTotalprice" id="unitTotalprice">${(cartDTO.pro_price - (cartDTO.pro_price * (cartDTO.pro_discount * 1/100))) * cartDTO.cart_amount}</span></td>
        <td><button type="button" name="btn_ajax_cart_del" class="btn btn-danger">삭제하기(ajax)</button></td>
        <td><button type="button" name="btn_cart_del" class="btn btn-danger">삭제하기(non-ajax)</button></td>
      </tr>
      </c:forEach>
    </tbody>
    <tfoot>
      <tr>
        <td colspan="8"><button type="button" class="btn btn-danger">선택삭제</button></td>
      </tr>
      <tr>
        <td colspan="8">
          최종결제금액 : <span id="cart_total_price">${cart_total_price}</span>
        </td>
      </tr>
      <tr>
        <td colspan="10" style="text-align: center">
          <button type="button" id="btn_product" class="btn btn-primary">쇼핑계속하기</button> 
          <button type="button" id="btn_cart_empty" class="btn btn-primary">장바구니 비우기</button> 
          <button type="button" id="btn_order" class="btn btn-primary">주문하기</button>
        </td>
      </tr>
    </tfoot>
  </table>
  </div>
  
  
  <div class="container">
    
</div>

<%@include file="/WEB-INF/views/comm/footer.jsp" %>
</div>

<%@include file="/WEB-INF/views/comm/plugin.jsp" %>
<script src="/js/category_menu.js"></script>

<script>
  $(document).ready(function () {


    //장바구니 목록에서 변경클릭시 
    $("button[name='btn_cart_amount_change']").on("click", function() {

      let cur_btn_change = $(this);

      let cart_amount = $(this).parent().find("input[name='cart_amount']").val();
       console.log("수량" , cart_amount);

       let cart_code = $(this).parent().parent().find("input[name='cart_code']").val();
      console.log("장바구니코드", cart_code);

      $.ajax({
        url:'/user/Cart/cart_list/cart_amount_change',
        type: 'post',
        data: {cart_code : cart_code, cart_amount : cart_amount},
        dataType: 'Text',
        success: function(result) {
          if(result == 'success') {
            console.log("수량 변경이 성공적으로 반영되었습니다.");

            //금액 = 판매가 - (판매가 * 할인율) * 수량
            let unitprice = cur_btn_change.parent().parent().find("span#unitprice").text();
            
            let unitDiscount = cur_btn_change.parent().parent().find("span#unitDiscount").text();

            let unitTotalprice = cur_btn_change.parent().parent().find("span#unitTotalprice");
          
            unitTotalprice.text((unitprice - (unitprice * unitDiscount)) * cart_amount);



            //전체주문금액
            fn_cart_sum_price();
          }
        }
      });

    });
          //장바구니 삭제 ajax
          $("button[name='btn_ajax_cart_del']").on("click" , function() {

            if(!confirm("장바구니 상품을 삭제하시겠습니까?")) return;

            let cur_btn_delete = $(this); //선택된 버튼태그의 위치를 참조
            let cart_code = $(this).parent().parent().find("input[name='cart_code']").val();
        //console.log("장바구니코드", cart_code);

            $.ajax({
              url : '/user/Cart/cart_list_del',
              type : 'post',
              data : {cart_code : cart_code},
              dataType : 'text',
              success : function(result) {
                if(result == "success"){
                  alert("장바구니 상품이 삭제되었습니다.");

                  cur_btn_delete.parent().parent().remove(); //삭제된 장바구니 데이터행 제거
                }
              }

            });


      });
                  //장바구니삭제 non-ajax
                  $("button[name='btn_cart_del']").on("click" , function() {

                  if(!confirm("장바구니 상품을 삭제하시겠습니까?")) return;

                  let cart_code = $(this).parent().parent().find("input[name='cart_code']").val();
                  location.href = "/user/Cart/cart_list_del?cart_code=" + cart_code;
});

        //주문하기
        $("button#btn_order").on("click", function(){
          location.href = "/user/order/order_info"
        });
    
  });

  function fn_cart_sum_price() {
                //전체주문금액
                let sumPrice = 0;
              $(".unitTotalprice").each(function() {
               sumPrice += Number($(this).text());
              });
              $("#cart_total_price").text(sumPrice);
          }
  

</script>

  </body>
</html>
