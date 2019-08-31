/**
 * 提交回复
 */
function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    commentAndQuestion(questionId, 1, content);
}

function commentAndQuestion(targetId, type, content) {
    if (!content) {
        alert("不能回复空内容")
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            if (response.code == 200) {
                window.location.reload();
            } else {
                if (response.code == 2003) {
                    let isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=e8f745c1d20c602ff26b&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(response.message);
                }
            }
        },
        dataType: "json",
    });
}

function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-" + commentId).val();
    commentAndQuestion(commentId, 2, content);
}

/**
 * 展开二级评论功能
 */
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);

    /*获取二级评论展开状态*/
    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        /*折叠二级评论*/
        comments.removeClass("in");
        /*标记评论状态并移除css样式*/
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {
        var subCommentContainer = $("#comment-" + id);
        if (subCommentContainer.children().length != 1) {
            /*展开二级评论*/
            comments.addClass("in");
            /*标记评论状态添加css样式*/
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        } else {
            /*手动拼接二级评论html代码*/
            $.getJSON("/comment/" + id, function (data) {
                /*foreach遍历出所有的子元素*/
                $.each(data.data.reverse(), function (index, comment) {
                    //拼接左边media-left 和头像
                    var mediaLeftElement = $("<div/>",{
                        "class":"media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded",
                        "src": comment.user.avatarUrl
                    }));

                    //右边主体 和 评论
                    var mediaBodyEmlment = $("<div/>",{
                        "class":"media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": comment.user.name
                    })).append($("<div/>", {
                        "html": comment.content
                    })).append($("<div/>", {
                        "class":"menu"
                    }).append($("<span/>", {
                        "class": "pull-right",
                        "html": moment(comment.gmtCreate).format("YYYY-MM-DD h:mm:ss")
                    })));

                    //拼接media
                    var mediaElement = $("<div/>",{
                        "class":"media"
                    }).append(mediaLeftElement).append(mediaBodyEmlment);

                    //拼接comments回复
                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments",
                    }).append(mediaElement);
                    //执行拼接
                    subCommentContainer.prepend(commentElement);//追加在子元素前面
                });
                /*展开二级评论*/
                comments.addClass("in");
                /*标记评论状态添加css样式*/
                e.setAttribute("data-collapse", "in");
                e.classList.add("active");
            });
        }
    }
}