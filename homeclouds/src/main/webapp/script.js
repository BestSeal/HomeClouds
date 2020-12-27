jQuery(function(){

    function openSuccess(responseText)
    {
        console.log(responseText);
        Cookies.set("path", path);
        $('.file-explorer').empty();
        $('.file-explorer').append($(responseText));
        $('.context-menu').hide(100);
        $('.context-menu-folder').hide(100);
        $('.path').empty();
        $('.path').append("<p>" + path + "</p>");
    }
    let path = Cookies.get('login');
    Cookies.set('path',path);
    Cookies.set('login',"docu");//убрать позже
    $.ajax({
        type: "POST",
        url: "/hop/",
        data: "path=" + path + "&action=open",
        success : openSuccess
    });

  $('.file-explorer').on('contextmenu','.button',function(event){
    event.preventDefault();
    if ($(this).attr('data-type') == 'folder')
    {
        $('.context-menu-folder').attr('data-name',$(this).attr('data-name'));
        $('.context-menu-folder').show(250);
        $('.context-menu-folder').css('top', event.pageY - 12);
        $('.context-menu-folder').css('left', event.pageX - 12);
    }
    else
    {
        $('.context-menu').attr('data-name',$(this).attr('data-name'));
        $('.context-menu').show(250);
        $('.context-menu').css('top', event.pageY - 12);
        $('.context-menu').css('left', event.pageX - 12);
    }
  });

  $('.message').on('mouseleave',function(){
    $('.message').empty();
    $('.message').hide(250);
  });
  
  $('.context-menu').on('mouseleave',function(){
    $('.context-menu').hide(250);
    $('.context-menu').attr('data-name',"");
  });
  
  $('.context-menu-folder').on('mouseleave',function(){
    $('.context-menu-folder').hide(250);
    $('.context-menu-folder').attr('data-name',"");
  });

  $('.context-icon').on('click',function(event){
    let login = Cookies.get("login");
    let act = $(this).attr('data-do');
    let name = $(this).parent().parent().parent().attr('data-name');
    let path = Cookies.get("path");
    switch(act)
    {
        case 'open':
            console.log("open " + name);
            $.ajax({
                type: "POST",
                url: "/hop/",
                data: "path=" + path  + "&action=" + act,
                success : openSuccess
            });
            break;
        case 'send':
            console.log("send " + name);
            $.ajax({
                type: "GET",
                url: "/share/",
                data: "login=" + login + "&path=" +  path + "&action=" + act + "&file=" + name,
                success : function(response){
                    console.log(response);
                    $('.message').append("<p>Ссылка для обмена файлом: </p>" + response);
                    $('.message').show(250);
                    $('.message').css('top', event.pageY - 12);
                    $('.message').css('left', event.pageX - 12);
                }
            });
            break;
        case 'download':
            console.log("donwload " + name);
            break;
        case 'delete':
            console.log("delete " + name);
            break;
    }
    
  });
});
