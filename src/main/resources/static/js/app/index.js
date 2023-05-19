const main = {
    init() {
        const btnSave = document.querySelector('#btn-save');
        if(btnSave) btnSave.addEventListener('click', ()=> this.save());


        const roleSelection = document.getElementById('role-selection');
        if (roleSelection) {
            roleSelection.addEventListener('change',function(){
                const selectedRole = document.querySelector('input[name="role"]:checked').value;
                main.showRoleContent(selectedRole);
            });
        }



    },

    save() {
        const data = {
            title: document.querySelector("#title").value,
            author: document.querySelector("#author").value,
            content: document.querySelector("#content").value,
            position: document.querySelector("input[name='position']:checked").value,
            tier: document.querySelector('#tier').value,

        };
        console.log(data);
        fetch('/posts/dto', {
            method: "POST",
            headers:{
            "Content-Type":"application/json;charset=utf-8",
            },
            body: JSON.stringify(data),
        })
        .then((response) => {
            if(response.status === 200 || response.status === 201){
                //저장 성공
                alert("글이 등록되었습니다.");
                window.location.href = "/";
            } else {
                //저장 실패
                alert("오류가 발생했습니다.")
            }
        })
        .catch((error) => {
            //네트워크 오류 등 예외 발생
            alert(error.message)
        });
    },

    showRoleContent(role) {
        const roleContent = document.getElementById('role-content');
        if(role === 'position'){
            roleContent.textContent = 'Position Lane Contents';
        }else if (role === 'top'){
            roleContent.textContent = 'Top Lane Contents';
        }else if(role === 'mid'){
            roleContent.textContent = 'Mid Lane Contents';
        }else if(role === 'jungle'){
            roleContent.textContent = 'Jungle Contents';
        }else if(role === 'bottom'){
            roleContent.textContent = 'Bottom Contents';
        }else if(role === 'supporter'){
            roleContent.textContent = 'Supporter Contents';
        }
    }


}



main.init();