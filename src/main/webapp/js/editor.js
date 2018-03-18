$(document).ready(function () {
    var textarea = $('.markdown-textarea'),
        toolbar = $('<div class="markdown-editor" id="md-button-bar" />').insertBefore(textarea.parent())
        preview = $('<div id="md-preview" class="md-hidetab" />').insertAfter('.markdown-editor');

    var options = {};

    options.strings = {
        bold: '<strong> Ctrl+B',
        boldexample: 'Bold',

        italic: '<em> Ctrl+I',
        italicexample: 'Em',

        link: '<a> Ctrl+L',
        linkdescription: 'Link Description',

        quote:  '<blockquote> Ctrl+Q',
        quoteexample: 'Quote',

        code: '<pre><code> Ctrl+K',
        codeexample: 'Code',

        image: '<img> Ctrl+G',
        imagedescription: 'Image Description',

        olist: '<ol> Ctrl+O',
        ulist: '<ul> Ctrl+U',
        litem: 'Item',

        heading: '<h1>/<h2> Ctrl+H',
        headingexample: 'Headling',

        hr: '<hr> Ctrl+R',

        undo: 'Undo Ctrl+Z',
        redo: 'Redo Ctrl+Y',
        redomac: 'Redo Mac - Ctrl+Shift+Z',


        //imagedialog: '<p><b>Insert An Image</b></p>',
        linkdialog: '<p><b>Insert A Link</b></p>',

        ok: 'Okey',
        cancel: 'Cancel'
    };

    var converter = new Markdown.Converter(),
        editor = new Markdown.Editor(converter, '', options),
        diffMatch = new diff_match_patch(), last = '', preview = $('#md-preview'),
        mark = '@mark' + Math.ceil(Math.random() * 100000000) + '@',
        span = '<span class="diff" />';

    // 设置markdown
    Markdown.Extra.init(converter, {
        extensions  :   ["tables", "fenced_code_gfm", "def_list", "attr_list", "footnotes"]
    });

    // 自动跟随
    converter.hooks.chain('postConversion', function (html) {
        // clear special html tags
        html = html.replace(/<\/?(\!doctype|html|head|body|link|title|input|select|button|textarea|style|noscript)[^>]*>/ig, function (all) {
            return all.replace(/&/g, '&amp;')
                .replace(/</g, '&lt;')
                .replace(/>/g, '&gt;')
                .replace(/'/g, '&#039;')
                .replace(/"/g, '&quot;');
        });

        // clear hard breaks
        html = html.replace(/\s*((?:<br>\n)+)\s*(<\/?(?:p|div|h[1-6]|blockquote|pre|table|dl|ol|ul|address|form|fieldset|iframe|hr|legend|article|section|nav|aside|hgroup|header|footer|figcaption|li|dd|dt)[^\w])/gm, '$2');

        var diffs = diffMatch.diff_main(last, html);
        last = html;

        if (diffs.length > 0) {
            var stack = [], markStr = mark;

            for (var i = 0; i < diffs.length; i ++) {
                var diff = diffs[i], op = diff[0], str = diff[1]
                    sp = str.lastIndexOf('<'), ep = str.lastIndexOf('>');

                if (op != 0) {
                    if (sp >=0 && sp > ep) {
                        if (op > 0) {
                            stack.push(str.substring(0, sp) + markStr + str.substring(sp));
                        } else {
                            var lastStr = stack[stack.length - 1], lastSp = lastStr.lastIndexOf('<');
                            stack[stack.length - 1] = lastStr.substring(0, lastSp) + markStr + lastStr.substring(lastSp);
                        }
                    } else {
                        if (op > 0) {
                            stack.push(str + markStr);
                        } else {
                            stack.push(markStr);
                        }
                    }

                    markStr = '';
                } else {
                    stack.push(str);
                }
            }

            html = stack.join('');

            if (!markStr) {
                var pos = html.indexOf(mark), prev = html.substring(0, pos),
                    next = html.substr(pos + mark.length),
                    sp = prev.lastIndexOf('<'), ep = prev.lastIndexOf('>');

                if (sp >= 0 && sp > ep) {
                    html = prev.substring(0, sp) + span + prev.substring(sp) + next;
                } else {
                    html = prev + span + next;
                }
            }
        }

        return html;
    });

    editor.hooks.set("insertImageDialog", function (callback) {

        setTimeout( function() {
            var imageWasSelected = false;
            var content = $('<div/>').addClass("modal-content");
            var categorySelect = $('<select/>')
                .append($("<option/>").text("Все").val(""))
                .append($("<option/>").text("Кадебо").val("cadebou"))
                .append($("<option/>").text("Хилер").val("heeler"))
                .append($("<option/>").text("Минибуль").val("minibull"))
                .append($("<option/>").text("Щенки кадебо").val("cadebou-puppy"))
                .append($("<option/>").text("Щенки хилера").val("heeler-puppy"))
                .append($("<option/>").text("Шенки минибуля").val("minibull-puppy"))
                .append($("<option/>").text("Остальное").val("other"));


            var label = $('<label for="isFullsize"/>').text('Полный размер');
            var header = $('<div/>').addClass("modal-header")
                .append($('<a data-dismiss="modal"/>').addClass('close').text('x'))
                .append($('<h3/>').text('Выберите изображение'))
                .append(categorySelect)
                .append(label);
            $('<input type="checkbox" checked="true"/>').attr('id', 'isFullsize').prependTo(label);
            content.append(header);
            var md = $('<div role="document"/>').addClass('modal-dialog').append(content);
            var dialog = $('<div role="dialog"/>').addClass('modal fade').append(md);
            $('body').append(dialog);

            categorySelect.on('change', function(){
                fillThumbs();
            });
            function fillThumbs(){
                $.getJSON( "/ajax/imageThumbs.json?tag="+categorySelect.val(), function( data ) {
                      $(".modal-body").remove();
                      var body = $('<div id="mbody"/>').addClass('modal-body');
                      var i = 0;
                      var row;
                      $.each( data, function( key, val ) {
                           var thumb = $('<img/>').addClass("img-responsive img-rounded") .attr('src', val.thumb).attr("data-img-full", val.full);
                           if(i%3 === 0){
                               row = $("<div/>").addClass("row row-offcanvas");
                               body.append(row);
                           }
                           i++;
                           row.append($('<div/>').addClass("col-xs-6 col-lg-4").append(thumb));
                           thumb.on('click', function(){
                               if($('#isFullsize').prop("checked")){
                                    callback(location.protocol + "//" + location.host + $(this).attr('data-img-full'));
                               }else{
                                    callback(this.src);
                               }
                               imageWasSelected = true;
                               $(".modal").modal('hide');
                           });
                      });
                      $(".modal-content").append(body);
                });
            }
            dialog.on('shown.bs.modal', function () {
                  fillThumbs();
            });

            dialog.on('hidden.bs.modal', function () {
                    dialog.remove();
                    if(!imageWasSelected){
                        callback(null);
                    }
            });
            dialog.modal();

        }, 0);
        return true; // tell the editor that we'll take care of getting the image url
    });

    editor.hooks.chain('onPreviewRefresh', function () {
        var diff = $('.diff', preview), scrolled = false;

        $('img', preview).load(function () {
            if (scrolled) {
                preview.scrollTo(diff, {
                    offset  :   - 50
                });
            }
        });

        if (diff.length > 0) {
            var p = diff.position(), lh = diff.parent().css('line-height');
            lh = !!lh ? parseInt(lh) : 0;

            if (p.top < 0 || p.top > preview.height() - lh) {
                preview.scrollTo(diff, {
                    offset  :   - 50
                });
                scrolled = true;
            }
        }
    });

    var th = textarea.height(), ph = preview.height();

    editor.run();

    // 编辑预览切换
    var edittab = $('#md-button-bar').prepend('<div class="md-edittab"><a href="#md-editarea" class="active">Editor</a><a href="#md-preview">Preview</a></div>'),
        editarea = $(textarea.parent()).attr("id", "md-editarea");

    $(".md-edittab a").click(function() {
        $(".md-edittab a").removeClass('active');
        $(this).addClass("active");
        $("#md-editarea, #md-preview").addClass("md-hidetab");

        var selected_tab = $(this).attr("href"),
            selected_el = $(selected_tab).removeClass("md-hidetab");

        // 预览时隐藏编辑器按钮
        if (selected_tab == "#md-preview") {
            $("#md-button-row").addClass("md-visualhide");
        } else {
            $("#md-button-row").removeClass("md-visualhide");
        }

        // 预览和编辑窗口高度一致
        $("#md-preview").outerHeight($("#md-editarea").innerHeight());

        return false;
    });
});
