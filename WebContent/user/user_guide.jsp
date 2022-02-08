<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.a {
	color: gray;
}

.p {		
	font-size: 13px;
}
.title {
	margin-top: 100px;
}

.tabs {
   position: relative;
   padding: 0;
   list-style: none;
   min-height: 300px; 
   overflow: hidden; /* 탭 외곽에 위치한 나머지 탭 컨텐츠가 보이지 않도록 가림 */
   margin: 0px 25px 25px 25px;
}
.tab {
    float: left;
    padding: 10px 0;
}
.tab label { /* 탭 헤더 */
    position: relative;
    background: #eee;
    padding: 8px 20px;
    border: 1px solid #ccc;
}
.tab [type="radio"] {
    display: none;
}
.tab .content { /* 탭 컨텐츠 영역 */
    position: absolute;
    background: white;
    top: 39px;
    left: 0;
    right: 0;
    bottom: 0;
    box-sizing: border-box;
    border: 1px solid #ccc;
    overflow-y: auto;
    padding: 20px;
    font-size: 13px;
}
.tab .content > article { 
    transform: translateX(-100%);
    transition: all 0.5s ease-in-out;
}
.tab [type="radio"]:checked ~ label {
    background: white;
    border-bottom: 1px solid white;
    z-index: 2; 
}
.tab [type="radio"]:checked + label + .content {
    z-index: 1;
}
.tab [type="radio"]:checked + label + .content > article {
    display: block;
    transform: translateX(0);
}
.tab label {
	cursor: pointer;
}

</style>
</head>
<body>

	
<jsp:include page="../include/user_top.jsp" />

	

	<h3 class="title">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;이용안내</h3>

<p align="right" class="p"> <a href="<%=request.getContextPath() %>/main.do" class="a">home</a>> guide&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </p>
	<br> 
	
	
	<ul class="tabs">
    <li class="tab">
     <!--  첫 번째 탭에는 "checked" 속성을 추가해 활성화 시킴  -->
      <input type="radio" id="tab-1" name="tab-group-1" checked>
      <label for="tab-1">회원가입안내</label>
      <div class="content">
      <h3>회원가입 안내</h3>  <p>
       [회원가입] 메뉴를 통해 이용약관, 개인정보보호정책 동의 및 일정 양식의 가입항목을 기입함으로써 회원에
가입되며, 가입 즉시 서비스를 무료로 이용하실 수 있습니다.<br>
주문하실 때에 입력해야하는 각종 정보들을 일일이 입력하지 않으셔도 됩니다. 공동구매, 경매행사에 항상 참여하실 수 있습니다. 회원을
위한 이벤트 및 각종 할인행사에 참여하실 수 있습니다.</p>
      </div>
    </li>
    
    <li class="tab">
      <input type="radio" id="tab-2" name="tab-group-1">
      <label for="tab-2">주문안내</label>
      <div class="content">
       <h3>주문 안내</h3><p>
         상품주문은
      다음단계로 이루어집니다.<br>
      <br>
      - Step1: 상품검색<br>
      - Step2: 장바구니에 담기<br>
      - Step3: 회원ID 로그인 또는 비회원 주문<br>
      - Step4: 주문서 작성<br>
      - Step5: 결제방법선택 및 결제<br>
      - Step6: 주문 성공 화면 (주문번호)<br>
      <br>
      비회원 주문인경우 6단계에서 주문번호와 승인번호(카드결제시)를 꼭 메모해 두시기 바랍니다. 단, 회원인 경우 자동 저장되므로 따로
      관리하실 필요가 없습니다.
 </p>
      </div>
    </li>
    
    <li class="tab">
      <input type="radio" id="tab-3" name="tab-group-1">
      <label for="tab-3">결제안내</label>
      <div class="content">
        <h3>결제안내</h3>
        <p>고액결제의 경우 안전을 위해 카드사에서 확인전화를 드릴 수도 있습니다.</p><p>확인과정에서 도난 카드의 사용이나 타인 명의의 주문등
      정상적인 주문이 아니라고 판단될 경우 임의로 주문을 보류 또는 취소할 수 있습니다. &nbsp; <br>
      <br>
      무통장 입금은 상품 구매 대금은 PC뱅킹, 인터넷뱅킹, 텔레뱅킹 혹은 가까운 은행에서 직접 입금하시면 됩니다. &nbsp;<br>
      주문시 입력한&nbsp;입금자명과 실제입금자의 성명이 반드시 일치하여야 하며, 7일 이내로 입금을 하셔야 하며&nbsp;입금되지
      않은 주문은 자동취소 됩니다. <br>
 </p>
      </div>
    </li>
    
    <li class="tab">
      <input type="radio" id="tab-4" name="tab-group-1">
      <label for="tab-4">배송안내</label>
      <div class="content">
         <h3>배송안내</h3>
        <ul>
<li>배송 방법 : 택배</li>
            <li>배송 지역 : 전국지역</li>
            <li>배송 비용 : 조건부 무료 : 주문 금액 KRW 80,000 미만일 때 배송비 KRW 2,500을 추가합니다.</li>
            <li>배송 기간 : 1일 ~ 3일</li>
            <li>배송 안내 : - 산간벽지나 도서지방은 별도의 추가금액을 지불하셔야 하는 경우가 있습니다.<br>
    고객님께서 주문하신 상품은 입금 확인후 배송해 드립니다. 다만, 상품종류에 따라서 상품의 배송이 다소 지연될 수 있습니다.<br>
</li>
        </ul>
      </div>
    </li>
    
    <li class="tab">
      <input type="radio" id="tab-5" name="tab-group-1">
      <label for="tab-5">교환/반품안내</label>
      <div class="content">
       <h3>교환/반품안내</h3>  <p>
  JSP는 반품배송비를 입금으로 받고있습니다. 동봉을 해주신 배송비는 분실시 저희쪽에서 책임지지 않는점 참고부탁드리겠습니다. <br>
  주문자명과 동일한 이름으로 입금해주셔야지 빠른 환불 가능하십니다.)  계좌 :신한 367161-42-517824[교환 및 반품안내]<br> 
- JSP Q&A 게시판에 '교환&반품' 게시글로 접수 또는 고객센터(070-4477-9922) 접수 해주셔야 하십니다. <br>
전자상거래법에 의한 상품의 철약철회(반품및교환) 가능일은 수령일로부터 7일이내에 철약철회(반품및교환) 의사표현을 해주셔야 가능하십니다.<br>
 상품 수령 날짜 기준 10일이내 상품 미반환시 청약철회(반품/교환)를 취소하는 것으로 간주합니다.-반품을 원하실 경우, 반드시 배송비를 입금해주셔야 하시며(배송비 차감후 환불은 불가능하십니다.)<br>
  >&nbsp;동봉을 하여 분실된 배송비에 대해서&nbsp; 저희 JSP는 책임지지 않습니다&nbsp;배송비 <strong>신한 367161-42-517824 김쌍용(JSP)</strong> 으로 입금 부탁드리겠습니다.<br>
   [배송비는 동봉이 아닌 입금을 받고있습니다. 주문자명과 동일한 이름으로 입금 부탁드리겠습니다.] - 무통장입금,계좌이체 하신고객님들은 반품봉투 앞면에 환불받으실 계좌정보,은행명,예금주명 작성하여 보내주시면 되십니다.<br>
   (상품이 도착하였을 때 계좌정보 확인 안될 시 환불 보류 처리되는 점 참고 부탁드립니다.) - 타 택배(우체국,편의점)를 이용하실 경우, 택배비는 선불로 발송 부탁드립니다.가급적 CJ대한통운 이용 부탁드리겠습니다.<br>
     타 택배(우체국,편의점)를 이용하실 경우에는 저희 JSP 에서 배송사고(분실,파손 등)에 대한 처리가 어렵습니다.- 택배 이동중 사고건 (분실,파손등) 에 대해선 저희측에서 처리가 어려우니 양해부탁드립니다.<br>
   - 구매자 변심으로 인한 교환은 제한이 없으며 왕복 배송비는 고객님의 부담입니다. <br>
   제품 하자 및 오배송으로 인한 교환의 경우  JSP가 왕복배송비를 부담하여 동일상품(동일색상,동일사이즈)으로 교환하여 드리지만 다른 색상,사이즈로 교환시 변심배송비 2500원 부담해주셔야 합니다.<br>
  </p>
<h3>교환 및 반품이 불가능한 경우</h3><p>
- 상품 가치를 훼손한 경우 (착용흔적,담배,향수냄새,음식물,화장품자국) 등 발견될 경우 고객님께서 배송비 부담후 반송처리 - 택배이동과정에서 발생한 사고경우 택배사측에서 처리받으셔야 합니다.<br>
- 교환/반품 기간은 상품 수령 날짜 기준으로 하여 7일이내에 청약철회(반품/교환) 의사표현을 해주셔야 하시며 청약철회(반품/교환) 접수 날짜 기준 7일이내
								</p></div>   
    </li>
    
    
    <li class="tab">
      <input type="radio" id="tab-6" name="tab-group-1">
      <label for="tab-6">환불안내</label>
      <div class="content">
         <h3>환불안내</h3><p>
        환불시 반품 확인여부를 확인한 후 3영업일 이내에 결제 금액을 환불해 드립니다. <br>
            신용카드로 결제하신 경우는 신용카드 승인을 취소하여 결제 대금이 청구되지 않게 합니다.<br>
            (단, 신용카드 결제일자에 맞추어 대금이 청구 될수 있으면 이경우 익월 신용카드 대금청구시 카드사에서 환급처리<br>
            됩니다.)  </p>
      </div>
    </li>

    <li class="tab">
      <input type="radio" id="tab-7" name="tab-group-1">
      <label for="tab-7">기타안내</label>
      <div class="content">
        <h3>기타안내</h3><p>
       <b>이용기간</b><br>
주문으로 발생한 적립금은 배송완료 체크시점으로 부터 20일이 지나야 실제 사용 가능 적립금으로 변환됩니다.
20일 동안은 미가용 적립금으로 분류 됩니다. 미가용 적립금은 반품, 구매취소 등을 대비한 실질적인 구입이 되지 않은
주문의 적립금 입니다.
<br>사용가능한 적립금(총 적립금-사용된적립금-미가용적립금)은 상품구매시 즉시 사용하실 수 있습니다.<br>
<br>
<b>이용조건</b><br>
적립금사용시 최소구매가능적립금(구매가능한 적립금 요구선)은 KRW 100 입니다. 적립금 사용시
최대구매가능적립금(적립금 1회 사용 가능 최대금액)은 '한도제한없음' 입니다.<br>
<br>
<b>소멸조건</b><br>
주문취소/환불시에 상품구매로 적립된 적립금은 함께 취소됩니다. 회원 탈퇴시에는 적립금은 자동적으로
소멸됩니다. 최종 적립금 발생일로부터 3년 동안 추가적립금 누적이 없을 경우에도 적립금은 소멸됩니다.
 </p>
      </div>
   </ul>
   
	

	
	<br><br><br>
<jsp:include page="../include/user_bottom.jsp" />
</body>
</html>