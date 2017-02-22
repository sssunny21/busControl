$(function(){
    $( ".datepicker" ).datepicker({
        changeMonth: true ,
        changeYear: true ,
        showMonthAfterYear: true ,
        dateFormat: "yy-mm-dd",
        dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
        monthNames: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
        monthNamesShort: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
        numberOfMonths: 1
    });
});
function keypress(event)
{
 var key;
 if (window.event) { 
     // IE에서 이벤트를 확인하기 위한 설정 
     key = window.event.keyCode;
 } else { 
     // FireFox에서 이벤트를 확인하기 위한 설정 
     key = event.which;
 }
  
 if(event.shiftKey) event.preventDefault();
 if(key == 16) event.preventDefault();
 if (key == 46 || key == 8){}
 else 
 {
  if (key < 95) {
   if (key < 48 || key > 57) event.preventDefault();
  }
  else {
   if (key < 96 || key > 105) event.preventDefault();
  }
 }
}