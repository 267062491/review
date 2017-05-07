/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 17-4-16
 * Time: 上午12:06
 * To change this template use File | Settings | File Templates.
 */

function get_text_rows(text) {   debugger ;
    var l1 =  text.length/80;
    var l2 =  text.split("\n").length;
    return l1+l2;
}
$(document).ready(function () {
    $(".form-control").each(function(){   debugger ;
        var num =get_text_rows($(this).val());
        if($(this).attr("readonly")!='readonly' && num<=1){
            num = 5 ;
        }
//        if($(this).attr("readonly")!='readonly' && num>=1){
//            num = 5 ;
//        }
        $(this).attr("rows",num);
    });
//        $("#textArea_contentDes_id").attr("rows",get_text_rows($('#textArea_contentDes_id').val()));
//        $("#textArea_coverProduct_id").attr("rows",get_text_rows($('#textArea_coverProduct_id').val()));
//        $("#textArea_fileTitle_id").attr("rows",get_text_rows($('#textArea_fileTitle_id').val()));
//        $("#textArea_oraLink_id").attr("rows",get_text_rows($('#textArea_oraLink_id').val()));
//        $("#textArea_result_id").attr("rows",get_text_rows($('#textArea_result_id').val()));
});