const main ={
    //초기화 함수
    init() {
        const btnSave = document.querySelector('#btn-save');
        if(btnSave) btnSave.addEventListener('click', () => this.save());

        const btnUpdate = document.querySelector('#btn-update');
        if(btnUpdate) btnUpdate.addEventListener('click', () => this.update());

        const btnDelete = document.querySelector('#btn-delete');
        if(btnDelete) btnDelete.addEventListener('click', () => this.delete());
    },
    //데이터 저장 함수
    save() {
        const data = {
            title: document.querySelector('#title').value,
            videoUrl: document.querySelector('#videoUrl').value,
            contents: document.querySelector('#summernote').value,
        };
   //fetch API를 이용해 POST 요청을 보내고 결과를 처리
   //fetch()는 요청에 대한 서버의 응답을 포함하는 Response 객체로 확인되는 Promise를 반환
        fetch('/api/v1/board-post',{
            method: 'POST',
            headers: {
            'Content-Type': 'application/json; charset=utf-8',
            },
            body: JSON.stringify(data),
        })
        .then((response) => {
            if(response.status === 200 || response.status === 201){
            // 저장 성공 시 메세지 출력 후 메인 페이지로 이동
             alert('글이 등록되었습니다.');
             window.location.href = '/community';
            } else {
            // 저장 실패 시 오류 메세지 출력
             alert('오류가 발생했습니다.');
            }
        })
        .catch((error) => {
            // 네트워크 오류 등 예외 발생 시 에러 메세지 출력
            alert(error.message);
        });
   },
   // 데이터 수정 함수
   update(){
        const data = {
            title: document.querySelector('#title').value,
            videoUrl: document.querySelector('#videoUrl').value,
            contents: document.querySelector('#summernote').value,
        };
        const postId = document.querySelector('#postId').value;
        // fetch API를 이용해 PUT 요청을 보내고 결과 처리
        fetch(`/api/v1/board-post/${postId}`,{
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json; charset=utf-8',
            },
            body : JSON.stringify(data)
        })
        .then((response) => {
            if (response.status === 200 || response.status === 201){
                //수정 성공 시 출력
                alert('글이 수정되었습니다.');
                window.location.href = '/community';
            } else {
                //수정 실패 시 출력
                alert('오류가 발생했습니다.')
            }
        })
        .catch((error) => {
            alert(error.message);
        });
       },
       // 데이터 삭제 함수
   delete(){
        const postId = document.querySelector('#postId').value;
        fetch(`/api/v1/board-post/${postId}`, {
            method: 'DELETE',
        })
        .then((response) => {
            if (response.status === 200 || response.status === 201){
                //삭제 성공
                alert('글이 삭제되었습니다.');
                window.location.href = '/community';
            } else {
                //삭제 실패
                alert('오류가 발생했습니다.');
            }
        })
        .catch((error) => {
            //네트워크 오류 등 예외 발생
           alert(error.message);
        });
   },
}
main.init();