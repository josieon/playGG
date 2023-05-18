const main = {
    // 초기화
    init() {
        const btnSearch = document.querySelector('#btn-search');
        if (btnSearch) {
            btnSearch.addEventListener('click', () => this.search());
        }
    },

    search()  {
        const summoner = document.querySelector('#summoner').value;
        console.log(summoner);
        fetch(`/summoners/kr?name=${summoner}`, {
            method: 'GET',
            headers: {
                "Content-Type": "application/json; charset=utf-8"
            },
        })
        .then(response => {
            window.location.href = `/summoners/kr?name=${summoner}`;
        })
        .catch(error => {
            alert(error.message);
        })
    }
}

main.init();