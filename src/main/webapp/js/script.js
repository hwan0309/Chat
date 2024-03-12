$(function(){
 
});

  function handleSubmit(){
    if($("#username").val() == ""){
       alert("이름을 입력하세요.");
       $("#username").focus();
       return false;
    }
  };
  
  function send(form) {
     $('#msg').val($('#temp').val());
     $('#temp').val('');
     $('#temp').focus();
     return true;
  }