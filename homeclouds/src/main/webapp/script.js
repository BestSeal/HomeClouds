jQuery(function(){


    let path = Cookies.get('login');
    Cookies.set('path',path);
    Cookies.set('login',"docu");//убрать позже
    console.log("cookies set");
    $.ajax({
        type: "POST",
        url: "/hop/",
        data: "path=" + path + "&action=open",
        success:function(responseText)
    {
        console.log(responseText);
        Cookies.set("path", path);
        console.log(path);
        $('.file-explorer').empty();
        $('.file-explorer').append($(responseText));
        $('.context-menu').hide(100);
        $('.context-menu-folder').hide(100);
        $('.path').empty();
        $('.path').append("<p>" + path + "</p>");
    }
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
  
   $('.upload-message').on('mouseleave',function(){
    $('.upload-message').hide(250);
  });
  
  $('.context-menu-folder').on('mouseleave',function(){
    $('.context-menu-folder').hide(250);
    $('.context-menu-folder').attr('data-name',"");
  });

    $('.upload').on('click',function(event){
        $('.upload-message').show(250);
        $('.upload-message').css('top', event.pageY - 12);
        $('.upload-message').css('left', event.pageX - 12);
    });
    
    var files;
    
    $('input[type=file]').on('change', function(){
       console.log((this).files);
       files = $(this).files;
    });
        
        
    $('.upload-icon').on('click', function(event){
        console.log('click');
        var file_data = $("#upload-input").prop("files")[0];  
        var data = new FormData();
        data.append('file',file_data);
   
        $.ajax({
            url : '/upload/',
            type : 'POST',
            data : data,
            cache : false,
            dataType : 'json',
            processData : false,
            contentType : false, 
            success : function()
            {
                $('.upload-message').hide(250);
                $.ajax({
                    type: "POST",
                    url: "/hop/",
                    data: "path=" + Cookies.get('path')  + "&action=open" ,
                    success : function (responseText)
                    {
                        console.log(responseText);
                        $('.file-explorer').empty();
                        $('.file-explorer').append($(responseText));
                        $('.context-menu').hide(100);
                        $('.context-menu-folder').hide(100);
                    },

                });

            },
            error: function(msg)
            {
                console.log('всё пропало! ' + msg);
                $('.upload-message').hide(250);
                $.ajax({
                    type: "POST",
                    url: "/hop/",
                    data: "path=" + Cookies.get('path')  + "&action=open" ,
                    success : function (responseText)
                    {
                        console.log(responseText);
                        $('.file-explorer').empty();
                        $('.file-explorer').append($(responseText));
                        $('.context-menu').hide(100);
                        $('.context-menu-folder').hide(100);
                    },

                });
            }
        })
    });


  $('.context-icon').on('click',function(event){
    let login = Cookies.get("login");
    let act = $(this).attr('data-do');
    let name = $(this).parent().parent().parent().attr('data-name');
    let path = Cookies.get("path");
    let tmp = path + "/" + name;
    switch(act)
    {
        case 'open':
            console.log("open " + name);
            $.ajax({
                type: "POST",
                url: "/hop/",
                data: "path=" + tmp  + "&action=" + act,
                success : function (responseText)
            {
                console.log(responseText);
                Cookies.set("path", tmp);
                console.log(tmp);
                $('.file-explorer').empty();
                $('.file-explorer').append($(responseText));
                $('.context-menu').hide(100);
                $('.context-menu-folder').hide(100);
                $('.path').empty();
                $('.path').append("<p>" + tmp + "</p>");
            }
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
            console.log("download " + name);
            
            $.ajax({
                type: "GET",
                url: '/share/',
		data: "login=" + login + "&path=" +  path + "&action=" + act + "&file=" + name,
                xhrFields: {
                    'responseType': 'blob'
                  },
                success : function(data, status, xhr){
                        $('.context-menu').hide(100);
                        $('.context-menu-folder').hide(100);
                        console.log(xhr.getResponseHeader('Content-Disposition'));
                        var link = document.createElement('a'), filename = 'file.xlsx';
                        if(xhr.getResponseHeader('Content-Disposition')){//имя файла
                            filename = xhr.getResponseHeader('Content-Disposition');
                            filename=filename.split("filename=")[1];
                            filename=decodeURIComponent(escape(filename));
                        }
                        console.log(data);
                        link.href = URL.createObjectURL(data);
                        link.download = filename;
                        link.click();
                    }
            });
            break;
        case 'delete':
            console.log("delete " + name);
            $.ajax({
                type: "GET",
                url: "/delete/",
                data: "login=" + login + "&path=" +  path + "&action=" + act + "&file=" + name,
                success: function()
            {
                $.ajax({
                    type: "POST",
                    url: "/hop/",
                    data: "path=" + Cookies.get('path')  + "&action=open" ,
                    success : function (responseText)
                    {
                        console.log(responseText);
                        $('.file-explorer').empty();
                        $('.file-explorer').append($(responseText));
                        $('.context-menu').hide(100);
                        $('.context-menu-folder').hide(100);
                    },

                });
            }
            });
            break;
    }
    
  });
});
