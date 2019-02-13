<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ page import="com.dongle.group.model.vo.Group,com.dongle.member.model.vo.ReportReason,com.dongle.gallery.model.vo.GalleryCommentJoin,java.util.*,com.dongle.member.model.vo.Member,com.dongle.gallery.model.vo.GalleryPath" %>
<%
   List<GalleryPath> gplist=(List)request.getAttribute("gplist");
   List<GalleryCommentJoin> gclist=(List)request.getAttribute("gclist");
   int groupNo = (int)request.getAttribute("groupNo");
   Member loginMember = (Member)session.getAttribute("loginMember");
   List<ReportReason> relist = (List)request.getAttribute("relist");
   Group group =(Group)request.getAttribute("g");
   int cCount=0;
%>
   <link href="<%=request.getContextPath() %>/css/gallery_style.css" rel="stylesheet">
   <!-- 댓글창 시작 -->
      <ul>
         <%if(gclist.size()!=0){ %>
            <%for(GalleryCommentJoin g:gclist){ %>
               <%if(g.getGalCommentLevel()==1&&g.getGalCommentReportStatus().equals("N")){ %>
                  <li class='level1' style="list-style:none;">
                     <span class='ico_skin thumb_profile'>
                        <img class='img_profile' src='<%=request.getContextPath()%>/images/member_img/<%=g.getGroupMemberImageNewPath() %>'>
                     </span>
                     <span class='comment_box'>
                        <span class='comment-writer'><%=g.getGroupMemberNickname()%></span>
                        <span class='comment-date'>
                           <%=g.getGalCommentDate() %>
                           <p class='btn-comment-report' value='<%=g.getGalCommentNo()%>'  style='float:right;color:RGB(112,136,172);' >신고</p>
                           <input type='hidden' class='reportGalCommentNo' value='<%=g.getGalCommentNo()%>' >
                           <input type='hidden' class='reportCommentNickName' value='<%=g.getGroupMemberNickname()%>' >
                           <input type='hidden' class='reportCommentLevel' value='<%=g.getGalCommentLevel()%>'>
                           <input type='hidden' class='reportCMemberNo' value='<%=g.getMemberNo()%>' >
                        </span>
                        <br/>
                        <span class='comment_content'>
                           <%=g.getGalCommentContent() %>
                           <%if((loginMember.getMemberNo()==group.getMemberNo())||(g.getMemberNo()==loginMember.getMemberNo())){ %>
                              <button class='btn-delete' value='<%=g.getGalCommentNo()%>' style='float:right;color:RGB(112,136,172);' >삭제</button>
                           <%} %>
                           <button class='btn-reply' value='<%=g.getGalCommentNo()%>'>답글</button>
                        </span>
                     </span>
                  </li>
               <%} 
               else if(g.getGalCommentLevel()==2&&g.getGalCommentReportStatus().equals("N")){%>
                  <li class="level2" style="list-style:none;">
                     <span class='ico_skin thumb_profile'>
                        <img class='img_profile' src='<%=request.getContextPath()%>/images/member_img/<%=g.getGroupMemberImageNewPath() %>'>
                     </span>
                     <span class='comment_box'>
                        <span class='comment-writer'><%=g.getGroupMemberNickname()%></span>
                        <span class='comment-date'>
                           <%=g.getGalCommentDate() %>
                           <p class='btn-comment-report' style='float:right;color:RGB(112,136,172);' >신고</p>
                           <input type='hidden' class='reportGalCommentNo' value='<%=g.getGalCommentNo()%>' >
                           <input type='hidden' class='reportCommentNickName' value='<%=g.getGroupMemberNickname()%>' >
                           <input type='hidden' class='reportCommentLevel' value='<%=g.getGalCommentLevel()%>' >
                           <input type='hidden' class='reportCMemberNo' value='<%=g.getMemberNo()%>' >
                        </span>
                        <br/>
                        <span class='comment_content'>
                           <%=g.getGalCommentContent() %>
                           <%if((loginMember.getMemberNo()==group.getMemberNo())||(g.getMemberNo()==loginMember.getMemberNo())){ %>
                              <button class='btn-delete' value='<%=g.getGalCommentNo()%>' style='float:right;color:RGB(112,136,172);' >삭제</button>
                           <%} %>
                        </span>
                     </span>
                  </li>
               <%} %>
            <%} %>
         <%}%> 
      </ul>
      <input type="hidden" name="groupNo" id='groupNo' value="<%=gplist.get(0).getGroupNo() %>"/>
      <input type="hidden" name="galNo" id="galNo" value="<%=gplist.get(0).getGalNo() %>"/>
      <input type="hidden" name="galCommentWriterNo" id='galCommentWriterNo' value="<%=loginMember.getMemberNo() %>"/>
      <input type="hidden" name="galCommentLevel" id='galCommentLevel' value="1"/>
      <input type="hidden" name="galCommentRef" id='galCommentRef' value="0"/>
      <input type="hidden" name="albumCode" id='albumCode' value="<%=gplist.get(0).getAlbumCode()%>"/>
      <input type="hidden" name="galFileNo" id='galFileNo2' value="<%=gplist.get(0).getGalFileNo()%>"/>
      <fieldset class='modal_comment'>
         <legend class='screen_out'>댓글쓰기 폼</legend>
         <div class='comment_write'>
            <label for='comment' class='lab_write screen_out'>내용</label>
            <textarea name="galCommentContent" id='galCommentContent' placeholder="소중한 댓글을 입력해주세요" tabindex='3' style='resize:none;box-sizing: border-box;width:100%;height:80;border:1px solid #fff;'></textarea>
         </div>
         <div class='comment_btn'>
            <button type="button" class='btn-insert1' style='float:right;width:65px;height:28px;font-size:14px;line-height:15px;border-radius: 20px;border:none;background-color:white;'>입력</button>
         </div>
      </fieldset>
      <%if(relist!=null){ %>
         <form id='reportFrm' name="reportFrm">
               <input type="hidden" id="report1" name="report1" value="<%=relist.get(0).getReportCode()%>">
               <input type="hidden" id="reason1" name="reason1" value="<%=relist.get(0).getReportReason()%>">
               <input type="hidden" id="report2" name="report2" value="<%=relist.get(1).getReportCode()%>">
               <input type="hidden" id="reason2" name="reason2" value="<%=relist.get(1).getReportReason()%>">
               <input type="hidden" id="report3" name="report3" value="<%=relist.get(2).getReportCode()%>">
               <input type="hidden" id="reason3" name="reason3" value="<%=relist.get(2).getReportReason()%>">
               <input type="hidden" id="report4" name="report4" value="<%=relist.get(3).getReportCode()%>">
               <input type="hidden" id="reason4" name="reason4" value="<%=relist.get(3).getReportReason()%>">
               <input type="hidden" id="report5" name="report5" value="<%=relist.get(4).getReportCode()%>">
               <input type="hidden" id="reason5" name="reason5" value="<%=relist.get(4).getReportReason()%>">
               <input type="hidden" id="report6" name="report6" value="<%=relist.get(5).getReportCode()%>">
               <input type="hidden" id="reason6" name="reason6" value="<%=relist.get(5).getReportReason()%>">
               <input type="hidden" id="report7" name="report7" value="<%=relist.get(6).getReportCode()%>">
               <input type="hidden" id="reason7" name="reason7" value="<%=relist.get(6).getReportReason()%>">
               
                 <input type="hidden" id="reportNickName" name="reportNickName" value="">
                 <input type="hidden" id="reportCMemberNo" name="reportCMemberNo" value="">
               <input type="hidden" id="reportGalNo" name="reportGalNo" value="<%=gplist.get(0).getGalNo()%>">
               <input type="hidden" id="reportGroupNo" name="reportGroupNo" value="<%=groupNo%>">
               <input type="hidden" id="reportMemberNo" name="reportMemberNo" value="<%=gplist.get(0).getMemberNo()%>">
                <input type="hidden" id="reportAlbumCode" name="reportAlbumCode" value="<%=gplist.get(0).getAlbumCode()%>">
                <input type="hidden" id="reportCommentNo" name="reportCommentNo" value=""/>
                <input type="hidden" id="selectRecode" name="selectRecode" value=""/>
                <input type="hidden" id="reportGalCommentLevel" name="reportGalCommentLevel" value=""/> 
            </form>
         <%} %>
<script>
/* 댓글 신고하기 */
$(document).ready(function(){
   $('#btn-report').click(function(e){
      if(<%=loginMember.getMemberNo()%>==<%=gplist.get(0).getMemberNo()%>){
         alert('자신을 신고할 수 없습니다.');
         return;
      }
       var reportWin=window.open("<%=request.getContextPath()%>/views/gallery/galleryReport.jsp","reportWin","width=500, height=300, top=200,left=500,menubar=no, status=no, toolbar=no");
       var reportNickName='<%=gplist.get(0).getGroupMemberNickname()%>';
   });
   
   $('.btn-comment-report').click(function(e){
       var reportCMemberNo=$(this).siblings('.reportCMemberNo').val();
      if(<%=loginMember.getMemberNo()%>==reportCMemberNo){
         alert('자신을 신고할 수 없습니다.');
         return;
      }
       var reportWin=window.open("<%=request.getContextPath()%>/views/gallery/galleryReport.jsp","reportWin","width=500, height=300, top=200,left=500, menubar=no, status=no, toolbar=no");
       var reportCommentNo=$(this).siblings('.reportGalCommentNo').val();
       var reportCommentNickName=$(this).siblings('.reportCommentNickName').val();
       var reportGalCommentLevel=$(this).siblings('.reportCommentLevel').val();

       document.getElementById('reportCommentNo').value=reportCommentNo;
       document.getElementById('reportNickName').value=reportCommentNickName;
       document.getElementById('reportGalCommentLevel').value=reportGalCommentLevel;
   });
   
});

/* 댓글 삭제하기 */
$(function(){
      $('.btn-delete').click(function(){
         
         if(!confirm("정말로 삭제하시겠습니까?")){return;}
         else{
            $.ajax({
               url:"<%=request.getContextPath()%>/gallery/deleteComment",
               data:{'galCommentNo':$(this).val(),'groupNo':<%=groupNo%>,
                  'galNo':<%=gplist.get(0).getGalNo()%>,
                  'galFileNo':<%=gplist.get(0).getGalFileNo()%>,
                  'albumCode':'<%=gplist.get(0).getAlbumCode()%>'
               },
               type:'post',
               dataType:'html',
               success:function(data){
                  if(data!=null)
                  {   
                     /* console.log(data); */
                     alert('댓글을 삭제하였습니다');
                     $('.comment-editor').html(data);
                  }
                  else
                  {
                     alert('댓글 삭제에 실패하였습니다');
                  }
               }
            })
         }
      });
   });
/* 대댓글 쓰기 함수*/
$(function(){
   var eventflag;
   $('.btn-reply').on('click',function(e){
      console.log($(this));
      <%if(loginMember!=null){%>
         eventflag=true;
         var div=$("<div class='recomment_content'></div>");
         var html="";
         html+="<input type='hidden' name='groupNo' value='<%=groupNo %>'/>"
         html+="<input type='hidden' name='galNo' value='<%=gplist.get(0).getGalNo()%>'/>";
         html+="<input type='hidden' name='galCommentWriterNo' value='<%=loginMember.getMemberNo()%>'/>";
         html+="<input type='hidden' name='galCommentLevel' value='2'/>";
         html+="<input type='hidden' name='albumCode' value='<%=gplist.get(0).getAlbumCode()%>'/>";
         html+="<input type='hidden' name='galFileNo2' value='<%=gplist.get(0).getGalFileNo()%>'/>";
         html+="<input type='hidden' name='galCommentRef2' value='"+$(this).val()+"'/>";
         html+="<fieldset class='modal_comment'>";
         html+="<div class='comment_write'>";
         html+="<textarea name='galCommentContent' id='galCommentContent' placeholder='소중한 댓글을 입력해주세요' tabindex='3' style='resize:none;box-sizing: border-box;width:100%;height:80;border:1px solid #fff;'></textarea>";
         html+="</div>";
         html+="<div class='comment_btn'>";
         html+="<button value='"+$(this).val()+"' type='button' class='btn-insert' style='float:right;width:65px;height:28px;font-size:14px;line-height:15px;border-radius: 20px;border:none;background-color:white;'>입력</button>";
         html+="</div>"
         html+="</fieldset>"
         div.html(html);
         div.insertAfter($(this).parent().parent().parent()).children("span").slideDown(800);
         /* 연결된 이벤트 삭제 */
         /* $(this).off('click'); */
         /* 빈공간 누르면 display바뀌는 것 */
         $('.recomment_content').click(function(){
            if(eventflag)
            {
               $(this).css('display','none');
               eventflag=false;
               return;
            }
         }) 
         
         div.find('.btn-insert').click(function(e){
            if(<%=loginMember==null%>)
            {
                fn_loginAlert();
               e.preventDefault();
               return;
            }
            $.ajax({
               url:"<%=request.getContextPath()%>/gallery/commentInsert",
               data:{"groupNo":$('#groupNo').val(),
                  "galNo":$('#galNo').val(),
                  "galCommentWriterNo":$('#galCommentWriterNo').val(),
                  "galCommentLevel":2,
                  "galCommentRef":$(this).val(),
                  "albumCode":$('#albumCode').val(),
                  "galFileNo":$('#galFileNo2').val(),
                  "galCommentContent":$('#galCommentContent').val(),
               },
               type:"post",
               success:function(data){
                  if(data!=null)
                  {   
                     alert('댓글을 등록하였습니다');
                     $('.comment-editor').html(data);
                  }
                  else
                  {
                     alert('댓글 등록에 실패하였습니다');
                  }
               },
               error:function(request){console.log(request);}
            })
         });
         div.find("textarea").focus();
      <%}%>
   });
   function fn_loginAlert()
   {
      alert('로그인 후 이용할 수 있습니다.');
   }

   
   /* 댓글 등록 */
   $(function(){
      $('.btn-insert1').click(function(){
         $.ajax({
            url:"<%=request.getContextPath()%>/gallery/commentInsert",
            data:{"groupNo":$('#groupNo').val(),
               "galNo":$('#galNo').val(),
               "galCommentWriterNo":$('#galCommentWriterNo').val(),
               "galCommentLevel":$('#galCommentLevel').val(),
               "galCommentRef":$('#galCommentRef').val(),
               "albumCode":$('#albumCode').val(),
               "galFileNo":$('#galFileNo2').val(),
               "galCommentContent":$('#galCommentContent').val(),
            },
            type:"post",
            success:function(data){
               if(data!=null)
               {   
                  console.log(data);
                  alert('댓글 등록 완료!');
                  $('.comment-editor').html(data);
               }
               else
               {
                  alert('댓글 등록에 실패하였습니다');
               }
            },
            error:function(request){console.log(request);}
         });               
      });
   });
});
</script>