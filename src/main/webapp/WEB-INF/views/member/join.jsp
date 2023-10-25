<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
<%@include file="/WEB-INF/views/comm/plugin2.jsp" %>

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



<div class="container">
  <div class="text-center">
  <div class="box box-primary">
<div class="box-header with-border">
<h1 class="box-title">회원가입</h3>
</div>
 <div class="mb-4"></div>
  <div class="mb-4"></div>
    <div class="mb-4"></div>

<form role="form">
<div class="box-body">
<div class="form-group">
<form>
  <div class="form-group row">
    <label for="mbsp_id" class="col-sm-2 col-form-label">아이디</label>
    <div class="col-sm-8">
      <input type="text" class="form-control" id="mbsp_id" name="mbsp_id" placeholder="Enter ID">
    </div>
    <div class="col-sm-2">
    <button type="button" class="btn btn-outline-info" id="idcheck">중복확인</button>
    </div>
  </div>
      <div class="form-group row">
    <label for="mbsp_password" class="col-sm-2 col-form-label">비밀번호</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" id="mbsp_password" name="mbsp_password" placeholder="Enter password">
    </div>
  </div>
      <div class="form-group row">
    <label for="mbsp_password2" class="col-sm-2 col-form-label">비밀번호 확인</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" id="mbsp_password2"  placeholder="Enter password">
    </div>
  </div>
    <div class="form-group row">
    <label for="mbsp_name" class="col-sm-2 col-form-label">이름</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="mbsp_name" name="mbsp_name" placeholder="Enter Name">
    </div>
  </div>
  <div class="form-group row">
    <label for="mbsp_email" class="col-sm-2 col-form-label">이메일</label>
    <div class="col-sm-8">
      <input type="email" class="form-control" id="mbsp_email" name="mbsp_email" placeholder="Enter email">
    </div>
    <div class="col-sm-2">
      <button type="button" class="btn btn-outline-info" id="mailAuth">메일인증</button>
    </div>
  </div>
  <div class="form-group row">
    <label for="authcode" class="col-sm-2 col-form-label">메일인증</label>
    <div class="col-sm-8">
      <input type="text" class="form-control" id="authcode" name="authcode" placeholder="Enter ID">
    </div>
    <div class="col-sm-2">
    <button type="button" class="btn btn-outline-info" id="">인증확인</button>
    </div>
  </div>
    <div class="form-group row">
    <label for="sample2_postcode" class="col-sm-2 col-form-label">우편번호</label>
    <div class="col-sm-8">
      <input type="text" class="form-control" id="sample2_postcode" name="mbsp_zipcode" placeholder="Enter zipcode">
    </div>
    <div class="col-sm-2">
     <button type="button" onclick="sample2_execDaumPostcode()" class="btn btn-outline-info">우편변호찾기</button>
     </div>
  </div>
    <div class="form-group row">
    <label for="sample2_address" class="col-sm-2 col-form-label">주소</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="sample2_address" name="mbsp_addr" placeholder="Enter addr">
    </div>
  </div>
    <div class="form-group row">
    <label for="sample2_detailAddress" class="col-sm-2 col-form-label">상세주소</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="sample2_detailAddress" name="mbsp_deaddr" placeholder="Enter deaddr">
      <input type="hidden" id="sample2_extraAddress" placeholder="참고항목">
    </div>
  </div>
  
    <div class="form-group row">
    <label for="mbsp_phone" class="col-sm-2 col-form-label">전화번호</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="mbsp_phone" name="mbsp_phone" placeholder="Enter phonenumber">
    </div>
  </div>
</form>

<div class="box-footer">
<button type="submit" class="btn btn-primary">Submit</button>
</div>
</form>
</div>

  </div>
  </div>

<%@include file="/WEB-INF/views/comm/footer.jsp" %>
</div>

<!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
<div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
</div>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    // 우편번호 찾기 화면을 넣을 element
    var element_layer = document.getElementById('layer');

    function closeDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element_layer.style.display = 'none';
    }

    function sample2_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample2_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample2_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample2_postcode').value = data.zonecode;
                document.getElementById("sample2_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample2_detailAddress").focus();

                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                element_layer.style.display = 'none';
            },
            width : '100%',
            height : '100%',
            maxSuggestItems : 5
        }).embed(element_layer);

        // iframe을 넣은 element를 보이게 한다.
        element_layer.style.display = 'block';

        // iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
        initLayerPosition();
    }

    // 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
    // resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
    // 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
    function initLayerPosition(){
        var width = 300; //우편번호서비스가 들어갈 element의 width
        var height = 400; //우편번호서비스가 들어갈 element의 height
        var borderWidth = 5; //샘플에서 사용하는 border의 두께

        // 위에서 선언한 값들을 실제 element에 넣는다.
        element_layer.style.width = width + 'px';
        element_layer.style.height = height + 'px';
        element_layer.style.border = borderWidth + 'px solid';
        // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
        element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
        element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
    }
</script>
<%@include file="/WEB-INF/views/comm/plugin.jsp" %>
    <script>
    // jquery.slim.min.js 파일에 jquery 명령어가 정의되어 있음
    // 별칭: $  -> jQuery()함수
    // read()이벤트 메서드 : 브라우저가 html태그를 모두 읽고난 후에 동작하는 이벤트 특징.
    // 자바스크립트 이벤트 등록 : https://www.w3schools.com/js/js_htmldom_eventlistener.asp
    $(document).ready(function() {

      let useIDCheck = false // 아이디 중복체크 사용유무

      //document.getElementById("idcheck")
    	$("#idcheck").click(function() {
       // alert("아이디 중복체크");
       if($("#mbsp_id"). val() =="") {
        alert("아이디를 입력하세요.");
        $("#mbsp_id").focus();
        return;
       }

       //아이디 중복체크
       $.ajax({
        url : '/member/idcheck',
        type : 'get',
        dataType : 'text',
        data : {mbsp_id : $("#mbsp_id").val()},
        success: function(result) {
          if(result == "yes") {
            alert("아이디 사용가능");
            useIDCheck = true;
          }else {
            alert("아이디 사용불가능");
            useIDCheck = false;
            $("#mbsp_id").val("");  //아이디 텍스트박스를 값을 지움
            $("#mbsp_id").focus();  //포커스
          }
        }
       });
      });

      //메일인증요청
      $("#mailAuth").click(function() {
        if($("#mbsp_email").val() == ""){
        alert("이메일을 입력하세요.");
        $("#mbsp_email").focus();
      return;
        }
      
      $.ajax({
        url: '/email/authcode',
        type: 'get',
        dataType: 'text', //스프링에서 보내는 데이터의 타입 'success'
        data: {receiverMail: $("#mbsp_email").val()},
        success:function(result) {
          if(result == "success"){
            alert("인증메일이 발송되었습니다. 메일을 확인해주세요.")
          }
        }
      });
        });
      
    }); 
    </script>
  </body>
</html>
