var main = {
    init : function () {
        var _this = this;

        $('#btn-save').on('click', function (){
            _this.save();
        });

        $('#btn-update').on('click', function (){
            _this.update();
        });

        $('#btn-delete').on('click', function (){
            _this.delete();
        });
    },

    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type : 'POST',
            url : '/api/v1/posts',
            dataType : 'json',
            contentType : 'application/json; charset=UTF-8',
            data : JSON.stringify(data)
        }).done(function (){
            alert('글이 등록되었습니다.');
            // 글 등록 성공시 메인페이지로 이동
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type : 'PUT',
            url : '/api/v1/posts/' + id,
            dataType : 'json',
            contentType : 'application/json; charset=UTF-8',
            data : JSON.stringify(data)
        }).done(function () {
            alert('글이 수정되었습니다.');
            // 글 수정 성공시 메인페이지로 이동
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=UTF-8'
        }).done(function () {
            alert('글이 삭제되었습니다.');
            // 글 삭제 성공시 메인페이지로 이동
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();
/*
*   var main 선언 이유
*   브라우저의 스코프는 공용 공간으로 쓰기이 떄문에
*   나중에 로딩된 js의 init,save가 먼저 로딩된 js의 function을 덮어 쓰게됨
*
*   여러사람이 참여하는 프로젝트에서는 중복된 함수 이름이 자주 발생하므로, 이러한 경우를 피하기 위해
*   해당 js만의 유효범위를 만들어 사용
* */